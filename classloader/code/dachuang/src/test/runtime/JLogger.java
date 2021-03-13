package test.runtime;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * JLogger类，记录日志，插入方法
 * JLogger.method();
 */
public class JLogger {

    static HashMap<Integer, List<String>> stackframe = new HashMap<>();

    public static void logFileAccess(String className,String methodName,String opcode,String fileName,int lineNumber){

    }

    /**
     * 答应栈帧信息
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
                    sbf.append(java.text.MessageFormat.format("{0}.{1}() {2}", e.getClassName(), e.getMethodName(),e.getLineNumber()));
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
     * 存的map是行号，然后一个string的list
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
        stackframe.put(linenumber,strackTrace);

        String dir = "E:\\b.txt";
        File file = new File(dir);
        //如果文件不存在，创建文件
        if (!file.exists())
            file.createNewFile();
        //创建BufferedWriter对象并向文件写入内容
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        //向文件中写入内容
        bw.write(stackframe.toString());
        bw.flush();
        bw.close();
    }
}
