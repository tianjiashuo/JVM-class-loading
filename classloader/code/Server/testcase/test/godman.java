package test;

public class godman{
    public static int cbu(int a, int b){
        int result = a + b + factur(44,34);
        return result; 
    }

    public static int factur(int a ,int c){
        int result = a + 3/c;
        return result;
    }

    public static void sayhello(){
        System.out.println("hello nice!");
    }

    public static int allocate(int a){
        int re= cbu(a,44);
        return re;
    }

    public static void main(String[] args) {
        int a = cbu(35, 5);
        int b = factur(4, 3);
        int c = allocate(cbu(44,3));
        sayhello();
        System.out.println(a + b + c);
    }
}