package com.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yangchun
 * @description:
 * @date: Created in 2019-09-02 17:11
 */
@RestController
public class TestController {
    @Autowired
    private IAmqpService iAmqpService;
    @RequestMapping("/test")
    public void test(){
        iAmqpService.convertAndSend("my first topic");
    }
}
