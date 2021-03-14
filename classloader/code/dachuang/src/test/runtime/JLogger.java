package test.runtime;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JLogger类，记录日志，插入方法
 * JLogger.method();
 */
public class JLogger {

    static HashMap<Integer, List<String>> stackframe = new HashMap<>();

    /**
     * 打印栈帧信息
     */
    public static void logMethodAccess() throws IOException {
        String result = null;
        StackTraceElement[] st = Thread.currentThread().getStackTrace();

        if (st == null) {
            System.out.println("无栈...");
            return;
        }
        StringBuffer sbf = new StringBuffer();
        for (StackTraceElement e : st) {
            if (sbf.length() > 0) {
                sbf.append(" <- ");
            }
            if ((!(e.toString().contains("StackTrace"))) && (!(e.toString().contains("printTrack"))) && (!(e.toString().contains("JLogger")))){
//                sbf.append(java.text.MessageFormat.format("{0}.{1}() {2}", e.getClassName(), e.getMethodName(),e.getLineNumber()));
                sbf.append(java.text.MessageFormat.format("{0}() {1}", e.getMethodName(), e.getLineNumber()));
            }
        }
        result = sbf.toString();
        /**
         * 测试输出，以后改为return result；
         * 方法一：写一个map存到本地 感觉方法一比较简洁
         * 方法二：写一个类 搞一个对象map
         */
        System.out.println(result);
        dataToMap(result);
    }

    /**
     * 处理字符串的函数
     * 存的map是行号，然后一个String的list
     */
    public static void dataToMap(String result) throws IOException {
//        System.out.println(result);
        String s[] =result.split("\\s+");
        String line=(s[(s.length-1)]);
        int linenumber = Integer.valueOf(line);
        List<String> strackTrace = new ArrayList<>();
//        System.out.println(linenumber);
        for (int i = 0; i < s.length;i ++){
//            System.out.println(s[i]);
            if(s[i].contains("()")){
                strackTrace.add(s[i]);
            }
        }
        if (linenumber != -1) {
            stackframe.put(linenumber,strackTrace);
        }

        String dir = "/Users/zhangqizhou/Desktop/testResult";
        dataToMapBeta(dir,stackframe);
    }

    public static void dataToMapBeta(String outputFilePath,HashMap<Integer, List<String>> stackframe){
        //new file object
        File file = new File(outputFilePath);

        BufferedWriter bf = null;;
        try{

            //create new BufferedWriter for the output file
            bf = new BufferedWriter( new FileWriter(file) );

            //iterate map entries
            for(Map.Entry<Integer, List<String>> entry : stackframe.entrySet()){

                //put key and value separated by a colon
                bf.write( entry.getKey() + ":" + entry.getValue() );

                //new line
                bf.newLine();
            }
            bf.flush();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                //always close the writer
                bf.close();
            }catch(Exception e){}
        }
    }
}