package ltd.newbee.cloud.controller;

import lombok.AllArgsConstructor;
import ltd.newbee.cloud.component.HelloServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class HelloServiceController {

    private HelloServiceImpl helloService;


    @GetMapping("/hello")
    public String hello() {
        // 调用本地方法，并通过HTTP协议进行响应
        return "hello from " + helloService.getName();
    }

}
