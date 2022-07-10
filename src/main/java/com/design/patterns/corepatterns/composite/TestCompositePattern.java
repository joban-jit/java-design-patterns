package com.design.patterns.corepatterns.composite;

import java.util.ArrayList;
import java.util.List;

public class TestCompositePattern {
    public static void main(String[] args) {
        Shape tri = new Triangle();
        Shape tri1 = new Triangle();
        Shape cir = new Circle1();

        Drawing drawing = new Drawing();
        drawing.add(tri);
        drawing.add(tri1);
        drawing.add(cir);

        drawing.draw("Red");
        drawing.clear();
        drawing.add(tri);
        drawing.add(cir);

        drawing.draw("Green");

    }
}

interface Shape{
    void draw(String fillColor);
}

class Triangle implements Shape{
    @Override
    public void draw(String fillColor) {
        System.out.println("Drawing Triangle with color: "+fillColor);

    }
}
class Circle1 implements Shape{
    @Override
    public void draw(String fillColor) {
        System.out.println("Drawing Circle with color: "+fillColor);

    }
}


class Drawing implements Shape{
    // collections of shapes
    private List<Shape> shapes = new ArrayList<>();

    @Override
    public void draw(String fillColor) {
        shapes.forEach(s -> s.draw(fillColor));
    }
    // adding shape to drawing
    public void add(Shape s){
        this.shapes.add(s);
    }
    // removing shape from drawing
    public void remove(Shape s){
        shapes.remove(s);
    }
    // removing all the shapes
    public void clear(){
        System.out.println("Clearing all the shapes ");
        this.shapes.clear();
    }
}