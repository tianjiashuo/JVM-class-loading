package com.jvm.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StackController {
    @GetMapping("/stack")
    @CrossOrigin
    StackVo stack(){
        StackVo stackVo = new StackVo("variableNameTest","typeTest","valueTest","quoteTest");
        return stackVo;
    }
}
