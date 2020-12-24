package com.jvm.demo.controller;

import com.jvm.demo.Entity.*;
import com.jvm.demo.service.ReceiveService;
import com.jvm.demo.utils.R;
import com.jvm.demo.utils.toClassUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/code")
@CrossOrigin
public class Receive {

    @Autowired
    private ReceiveService receiveService;

    private final static String file = new String("/Users/zhangqizhou/Documents/JavaVirtualMachines/JVM-class-loading/classloader/code/jvmclassload/file/dest");
    private final static String result = new String("/Users/zhangqizhou/Documents/JavaVirtualMachines/JVM-class-loading/classloader/code/jvmclassload/result");
    private final static String forClass = new String("/Users/zhangqizhou/Documents/JavaVirtualMachines/JVM-class-loading/classloader/code/jvmclassload/forClass");

    public String getContent(String filename) {
        StringBuffer sb = new StringBuffer();

        Reader red;
        try {
            red = new FileReader(filename);
            BufferedReader br = new BufferedReader(red);
            String str;
            while ((str = br.readLine()) != null) {
                sb.append(str);
                sb.append("\r\n");
            }
            br.close();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return sb.toString();
    }

    public String FiletoString(File file) throws IOException {
        StringBuffer buffer = new StringBuffer();
        BufferedReader bf= new BufferedReader(new FileReader(file));
        String s = null;
        while((s = bf.readLine())!=null){//使用readLine方法，一次读一行
            buffer.append(s.trim());
        }
        String xml = buffer.toString();
        return xml;
    }

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

    @PostMapping("/save")
    public R saveFile(@RequestParam("file") MultipartFile zipFile) throws IOException {
        String data = zipFile.getOriginalFilename();
        System.out.println(data);
        String[] datas = data.split("\\.");
        System.out.println(datas[0]);
        //这个方法是把内容写到address（file下的dest文本）里面
        zipFile.transferTo(new File(file));
        //调用这个方法读取address里面内容
        String content = getContent(file);
        //创建result文件夹和对应java文件
        toClassUtil.create(result,data);
        //copy文件address(.txt) --> result(.java)下的对应文件
        toClassUtil.copyFile(file,result+"/"+data,1);
        //编译文件(.java->.class)
        toClassUtil.toClass(forClass,result+"/"+data);
        //反编译class文件(.class->bytecode)
        String vclass = toClassUtil.javapClass(forClass+"/" + datas[0]);
        ReceiveEntity rn = ReceiveEntity.builder().fileName(data).content(content).vclass(vclass).build();
        //保存到数据库
        receiveService.save(rn);
        return R.ok("文件上传成功");
    }

}
