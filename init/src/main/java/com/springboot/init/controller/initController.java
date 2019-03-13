package com.springboot.init.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/init")
public class initController {

    @GetMapping()
    public String init(){

        return "hello SpringBoot!";
    }
}
