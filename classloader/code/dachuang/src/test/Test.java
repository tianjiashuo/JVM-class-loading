package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test{

    public static void execCMD(String cmd){
        StringBuilder result = new StringBuilder();
        try {
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader bf = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));
            String line = null;
            while((line=  bf.readLine())!=null) {
                result.append(line+"\n");
            }
        }catch(Exception e) {
            result.delete(0,result.length()-1).append("无效命令");
        };
        System.out.println(result.toString());
    }

    public static void toJar(String address) throws IOException, InterruptedException {
        File f = new File(System.getProperty("user.home"), address);
        ProcessBuilder pb = new ProcessBuilder().directory(f);
        pb.inheritIO();
        pb.command("java", "-javaagent:lib/Agent.jar",
                "-cp", "./Dachuang/dachuang.jar:testcase", "test.Bank");
        Process p = pb.start();
        p.waitFor();
    }

    public static void execute(String filename) throws IOException, InterruptedException {
        File f = new File(System.getProperty("user.home"), "Desktop/Tmp/Server/testcase/test");
        ProcessBuilder pb = new ProcessBuilder().directory(f);
        pb.inheritIO();
        pb.command("javac", filename);
        Process p = pb.start();
        p.waitFor();
        System.out.println("Yes!");
    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        toJar("Desktop/Tmp/Server/");

        execute("la.java");
    }
}
