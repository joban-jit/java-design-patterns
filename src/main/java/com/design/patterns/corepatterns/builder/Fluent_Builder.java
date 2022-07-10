package com.design.patterns.corepatterns.builder;

public class Fluent_Builder {

    public static void main(String[] args) {
        PersonBuilder personBuilder = new PersonBuilder();
        Person person = personBuilder.withName("John").build();
        System.out.println(person);


        EmployeeBuilder employeeBuilder = new EmployeeBuilder();
        Person person1 = employeeBuilder.withName("Harry")
                .worksAt("Developer")
                .build();
        System.out.println(person1);
// so if you want to fluent interface to propagate across inheritance hierarchy
        // you need to have recursive generic definition
        // e.g class PersonBuilder<T extends PersonBuilder<T>>{..}
        // so every type of inheritance you stick that inheritar (EmployeeBuilder) as a Type argumnet of PersonBuilder
        // thereby propagating this idea of always returning a type reference
        // , a type of reference to most derived type that you're working with



    }
}


class Person {
    public String name;
    public String position;

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}

class PersonBuilder<T extends PersonBuilder<T>> {
    // why we have added a generic here, : to preserve the fluent interface,
    // as we need this builder returns PersonBuilder but now adding this Generic we
    // can say it returns anything which extends Personbuilder
    protected Person person;

    public PersonBuilder() {
        person = new Person();
    }

    // we are returning T
    public T withName(String name) {
        person.name = name;
        return t();
    }

    protected T t() {
        return (T) this;
    }

    public Person build() {
        return person;
    }
}

class EmployeeBuilder extends PersonBuilder<EmployeeBuilder> {

    public EmployeeBuilder worksAt(String position) {
        person.position = position;
        return t();
    }

    @Override
    protected EmployeeBuilder t() {
        return this;
    }
}