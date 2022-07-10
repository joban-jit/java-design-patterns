package com.design.patterns.corepatterns.bridge.easyExample;

public class BridgeDemo {
    public static void main(String[] args) {
        Triangle triangle = new Triangle(new RedColor());
        triangle.drawShapeWithColor();//Triangle filled with color red.

        Triangle triangle1 = new Triangle(new GreenColor());
        triangle1.drawShapeWithColor();//Triangle filled with color green.

        Pentagon pentagon = new Pentagon(new GreenColor());
        pentagon.drawShapeWithColor();//Pentagon filled with color green.
    }

}


interface Color {
    void applyColor();
}

abstract class Shape {
    protected Color color;

    protected Shape(Color color) {
        this.color = color;
    }

    public abstract void drawShapeWithColor();
}

class Triangle extends Shape {
    public Triangle(Color color) {
        super(color);
    }

    @Override
    public void drawShapeWithColor() {
        System.out.print("Triangle filled with color ");
        color.applyColor();
    }
}

class Pentagon extends Shape {

    public Pentagon(Color c) {
        super(c);
    }

    @Override
    public void drawShapeWithColor() {
        System.out.print("Pentagon filled with color ");
        color.applyColor();
    }

}

class RedColor implements Color {
    @Override
    public void applyColor() {
        System.out.println("red.");
    }
}

class GreenColor implements Color {

    public void applyColor() {
        System.out.println("green.");
    }
}


