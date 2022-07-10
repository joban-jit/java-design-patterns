package com.design.patterns.corepatterns.factory;

public class Factory_Demo {
    public static void main(String[] args) {
//        Point2 newCartesianPoint = Point2.newCartesianPoint(1, 3);
//        Point2 newPolarPoint = Point2.newPolarPoint(2, 30);
        Point2 newCartesianPoint = Point2.Factory.newCartesianPoint(1, 3);
        Point2 newPolarPoint = Point2.Factory.newPolarPoint(2, 30);

        System.out.println("Cartesian point: " + newCartesianPoint);
        System.out.println("Polar point: " + newPolarPoint);

    }
}


class Point2 {
    private double x, y;

    // made constructor private to force the users to use static methods to create desired objects.
    private Point2(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
// created this factory and made this inside this class as we want to keep constructor private;
    public static class Factory {

        public static Point2 newCartesianPoint(double x, double y) {
            return new Point2(x, y);
        }

        public static Point2 newPolarPoint(double rho, double theta) {
            return new Point2(rho * Math.cos(theta), rho * Math.sin(theta));

        }
    }
}


