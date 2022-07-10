package com.design.patterns.corepatterns.factory;

public class FactoryMethod_Demo {

    public static void main(String[] args) {
        Point newCartesianPoint = Point.newCartesianPoint(1, 3);
        Point newPolarPoint = Point.newPolarPoint(2, 30);

        System.out.println("Cartesian point: "+newCartesianPoint);
        System.out.println("Polar point: "+newPolarPoint);

    }
}
// Using Factory Method
class Point{
    private double x,y;
    // made constructor private to force the users to use static methods to create desired objects.
    private Point(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public static Point newCartesianPoint(double x, double y){
        return new Point(x,y);
    }
    public static Point newPolarPoint(double rho, double theta){
        return new Point(rho*Math.cos(theta),rho*Math.sin(theta));

    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}


enum CoordinateSystem{
    CARTESIAN,
    POLAR
}
// problem here is you need to specify what are a, b , cs are in some documentation and constructor looks ugly
class Point_old2{
    private double x,y;

    public Point_old2(double a, double b, CoordinateSystem cs){
        switch(cs){
            case CARTESIAN -> {
                this.x = a;
                this.y = b;
            }
            case POLAR -> {
                x = a*Math.cos(b);
                y = a*Math.sin(b);
            }

        }
    }
}


class Point_old1{
    private double x, y;

    public Point_old1(double x, double y) {
        this.x = x;
        this.y = y;
    }
// let say you want to create another way to create an object using Polar cooridates
    // you can't do below in Java.
//    public Point(double rho, double theta){
//        x = rho*Math.cos(theta);
//        y = rho*Math.sin(theta);
//    }
}
