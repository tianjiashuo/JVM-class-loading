package test1;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class test1 {

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

//    public static void main(String[] args) {
//        System.out.println("-----------------");
//        System.out.println("in class test1:");
//        print pr = new print();
//        pr.printTrack();
////        Exception e = new Exception();
////        String s = getStackTrace(e);
////        System.out.println(s);
//        add ad = new add();
//        int result = ad.add(2);
//        //System.out.println(result);
//        //pr.printTrack();
//    }


}
