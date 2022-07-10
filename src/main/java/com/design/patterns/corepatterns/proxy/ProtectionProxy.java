package com.design.patterns.corepatterns.proxy;

// it controls access to a particular resource while once again offering the same API.
public class ProtectionProxy {

    public static void main(String[] args) {
        Car car = new Car(new Driver(12));
        car.drive();
        Car carWithProxy = new CarProxy(new Driver(12));
        carWithProxy.drive();
    }
}

interface Drivable{
    void drive();
}

class Car implements Drivable{

    protected Driver driver;

    public Car(Driver driver) {
        this.driver = driver;
    }

    @Override
    public void drive() {
        System.out.println("Car being driven");
    }
}

class Driver{

    public int age;

    public Driver(int age) {
        this.age = age;
    }

}

// so we want to have something which behave like a Car but it actually verifies that driver is old enough to drive
// so we create a proxy
class CarProxy extends Car{
    public CarProxy(Driver driver){
        super(driver);
    }

    @Override
    public void drive() {
        if(driver.age>=15){
            super.drive();
        }else{
            System.out.println("Driver too young!");
        }
    }
}