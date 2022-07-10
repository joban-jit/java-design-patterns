package com.design.patterns.corepatterns.proxy.exercise;

public class ProxyExercise {
}


class Person {
    private int age;

    public Person(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String drink() {
        return "drinking";
    }

    public String drive() {
        return "driving";
    }

    public String drinkAndDrive() {
        return "driving while drunk";
    }
}

class ResponsiblePerson {
    private Person person;

    public ResponsiblePerson(Person person) {
        this.person = person;
    }

    public String drink() {
        if(person.getAge()>=18)
            return "drinking";
        else
            return "too young";
    }

    public String drive() {
        if(person.getAge()>=16)
            return "driving";
        else
            return "too young";
    }

    public String drinkAndDrive() {
        return "dead";
    }

}
