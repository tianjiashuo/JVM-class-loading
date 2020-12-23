package com.jvm.demo.controller;

import com.jvm.demo.Entity.*;
import com.jvm.demo.service.ReceiveService;
import com.jvm.demo.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/code")
@CrossOrigin
public class Receive {

    @Autowired
    private ReceiveService receiveService;

    public Map<Integer, AreaVo> getInfo() {
        Map<Integer, AreaVo> map = new HashMap<>();
        map.put(1, null);
        map.put(2, null);
        long[] param = {0, 1};
        map.put(3, MethodAreaVo.builder().area("MethodArea").ClassName("com/jvm/demo/source/Add")
                .method(new String[]{"addcd()", "add()"}).pool(param).build());
        map.put(4, MethodAreaVo.builder().area("MethodArea").type("int").MethodName("a").value("0").build());
        map.put(5, MethodAreaVo.builder().area("MethodArea").MethodName("b").type("int").value("1").build());
        map.put(6, null);
        map.put(7, HeapVo.builder().area("Heap").name("c").value("3").type("int").build());
        map.put(8, HeapVo.builder().area("Heap").name("d").value("4").type("int").build());
        map.put(9, null);
        map.put(10, null);
        map.put(11, null);
        map.put(12, null);
        map.put(13, null);
        map.put(14, null);
        map.put(15, null);
        map.put(16, null);
        map.put(17, null);
        map.put(18, MethodAreaVo.builder().area("MethodArea").method(new String[]{"main"}).type("void").param(new String[]{"String[] args"}).build());
        map.put(19, JvmStackVo.builder().area("JvmStack").methodName("add").build());
        map.put(20, JvmStackVo.builder().area("JvmStack").referenceName("add").type("Add").build());
        map.put(21, HeapVo.builder().area("Heap").name("add").type("Add").build());
        map.put(22, JvmStackVo.builder().area("JvmStack").type("int").methodName("addcd").build());
        map.put(23, null);
        return map;
    }

    @PostMapping("/receive")
    public Map<Integer, AreaVo> receive(@RequestBody String code){
        return getInfo();
    }

    @RequestMapping("/fromId")
    public R receiveFromId(@RequestParam("id") int id){
        if (receiveService.receiveById(id) == null){
            System.out.println("wrong");
        }else{
            System.out.println(receiveService.receiveById(id).toString());
        }
        return R.ok().put("文件内容是",receiveService.receiveById(id).toString());
    }


}
