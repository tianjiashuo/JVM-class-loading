package com.jvm.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MethodareaController {
    @GetMapping("/methodarea")
    @CrossOrigin
    MethodareaVo methodarea(){
        MethodareaVo methodareaVo = new MethodareaVo("appMain","main");
        return methodareaVo;
    }
}
