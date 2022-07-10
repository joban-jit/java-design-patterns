package com.design.patterns.corepatterns.ocp;

import java.util.List;
import java.util.stream.Stream;

// Open closed Principle + Specification
// you should not be opening any code which is already written: open for extemsion but closed
// for modification
public class OCP_Demo {
    public static void main(String[] args) {
       //"./src/main/java/com/design/patterns/corepatterns/srp/journal.txt";

        Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
        Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
        Product house = new Product("House", Color.BLUE, Size.LARGE);

        List<Product> products = List.of(apple, tree, house);
        ProductFilter pf = new ProductFilter();
        System.out.println("Green products (old): ");
        pf.filterByColor(products, Color.GREEN).forEach(p->
                System.out.println(" - "+p.name + " is green"));

        BetterFilter bf = new BetterFilter();
        System.out.println("Green products (new): ");
        bf.filter(products, new ColorSpecification(Color.GREEN))
                .forEach(p->
                        System.out.println(" - "+p.name + " is green"));

        System.out.println("Large blue items: ");
        bf.filter(products, new AndSpecification<>(
                new ColorSpecification(Color.BLUE),
                new SizeSpecification(Size.LARGE)
        )).forEach(p->
                System.out.println(" - "+p.name + " is large and blue"));

    }
}

enum Color{
    RED,
    GREEN,
    BLUE;
}
enum Size{
    SMALL,
    MEDIUM,
    LARGE,
    HUGE;
}
class Product
{
    public String name;
    public Color color;
    public Size size;

    public Product(String name, Color color, Size size) {
        this.name = name;
        this.color = color;
        this.size = size;
    }
}
// here we are adding the filters based on changing requirements, but if we introduce another
// parameter apart from color, size let's say price we might have to change the code to filter
// on basis of price as well, so we are here modifying the code
class ProductFilter{
    public Stream<Product> filterByColor(List<Product> products, Color color){
        return products.stream().filter(p->p.color==color);
    }

    public Stream<Product> filterBySize(List<Product> products, Size size){
        return products.stream().filter(p->p.size==size);
    }

    public Stream<Product> filterByColorAndSize(List<Product> products,
                                                Color color,
                                                Size size){
        return products.stream().filter(p->p.color==color).filter(p->p.size == size);
    }
}

// How to avoid that : modifying the existing class
interface Specification<T>{
    boolean isSatisfied(T item);
}

interface Filter<T>{
    Stream<T> filter(List<T> items, Specification<T> spec);
}

class ColorSpecification implements Specification<Product>{
    private Color color;

    public ColorSpecification(Color color) {
        this.color = color;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.color == color;
    }
}
class SizeSpecification implements Specification<Product>{
    private Size size;

    public SizeSpecification(Size size) {
        this.size = size;
    }

    @Override
    public boolean isSatisfied(Product item) {
        return item.size == size;
    }
}

class AndSpecification<T> implements Specification<T>{

    private Specification<T> first, second;

    public AndSpecification(Specification<T> first, Specification<T> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(T item) {
        return first.isSatisfied(item) && second.isSatisfied(item);
    }
}

class BetterFilter implements Filter<Product>{

    @Override
    public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
//        return items.stream().filter(p->spec.isSatisfied(p));
        return items.stream().filter(spec::isSatisfied);
    }
}
