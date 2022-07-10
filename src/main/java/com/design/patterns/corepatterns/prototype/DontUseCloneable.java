package com.design.patterns.corepatterns.prototype;

import java.util.Arrays;

// implementing the Cloneable doesn't guarantee it will be Shallow or Deep Copy
// default behaviour of cloneable is shallow copying.
// when you copy reference it is called shallow copying...but do look up this shallow copy


public class DontUseCloneable {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person john = new Person(new String[]{"John", "Smith"},
                new Address("London Road", 143)
        );

        Person jane = (Person) john.clone();
        jane.names[0] = "Jane";
        System.out.println(john);
        System.out.println(jane);
    }
}

class Address implements Cloneable{
    public String streetName;
    public int houseNumber;

    public Address(String streetName, int houseNumber) {
        this.streetName = streetName;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // this is deep copy
        return new Address(streetName, houseNumber);
    }
}

class Person implements Cloneable {
    public String [] names;
    public Address address;

    public Person(String[] names, Address address) {
        this.names = names;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Person{" +
                "names=" + Arrays.toString(names) +
                ", address=" + address +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException{
        return new Person(
                names.clone(), // Array has clone method.
                (Address) address.clone()
        );
    }
}

