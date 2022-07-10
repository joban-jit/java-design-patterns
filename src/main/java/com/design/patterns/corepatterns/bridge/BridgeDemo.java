package com.design.patterns.corepatterns.bridge;

public class BridgeDemo {
    public static void main(String[] args) {
        RasterRenderer raster = new RasterRenderer();
        VectorRenderer vector = new VectorRenderer();
        Circle circle = new Circle(vector, 5);
        circle.draw();
        circle.resize(2);
        circle.draw();
        // this approach might seems a bit clunky, I mean you have to keep adding the renderer in all
        // sorts of locations

        /**
         * Imagine you have a very complicated application and you want to supply a single renderer to
         * every object that's being constructed.
         * so we can use proper dependency injection , I haven't implemented that.
         */
    }
}

interface Renderer {
    void renderCircle(float radius);
}

class VectorRenderer implements Renderer {
    @Override
    public void renderCircle(float radius) {
        System.out.println("Drawing a circle of radius "
                + radius);
    }
}

class RasterRenderer implements Renderer {
    @Override
    public void renderCircle(float radius) {
        System.out.println("Drawing pixels for a circle "
                + "of radius " + radius);
    }
}

abstract class Shape {
    protected Renderer renderer;
    public Shape(Renderer renderer) {
        this.renderer = renderer;
    }
    public abstract void draw();
    public abstract void resize(float factor);
}

class Circle extends Shape {
    public float radius;
    public Circle(Renderer renderer) {
        super(renderer);
    }
    public Circle(Renderer renderer, float radius) {
        super(renderer);
        this.radius = radius;
    }
    @Override
    public void draw() {
        renderer.renderCircle(radius);
    }
    @Override
    public void resize(float factor) {
        radius *= factor;
    }
}