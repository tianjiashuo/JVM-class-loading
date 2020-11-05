package test;

import java.util.ArrayList;

public class Bank {

    //不是final拿不到
    public int maxAccount = 10;

    public String s1 = new String("abc");
    public String s2 = new String("abc");

    public String s3 = "abc";
    public String s4 = "abc";


    public static void main(String[] args) {
        System.out.println("main");
        Person p1 = new Person(1,"A",10,100);
        p1.getId();
    }
}
