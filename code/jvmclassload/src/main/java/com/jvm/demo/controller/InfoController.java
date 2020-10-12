package com.jvm.demo.controller;

import com.jvm.demo.Entity.AreaVo;
import com.jvm.demo.Entity.HeapVo;
import com.jvm.demo.Entity.JvmStackVo;
import com.jvm.demo.Entity.MethodAreaVo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class InfoController {
    @RequestMapping("/getInfo")
    @CrossOrigin
    public Map<Integer, AreaVo> getInfo() {
        Map<Integer, AreaVo> map = new HashMap<>();
        map.put(1, null);
        map.put(2, null);
        long[] param = {0, 1};
        map.put(3, MethodAreaVo.builder().classname("com/jvm/demo/source/Add")
                .method(new String[]{"addcd()", "add()"}).pool(param).build());
        map.put(4, MethodAreaVo.builder().fieldName("a").value("0").build());
        map.put(5, MethodAreaVo.builder().fieldName("b").value("1").build());
        map.put(6, null);
        map.put(7, HeapVo.builder().name("c").value("3").build());
        map.put(8, HeapVo.builder().name("d").value("4").build());
        map.put(9, null);
        map.put(10, null);
        map.put(11, null);
        map.put(12, null);
        map.put(13, null);
        map.put(14, null);
        map.put(15, null);
        map.put(16, null);
        map.put(17, null);
        map.put(18, MethodAreaVo.builder().method(new String[]{"main"}).build());
        map.put(19, JvmStackVo.builder().methodName("add").args(new String[]{}).build());
        map.put(20, JvmStackVo.builder().referenceName("add").type("Add").build());
        map.put(21, HeapVo.builder().name("add").type("Add").build());
        map.put(22, JvmStackVo.builder().type("int").methodName("addcd").build());
        map.put(23, null);
        return map;
    }

}
