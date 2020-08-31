package com.jvm.demo.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeapController {
    @GetMapping("/heap")
    @CrossOrigin
    HeapVo heap(){
        HeapVo heapVo = new HeapVo("name","tset","xxx");
        return heapVo;
    }
}
