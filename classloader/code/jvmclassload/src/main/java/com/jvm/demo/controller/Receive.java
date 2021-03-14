package com.jvm.demo.controller;

import com.jvm.demo.service.ReceiveService;
import com.jvm.demo.utils.R;
import com.jvm.demo.utils.toClassUtil;
import com.jvm.demo.utils.toFileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/code")
@CrossOrigin
public class Receive {

    @Autowired
    private ReceiveService receiveService;
    private static String myAddress = "/Users/zhangqizhou/Desktop/Tmp/Server/testcase/test/";

    private String classFile;

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

    public Map<Object, Object> getInfo() {
        Map<Object, Object> map = new HashMap<>();
        map = toFileUtil.getHashMapFromTextFile();
        return map;
    }

    @RequestMapping("/receive")
    public Map<Object, Object> receive(){
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

//    @PostMapping("/analyse")
//    public Map<Object,Object> saveFile(@RequestParam("file") MultipartFile zipFile) throws IOException, InterruptedException {
//        String data = zipFile.getOriginalFilename();
//        System.out.println(data);
//        String[] datas = data.split("\\.");
//        System.out.println(datas[0]);
//        //这个方法是把内容写到address（file下的dest文本）里面
//        zipFile.transferTo(new File(file));
//        //调用这个方法读取address里面内容
//        String content = getContent(file);
//        //创建result文件夹和对应java文件
//        toClassUtil.create(result,data);
//        //copy文件address(.txt) --> result(.java)下的对应文件
//        toClassUtil.copyFile(file,result+"/"+data,1);
//        //编译文件(.java->.class)
//        toClassUtil.toClass(forClass,result+"/"+data);
//        //反编译class文件(.class->bytecode)
//        String vclass = toClassUtil.javapClass(forClass+"/" + datas[0]);
//        classFile = vclass;
//        //保存到指定文件位置Desktop/Tmp/Server/testcase/test
//        toFileUtil.StoreInFile(classFile,"/Users/zhangqizhou/Desktop/Tmp/Server/testcase/test/"+datas[0]+".class");
//        //获取分析结果,文件分析结果放在了指定文件里Map<Object,Object>形式
//        toClassUtil.toJar("Desktop/Tmp/Server/",datas[0]);
//        //读取分析结果返回Map
//        Map<Object,Object> analyze;
//        analyze = toFileUtil.getHashMapFromTextFile();
//        //保存到数据库
//        ReceiveEntity rn = ReceiveEntity.builder().fileName(data).content(content).vclass(vclass).build();
//        receiveService.save(rn);
//        return analyze;
//    }

    @PostMapping ("/analyze")
    public Map<Object,Object> analyzeFile(@RequestParam("content") String content,@RequestParam("fileName") String fileName) throws IOException, InterruptedException {
        toFileUtil.StoreInFile(content,myAddress +fileName+".java");
        toClassUtil.execute(fileName);
        toClassUtil.toJar("Desktop/Tmp/Server/",fileName);
        //读取分析结果返回Map
        Map<Object,Object> analyze;
        analyze = toFileUtil.getHashMapFromTextFile();
        return analyze;
    }

}
