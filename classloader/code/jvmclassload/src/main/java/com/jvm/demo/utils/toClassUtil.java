package com.jvm.demo.utils;

import java.io.*;

public class toClassUtil {
//    private static final String dest = "/Users/zhangqizhou/Documents/JavaVirtualMachines/JVM-class-loading/classloader/code/jvmclassload/src/main/resources/class/";

    public static void toClass(String dest,String filename) {
        try {
            Process process = Runtime.getRuntime().exec("javac " + "-d " + dest + " " + filename);
            System.out.println("javac " + "-d " + dest + " " + filename);
            System.out.println("编译成功");
            InputStream in = null;
            in = process.getInputStream();
            BufferedReader read = new BufferedReader(new InputStreamReader(in, "GBK"));

            String line = null;

            while ((line = read.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("编译失败");
        }
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
}
