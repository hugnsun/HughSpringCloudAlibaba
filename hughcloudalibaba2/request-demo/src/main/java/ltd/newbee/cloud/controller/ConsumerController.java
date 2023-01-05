package ltd.newbee.cloud.controller;


import lombok.AllArgsConstructor;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class ConsumerController {

    private RestTemplate restTemplate;
    private final String SERVICE_URL = "http://localhost:8088";
    //private final String SERVICE_URL = "http://localhost:8082";

    /**
     * 使用RestTemplate来处理http请求
     * @return
     * @throws IOException
     */
    @GetMapping("/restTemplateTest")
    public String restTemplateTest() {
        // 打印请求结果
      return  restTemplate.getForObject(SERVICE_URL + "/hello", String.class);
    }

    /**
     * 使用HttpClient来处理http请求
     * @return
     * @throws IOException
     */
    @GetMapping("/httpClientTest")
    public String httpClientTest() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(SERVICE_URL + "/hello");
        CloseableHttpResponse response = null;
        try {
            // 执行请求
            response = httpClient.execute(httpGet);
            // 判断返回状态码
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "UTF-8");
                // 打印请求结果
               return content;
            }
        } finally {
            if (response != null) {
                response.close();
            }
            httpClient.close();
        }
        return "请求成功";
    }
}