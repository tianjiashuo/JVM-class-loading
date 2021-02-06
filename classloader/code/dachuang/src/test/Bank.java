package test;

public class Bank {
    public int Max_count = 10;
    public int count = 0;
    public double real = 2.01;
    public static String str = "Hello World";

    public static double count(){
        return 1.0;
    }

    public static Boolean real(){
        count();
        return true;
    }

    public static void meiyou(){

    }

    public static void sayNothing(){
        System.out.println("hahaha");
    }

    public static void main(String args[]){
        if (real()){
            sayNothing();
            System.out.println(count());
            add add = new add();
            add.addcd();
        }
        meiyou();
    }
}
