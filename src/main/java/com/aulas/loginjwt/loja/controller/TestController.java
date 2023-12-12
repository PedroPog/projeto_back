package com.aulas.loginjwt.loja.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class TestController {

    @GetMapping("/test")
    public String hello(@RequestParam String msg){
        return msg;
    }
    @PostMapping("/testpost")
    public String helloPost(@RequestBody String msg){
        return msg;
    }

}
