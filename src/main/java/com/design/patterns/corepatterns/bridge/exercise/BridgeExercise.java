package com.design.patterns.corepatterns.bridge.exercise;

public class BridgeExercise {
    public static void main(String[] args) {
        Triangle triangle = new Triangle(new RasterRenderer());
        System.out.println(triangle.toString());
        Triangle triangle1 = new Triangle(new VectorRenderer());
        System.out.println(triangle1.toString());


    }


}

interface Renderer{
    String whatToRenderAs();
}

class VectorRenderer implements Renderer{
    @Override
    public String whatToRenderAs() {
        return "lines";
    }
}

class RasterRenderer implements Renderer{
    @Override
    public String whatToRenderAs() {
        return "pixels";
    }
}

abstract class Shape
{
    protected Renderer renderer;

    protected Shape(Renderer renderer){
        this.renderer = renderer;
    }
    public abstract String getName();
}

class Triangle extends Shape
{
    public Triangle(Renderer renderer){
        super(renderer);
    }

    @Override
    public String getName()
    {
        return "Triangle";
    }

    @Override
    public String toString() {
        return "Drawing "+getName()+" as "+renderer.whatToRenderAs();
    }
}

class Square extends Shape
{
    public Square(Renderer renderer){
        super(renderer);
    }
    @Override
    public String getName()
    {
        return "Square";
    }

    @Override
    public String toString() {
        return "Drawing "+getName()+" as "+renderer.whatToRenderAs();
    }


}

// does not need below code
class VectorSquare extends Square
{
    public VectorSquare(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String toString()
    {
        return String.format("Drawing %s as lines", getName());
    }
}

class RasterSquare extends Square
{
    public RasterSquare(Renderer renderer) {
        super(renderer);
    }

    @Override
    public String toString()
    {
        return String.format("Drawing %s as pixels", getName());
    }
}

// imagine VectorTriangle and RasterTriangle are here too
