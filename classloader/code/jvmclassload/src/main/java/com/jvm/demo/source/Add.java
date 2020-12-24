package com.jvm.demo.source;

public class Add {      //Add放入方法区
    private static int a = 0;       //a进入方法区
    private static int b = 1;       //b进入方法区

    private int c = 3;      //c进入堆
    private int d = 4;      //d进入堆

    public int addcd(){     //addcd引用进入栈
        return c + d ;       //addcd指令进入方法区
    }

    public static int add(){        //add引用进入栈，add进入方法区
        return a + b;
    }

    public static void main(String[] args) {//main进入方法区，这时候main方法进入execute子系统
        add();
        Add add = new Add();
        //add引用放入栈区， add对象放入堆区
        System.out.println(add.addcd());//
    }
}
