package com.design.patterns.corepatterns.adapter;

import java.util.*;
import java.util.function.Consumer;

// eg. we want to draw a picture in form of vector form or in pixels (raster)
public class Vector_caching {
//    private static final List<VectorObject> vectorObjects = new ArrayList<>(
//            Arrays.asList(new VectorRectangle(1,1,10,10),
//                    new VectorRectangle(3,3,6,6))
//    );
    private static final List<VectorObject> vectorObjects= List.of(new VectorRectangle(1,1,10,10),
                    new VectorRectangle(3,3,6,6));

// we don't have way to convert vector to point so we need to build an adapter
    // we have only one api which uses points to draw.
    public static void drawPoint(Point p){
        System.out.println(".");
    }

    private static void draw(){
        //(in case if still confused what is VectorOject) below for loops proves the point that vectorObjects here we are using is infact list of list
        // so proving VectorObject is infact a subclass of list.
        vectorObjects.forEach( vo->{
            vo.forEach(line->{
                LineToPointAdapter adapter = new LineToPointAdapter(line); // this adapter again is subclass of arraylist
                // of lines.
                adapter.forEach( point-> drawPoint(point));
            });
        });
    }
    public static void main(String[] args) {
        draw();
        draw();
        // so if we call the draw() method again then we would creating those points again..kinda double the work
        // so to avoid this - create a cache
        // here in this we have
        // implemented hashcode and equals methods in Point and Line so we don't create same point and line with same hash
        // and used HashMap to store cache
    }
}

class Point{
    public int x,y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31*result+y;
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

// vector objects consists of lines - multiple lines so
// that's why we created class extended with Arraylist of lines
// now VectorObject is sub class of Arraylist and will inherit all the members/methods of Arraylist
// like add() method and so on.
class VectorObject extends ArrayList<Line> {

}

// this class is vectorRectangle as we are creating rectangle using lines not points
// so we'll extend VectorObject as VectorRectangle is child class of VectorObject.

class VectorRectangle extends VectorObject{
    // here we creating multiple lines and adding them to VectorObject list

    public VectorRectangle(int x, int y, int width, int height){
        add(new Line(new Point(x,y), new Point(x+width, y)));
        add(new Line(new Point(x+width, y), new Point(x+width, y+height)));
        add(new Line(new Point(x,y), new Point(x, y+height)));
        add(new Line(new Point(x,y+height), new Point(x+width, y+height)));
    }
}
class Line{
    public Point start, end;

    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        if (start != null ? !start.equals(line.start) : line.start != null) return false;
        return end != null ? end.equals(line.end) : line.end == null;
    }

    @Override
    public int hashCode() {
        int result = start != null ? start.hashCode() : 0;
        result = 31 * result + (end != null ? end.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Line{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }
}

// takes a line and convert in a bunch of points
class LineToPointAdapter implements Iterable<Point> {

    // here we are generating temp information and we are storing in arraylist
    private static int count = 0;

//    Integer here indicate hascode of line we are mapping
    // List<point> represent a line
    private static Map<Integer, List<Point>> cache= new HashMap<>();
    private int hash;
    public LineToPointAdapter(Line line){
        hash = line.hashCode();
        if(cache.get(hash)!=null)
            return;
        System.out.println(
                String.format("%d: Generating points for line [%d,%d]-[%d,%d] (with caching)",
                        ++count, line.start.x, line.start.y, line.end.x, line.end.y));
        ArrayList<Point> points = new ArrayList<>();
        int left = Math.min(line.start.x, line.end.x);
        int right = Math.max(line.start.x, line.end.x);
        int top = Math.min(line.start.y, line.end.y);
        int bottom = Math.max(line.start.y, line.end.y);
        int dx = right - left;
        int dy = line.end.y - line.start.y;

        if (dx == 0)
        {
            for (int y = top; y <= bottom; ++y)
            {
                points.add(new Point(left, y));
            }
        }
        else if (dy == 0)
        {
            for (int x = left; x <= right; ++x)
            {
                points.add(new Point(x, top));
            }
        }
        cache.put(hash, points);
    }

    @Override
    public Iterator<Point> iterator() {
        return cache.get(hash).iterator();
    }

    @Override
    public void forEach(Consumer<? super Point> action) {
        cache.get(hash).forEach(action);
    }

    @Override
    public Spliterator<Point> spliterator() {
        return cache.get(hash).spliterator();
    }
}
