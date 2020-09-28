package test1;

public class add {
    private int a = 1;
    public int  add(int b){
        System.out.println("-----------------");
        System.out.println("in class add:");
        print pr = new print();
        //方法一
        pr.printTrack();
        //方法二
        //pr.printTrack2();
        int c = a + b;
        return c;
    }

}
