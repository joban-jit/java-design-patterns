package com.design.patterns.corepatterns.composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GeometricShapes {

    public static void main(String[] args) {
        GraphicObject drawing = new GraphicObject();
        drawing.setName("My Drawing");
        drawing.children.add(new Square("Red"));
        drawing.children.add(new Circle("Yellow"));

        GraphicObject group = new GraphicObject();
        group.children.add(new Circle("Blue"));
        group.children.add(new Square("Blue"));
        drawing.children.add(group);
        System.out.println(drawing);
    }
}

class GraphicObject{
    protected String name = "Group";

    public String color;
    public List<GraphicObject> children = new ArrayList<>();
    public GraphicObject() {

    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        print(sb, 0);
        return sb.toString();
    }
    private void print(StringBuilder sb, int depth) {
        sb.append(String.join("", Collections.nCopies(depth, "*")))
                .append(depth>0? " ": "")
                .append((color==null || color.isEmpty()) ? "": color+" ")
                .append(getName())
                .append(System.lineSeparator());
        for (GraphicObject child: children){
            child.print(sb, depth+1);
        }

    }
}
// in our below GraphicObject implementation (any).
// in all cases we are doing the recursive procedure through "print" method
// and print goes through all the children and it treats all the children uniformly
// because essentially GraphicObject is essentially either a Singular object as you can see in
// circle or Square which inherit it or it can be container of object, because it has this list of
// children
// And if you populate this list then when you printed for loop in print method will go through every single
// child and prints the childs as well
// so that's key of the composite design pattern.
// it is an ability to have objects which can be treated in both singluar manner as scalar objects or
// as collections of objects
class Circle extends GraphicObject {
    public Circle(String color){
        name = "Circle";
        this.color = color;
    }
}
class Square extends GraphicObject {
    public Square(String color){
        name = "Square";
        this.color = color;
    }
}
