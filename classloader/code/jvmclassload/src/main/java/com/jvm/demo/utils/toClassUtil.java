package com.jvm.demo.utils;

import java.io.*;

public class toClassUtil {
//    private static final String dest = "/Users/zhangqizhou/Documents/JavaVirtualMachines/JVM-class-loading/classloader/code/jvmclassload/src/main/resources/class/";

    /**
     * 编译.java文件成.class文件
     * @param dest
     * @param filename
     */
    public static void toClass(String dest,String filename) {
        try {
            Process first = Runtime.getRuntime().exec("cd " + dest);
            System.out.println("cd " + dest);
            Process process = Runtime.getRuntime().exec("javac " + "-d " + dest + " " + filename);
            System.out.println("javac " + "-d " + dest + " " + filename);
            InputStream in = null;
            in = process.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in, "GBK"));

            String line = null;

            while ((line = read.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("编译成功");
        } catch (IOException e) {
            System.out.println("编译失败");
        }
    }

    public static void execute(String filename) throws IOException, InterruptedException {
        File f = new File(System.getProperty("user.home"), "Desktop/Tmp/Server/testcase/test");
        ProcessBuilder pb = new ProcessBuilder().directory(f);
        pb.inheritIO();
        pb.command("javac", filename+".java");
        Process p = pb.start();
        p.waitFor();
    }

    public static String javapClass(String dest){
        StringBuffer sb = new StringBuffer();
        try {
            Process process = Runtime.getRuntime().exec("javap -v " + dest);
            System.out.println("反编译成功");
            InputStream in = null;
            in = process.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in, "GBK"));

            String line = null;

            while ((line = read.readLine()) != null) {
                sb.append(line);
                sb.append("\r\n");
            }
        } catch (IOException e) {
            sb.append("failed!");
        }
        return sb.toString();
    }

    public static void copyFile(String origin, String dest, int bufferSize) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(new File(origin));
            out = new FileOutputStream(new File(dest));

            byte[] buffer = new byte[bufferSize];
            int len;

            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            System.out.println("Wrong!");
        }
    }

    public static void reName(File change,String path,String newname){
        File file = new File(path + newname);
        change.renameTo(file);
    }

    public static void create(String path, String filename) {
        File file = new File(path + File.separator + filename);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获得分析结果，这里要记得修改下jar的位置
    public static void toJar(String address,String fileName) throws IOException, InterruptedException {
        File f = new File(System.getProperty("user.home"), address);
        ProcessBuilder pb = new ProcessBuilder().directory(f);
        pb.inheritIO();
        pb.command("java", "-javaagent:lib/Agent.jar",
                "-cp", "./Dachuang/dachuang.jar:testcase", "test."+fileName);
        Process p = pb.start();
        p.waitFor();
    }

}
