package com.jvm.demo.controller;

import com.jvm.demo.Entity.MethodAreaVo;
import com.jvm.demo.service.test;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class MethodareaController {

    test testM = new test();
//    @GetMapping("/methodarea")
//    @CrossOrigin
//    MethodAreaVo methodarea(){
//        MethodareaVo methodareaVo = new MethodareaVo("appMain","main");
//        return methodareaVo;
//    }
    @GetMapping("/methodTest")
    @CrossOrigin
    Map<Integer, String> testMethod(){
        return testM.testAdd();
    }
}
