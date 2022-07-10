package com.design.patterns.corepatterns.factory;

import org.springframework.http.converter.json.GsonBuilderUtils;

public class Factory_Exercise {
    public static void main(String[] args) {
        PersonFactory pf = new PersonFactory();
    Person p = pf.createPerson("John");
        System.out.println(p);
        Person p1 = pf.createPerson("Harry");
        System.out.println(p1);
    }

}

class Person{
    public int id;
    public String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

class PersonFactory{
    public static int count = 0;
    public Person createPerson(String name){
        return new Person(count++, name);
    }
}
