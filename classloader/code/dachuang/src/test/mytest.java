package test;

import java.util.ArrayList;

public class mytest {
    /**
     * static的值（类成员变量）会直接存储到常量池。其余的（实例变量）会被实例引用调用
     * 就是aload指令。
     */
    public int Max_count = 10;
    public int count = 0;
    public static double real = 2.01;
    public static String str = "Hello World";

    public static double mycount(){
        return 1.0;
    }

//    public static Boolean real(){
//        count();
//        return true;
//    }

//    public static void meiyou(){
//
//    }

    public static int add(int a,int b){
        int result = 99;
        result = a + b;
        return result;
    }

    public static boolean greaterThan(int a,int b){
        boolean flag = false;
        if (a > b){
            flag = true;
        }
        return flag;
    }

    public static void he(String i){
        i = "aaa";
    }

    public static int tripleadd(int a,int b,int c){
        int result = 0;
        int middleResult = 4;
        result = (a + b + c) * middleResult;
        return result;
    }

    public static void sayNothing(){
        System.out.println("hahaha");
    }

    public static int subc(int a, int b){
        int result = 0;
        result = a - b;
        return result;
    }

    public void doMyThing(){
        int a = 2;
        int b = 3;
        int c = a + b;
    }

    public static void main(String args[]){
//        if (real()){
//            sayNothing();
//            System.out.println(count());
////            add add = new add();
////            add.addcd();
//        }
//        int myResult = 0;
//        myResult = tripleadd(1,2,3);
//        System.out.println(myResult);
//        boolean yourResult = false;
//        yourResult = greaterThan(1,2);
//        System.out.println(yourResult);
//        ArrayList hello = new ArrayList();
//
//        hello.set(3,4);
    }
}
