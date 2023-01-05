package ltd.newbee.cloud.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
public class WebfluxController {

    private final String SERVICE_URL = "http://localhost:8088";
    //private final String SERVICE_URL = "http://localhost:8082";

    private WebClient webClient = WebClient.builder()
            .baseUrl(SERVICE_URL)
            .build();

    /**
     * 使用WebClient处理http请求
     * @return
     */
    @GetMapping("/webClientTest")
    public String webClientTest() {
        Mono<String> mono = webClient
                // GET 请求方式
                .get()
                // 路径
                .uri("/hello")
                // 获取响应结果
                .retrieve()
                //响应结果转换
                .bodyToMono(String.class);

        // 打印请求结果
        mono.subscribe(result -> {
            System.out.println(result);
        });
        return "请求成功";
    }
}
