package com.tju.classloader;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class VisitHeapByCmd {
    static String allPids = "";

    public static String exeCmd(String commandStr) {
        BufferedReader br = null;
        try {
            Process p = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
//            System.out.println(sb.toString());
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public static String getPid(String allPids){
        String line[] = allPids.split("\\r?\\n");
        int index = 0;
        for(int i = 0; i < line.length;i ++){
            if(line[i].contains("VisitHeapByCmd")){
                index = i;
            }
        }
//        System.out.println(line[index]);
        String targetPid = line[index];
        String pid = targetPid.split("\\s+")[0];
//        System.out.println(pid);
        return pid;
    }

    public static void main(String[] args) {
        String commandStr = "jps";
        allPids = VisitHeapByCmd.exeCmd(commandStr);
//        System.out.println(allPids);
        String pid = getPid(allPids);
        String commandStr1 = "jmap -heap " + pid;
        String result = VisitHeapByCmd.exeCmd(commandStr1);
        System.out.println(result);
    }

}
