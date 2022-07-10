package com.design.patterns.corepatterns.decorator.toAvoidConflicit;

import java.util.function.Supplier;

public class StaticDecoratorComposition {
    public static void main(String[] args) {
        Circle circle = new Circle(10);
        System.out.println(circle.info());
        ColoredShape<Square> blueSquare = new ColoredShape<>(()->new Square(20), "blue");
        System.out.println(blueSquare.info());
        // it is called Dynamic Decorator as in below we are building decorator at runtime..
        TransparentShape<ColoredShape> myCircle
                = new TransparentShape<>(
                ()->new ColoredShape<Circle>(
                        ()->new Circle(5),
                        "green"),
                50);
        System.out.println(myCircle.info());
        // here as well we can do
        // myCircle.resize() - as we still do have access to underlying api's
    }
}
interface Shape{
    String info();
}

class Circle implements Shape {

    private float radius;

    public Circle(){

    }
    public Circle(float radius) {
        this.radius = radius;
    }

    void resize(float factor){
        radius*=factor;
    }

    @Override
    public String info() {
        return "A circle of radius "+radius;
    }
}

class Square implements Shape {
    private float side;

    public Square() {
    }

    public Square(float side) {
        this.side = side;
    }

    @Override
    public String info() {
        return "Square with side "+side;
    }
}

// to add color
class ColoredShape<T extends Shape> implements Shape {

    private Shape shape;
    private String color;

    public ColoredShape(Supplier<? extends T> construct, String color) {
        this.shape = construct.get();
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info() + " has the color "+color;
    }
}

class TransparentShape<T extends Shape> implements Shape {
    private Shape shape;
    private int transparency;

    public TransparentShape(Supplier<? extends T> construct, int transparency) {
        this.shape = construct.get();
        this.transparency = transparency;
    }

    @Override
    public String info() {
        return shape.info()+" has transparency level: "+transparency+ "%";
    }
}
