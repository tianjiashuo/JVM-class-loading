package test;

public class poly {
    static class Father {
        public int money = 1;

        public Father() {
            money = 2;
            show();
        }

        public void show() {
            System.out.println("I am a father, i have $" + money);
        }
    }

        static class Son extends  Father{
            public int money = 3;

            public Son(){
                money = 4;
                show();
            }

            public void show(){
                System.out.println("I am a son, i have $" + money);
            }
        }

        public static void main(String[] args) {
            Father gay = new Son();
            System.out.println("This gay has $" + gay.money);
        }
}
