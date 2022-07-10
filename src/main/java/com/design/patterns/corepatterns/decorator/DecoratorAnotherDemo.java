package com.design.patterns.corepatterns.decorator;

public class DecoratorAnotherDemo {
    public static void main(String[] args) {
        SportsCar sportsCar = new SportsCar(new BasicCar());
        sportsCar.assemble();//Basic Car. Adding features of Sports Car.
        System.out.println("\n***");
        LuxuryCar luxurySportCar = new LuxuryCar(new SportsCar(new BasicCar()));
        luxurySportCar.assemble();//Basic Car. Adding features of Sports Car. Adding features of Luxury Car.
    }
}

// Component interface
interface Car{
    void assemble();
}
// component implementation
class BasicCar implements Car{
    @Override
    public void assemble() {
        System.out.print("Basic Car.");
    }
}
//Decorator – Decorator class implements the component interface and it has a HAS-A relationship with the component interface.
//        The component variable should be accessible to the child decorator classes, so we will make this variable protected.
abstract class CarDecorator implements Car{

    protected Car car;
    public CarDecorator(Car car){
        this.car  = car;
    }
    @Override
    public void assemble() {
        this.car.assemble();
    }
}

//    Concrete Decorators – Extending the base decorator functionality and
//    modifying the component behavior accordingly.
//        We can have concrete decorator classes as LuxuryCar and SportsCar.
class SportsCar extends CarDecorator{
    public SportsCar(Car c){
        super(c);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.print(" Adding features of Sports Car.");
    }
}

class LuxuryCar extends CarDecorator{
    public LuxuryCar(Car c){
        super(c);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.print(" Adding features of Luxury Car.");
    }
}


