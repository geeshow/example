package com.ken207.example2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/board")
public class BoardController {

    @GetMapping("/hello")
    public String hello() {
        return "hello world";
    }


}
