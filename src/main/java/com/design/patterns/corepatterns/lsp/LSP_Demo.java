package com.design.patterns.corepatterns.lsp;

// Liskov Substitution Principle:
// we should be able to substitute sub class for a base class
// upon violating it could result incorrect code through inheritance
public class LSP_Demo {
    static void useIt(Rectangle r){
        int width = r.getWidth();
        r.setHeight(10);
        // area = should be width*10
        System.out.println("Expected area of "
        + (width*10) + ", got : "+r.getArea());
    }

    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2,3);
        useIt(rc);

        // ideally we should be able to use the same method for Square as well
        Square sq = new Square();
        sq.setWidth(5);
        useIt(sq);// Expected area of 50, got : 100
        // so the problem is the setter method in "useIt" method
        // which is fine for Rectangle but not Square because the way we
        // have implemented setter methods for "Square" class
        // use Factory pattern instead
    }
}

class Rectangle{
    protected int width, height;

    public Rectangle(){

    }

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getArea(){
        return width*height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}

class Square extends Rectangle{
    public Square(){

    }
    public Square(int size){
        width = height = size;
    }

    // here we have violated the Liskov Substitution Principle
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        super.setHeight(width);
    }

    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        super.setWidth(height);
    }
}
// solution: use factory pattern
class RectangleFactor{
    public static Rectangle newRectangle(int width, int height){
        return new Rectangle(width, height);
    }

    public static Rectangle newSquare(int side){
        return new Rectangle(side, side);
    }
}
