package test;

public class Person {
    public int ID;
    public String name;
    public int age;
    public int todayMoney;
    public Person(){}
    public Person(int ID, int age, String name, int todayMoney){
        this.ID = ID;
        this.name = name;
        this.todayMoney = todayMoney;
        this.age = age;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getTodayMoney() {
        return todayMoney;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTodayMoney(int todayMoney) {
        this.todayMoney = todayMoney;
    }

    public void add(int a, int b){
        this.todayMoney = a+b;
    }
}
