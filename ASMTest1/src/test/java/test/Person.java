package test;

public class Person {

    private int Id;
    private String name;
    private int age;
    private int totalMoney;

    public Person(int id, String name, int age, int totalMoney) {
        Id = id;
        this.name = name;
        this.age = age;
        this.totalMoney = totalMoney;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public void add(int a, int b){
        this.totalMoney = a + b;
    }
}
