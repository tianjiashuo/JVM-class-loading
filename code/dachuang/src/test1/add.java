package test1;

public class add {
    private int a = 1;
    public int  add(int b){
//        System.out.println("-----------------");
//        System.out.println("in class add:");
//        print pr = new print();
//        //方法一
//        pr.printTrack();
//        //方法二
//        //pr.printTrack2();
        int c = a + b;
        return c;
    }
    public static void main(String[] args) {
        System.out.println("-----------------");
        print pr = new print();
        pr.printTrack();
//        Exception e = new Exception();
//        String s = getStackTrace(e);
//        System.out.println(s);
        add ad = new add();
        int result = ad.add(2);
        //System.out.println(result);
        //pr.printTrack();
    }

}
