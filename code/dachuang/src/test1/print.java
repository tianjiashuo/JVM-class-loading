package test1;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class print {
    void printTrack(){
        System.out.println("方法1");
        System.out.println("id");
        System.out.println(Thread.currentThread().getId());
        StackTraceElement[] st = Thread.currentThread().getStackTrace();
        if(st==null){
            System.out.println("无堆栈...");
            return;
        }
        StringBuffer sbf =new StringBuffer();
        for(StackTraceElement e:st){
            if(sbf.length()>0){
                sbf.append(" <- ");
                sbf.append(System.getProperty("line.separator"));
            }
            sbf.append(java.text.MessageFormat.format("{0}.{1}() {2}"
                    ,e.getClassName()
                    ,e.getMethodName()
                    ,e.getLineNumber()));
        }
        System.out.println(sbf.toString());
    }

    void printTrack2(){
        System.out.println("方法2");
        StackTraceElement stack[] = Thread.currentThread().getStackTrace();
        for(int i=0;i<stack.length;i++){
            System.out.print(stack[i].getClassName()+" ."+stack[i].getMethodName()+"-----");
        }
    }

    //方法3
    public static String getStackTrace(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}
