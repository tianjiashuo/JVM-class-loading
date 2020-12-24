package test;

public class Bank {
    final public int Max_count = 10;
    final public int count = 0;
    final public double real = 2.01;
    final public String str = "Hello World";
    //方法区的运行时常量池
    //不是final拿不到
//    public ArrayList<Person> banks = new ArrayList<Person>();
    public double count(){
        return 1.0;
    }

    public Boolean real(){
        return true;
    }

    public void sayNothing(){
        System.out.println("hahaha");
    }
    public static void main(String args[]){
//        String s1= new String("abc");
//        String s2= new String("abc");
//        System.out.println(s1==s2);
        System.out.println("main");
//        Person p1= new Person(1,20,"aying",7777);
//        p1.getID();
        //add();
        add ad = new add();
        //add引用放入栈区， add对象放入堆区
        int s = ad.add();
//        ClassFileVIstor classVisitor =new ClassFileVIstor();
        System.out.println("IN bank end");
    }
}
