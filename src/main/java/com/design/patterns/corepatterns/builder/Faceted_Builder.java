package com.design.patterns.corepatterns.builder;

public class Faceted_Builder {
    public static void main(String[] args) {
       PersonBuilder1 pb = new PersonBuilder1();
       Person1 person1 = pb
               .lives().at("123 London Road").in("London").withPostcode("SW12BC")
               .works().at("Fabrikam").asA("Engineer").earning(123000)
               .build();

        System.out.println(person1);
    }
}

class Person1{
    // address
    public String streetAddress, postcode, city;

    // employment
    public String companyName, position;
    public int annualIncome;

    @Override
    public String toString() {
        return "Person1{" +
                "streetAddress='" + streetAddress + '\'' +
                ", postcode='" + postcode + '\'' +
                ", city='" + city + '\'' +
                ", companyName='" + companyName + '\'' +
                ", position='" + position + '\'' +
                ", annualIncome=" + annualIncome +
                '}';
    }
}
// q: why do the sub builders have to inhert from base class builder?
// ans: As soon as they inherit from the base class builder, they both expose the works() and lives()
// methods , which means that you can switch from one sub builder to another sub builder inside a single
// fluent api call
// builder facade
class PersonBuilder1{
    protected Person1 person1 = new Person1();
// usage of sub builders , as both extends PersonBuilder1 so return type of both build() method is "Person1"
    public PersonAddressBuilder lives(){
        return new PersonAddressBuilder(person1);
    }

    public PersonJobBuilder works(){
        return new PersonJobBuilder(person1);
    }

    public Person1 build(){
        return person1;
    }
}

class PersonAddressBuilder extends PersonBuilder1{

    public PersonAddressBuilder(Person1 person1){
        this.person1 = person1;
    }

    public PersonAddressBuilder at(String streetAddress){
        person1.streetAddress = streetAddress;
        return this;
    }

    public PersonAddressBuilder withPostcode(String postcode){
        person1.postcode = postcode;
        return this;
    }

    public PersonAddressBuilder in(String city){
        person1.city = city;
        return this;
    }
}
class PersonJobBuilder extends PersonBuilder1{

    public PersonJobBuilder(Person1 person1){
        this.person1 = person1;
    }

    public PersonJobBuilder at(String companyName){
        person1.companyName = companyName;
        return this;
    }

    public PersonJobBuilder asA(String position){
        person1.position = position;
        return this;
    }

    public PersonJobBuilder earning(int annualIncome){
        person1.annualIncome = annualIncome;
        return this;
    }
}



