package com.jeremy.wiki.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 接口
public class TestController {
    /*
    GET POST PUT DELETE
    /user?id=1
     */
    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }
}
