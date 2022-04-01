package com.jeremy.wiki.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 接口
public class TestController {
    // @Value("${test.hello：TEST}") TEST 为默认值
    @Value("${test.hello}") //读取配置项
    private String testHello;
    /*
    GET POST PUT DELETE
    /user?id=1
     */
    @GetMapping("/hello")
    public String hello(){
        return "Hello World!" + testHello;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name){
        return "Hello World! Post," + name;
    }
}
