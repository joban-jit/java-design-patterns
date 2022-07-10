package com.design.patterns.corepatterns.singleton;


// Dangerous approach, as there is no way it says it is singleton behaving class
public class Monostate {

    public static void main(String[] args) {
        CheifExecutiveOfficer ceo = new CheifExecutiveOfficer();
        ceo.setName("Adam Smith");
        ceo.setAge(55);
        CheifExecutiveOfficer ceo2 = new CheifExecutiveOfficer();
        System.out.println(ceo2);// CheifExecutiveOfficer{name='Adam Smith', age=55}
    }
}

class CheifExecutiveOfficer{
    // to make a existing instance class singleton, add "static" keyword to data storage members
    // you can make 100 instances of these classes , but as soon you use one of these instances , everything
    // mpas to just a pair of static fields, as they all share common storage elements
    private static String name;
    private static int age;

    public  String getName() {
        return name;
    }

    public  void setName(String name) {
        CheifExecutiveOfficer.name = name;
    }

    public  int getAge() {
        return age;
    }

    public  void setAge(int age) {
        CheifExecutiveOfficer.age = age;
    }

    @Override
    public String toString() {
        return "CheifExecutiveOfficer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
