package com.design.patterns.corepatterns.prototype;

public class CopyConstructor {
    public static void main(String[] args) {
        Employee john = new Employee(
                "John",
                new Address1("123 London Road", "London", "UK"));
        Employee chris = new Employee(john);
        chris.name = "Chris";
        System.out.println(john);
        System.out.println(chris);
    }

}

 class Address1{
    public String streetAddress, city, country;

     public Address1(String streetAddress, String city, String country) {
         this.streetAddress = streetAddress;
         this.city = city;
         this.country = country;
     }

     // use of copy constructor
     public Address1(Address1 other){

         this(other.streetAddress, other.city, other.country);
     }

     @Override
     public String toString() {
         return "Address1{" +
                 "streetAddress='" + streetAddress + '\'' +
                 ", city='" + city + '\'' +
                 ", country='" + country + '\'' +
                 '}';
     }
 }

 class Employee{
    public String name;
    public Address1 address1;

     public Employee(String name, Address1 address1) {
         this.name = name;
         this.address1 = address1;
     }
    // use of copy constructor
     public Employee(Employee other){
         name = other.name;
         address1 = new Address1(other.address1);
     }

     @Override
     public String toString() {
         return "Employee{" +
                 "name='" + name + '\'' +
                 ", address1=" + address1 +
                 '}';
     }
 }
