package test;

public class add {
    public static double mycount(){
        return 1.0;
    }


    public static void meiyou(){
        int a = add(1,2);
        int b = tripleadd(1,2,3);
        int c = a+b;
    }

    public static int add(int a,int b){
        int result = 99;
        result = a + b;
        return result;
    }

    public static int tripleadd(int a,int b,int c){
        int result = 0;
        int middleResult = 4;
        result = (a + b + c) * middleResult;
        return result;
    }


    public static int sub(int a,int b){
        return a-b;
    }
    public static int myadd(int a,int b,int c){
        return tripleadd(a,b,c) + add(a,b);
    }


    public static void main(String args[]){
        int result;
        result = add(35,46);
        int subre = sub(1,4);
        int myresult = 0;
        myresult = myadd(23,45,64);
    }
}
