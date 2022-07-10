package com.design.patterns.corepatterns.decorator;

public class DecoratorExercise {

    public static void main(String[] args) {
        Bird bird = new Bird();
        bird.setAge(11);
        System.out.println(bird.fly());

        Lizard lizard = new Lizard();
        lizard.setAge(11);
        System.out.println(lizard.crawl());

        Dragon dragon = new Dragon();
        dragon.setAge(5);
        System.out.println(dragon.fly());
        System.out.println(dragon.crawl());

    }
}

class Bird
{
    public int age;
    public void setAge(int age)
    {
        this.age = age;
    }

    public String fly()
    {
        return age < 10 ? "flying" : "too old";
    }
}

class Lizard
{
    public int age;
    public void setAge(int age)
    {
        this.age = age;
    }

    public String crawl()
    {
        return (age > 1) ? "crawling" : "too young";
    }
}

class Dragon
{
    private int age;
    public void setAge(int age)
    {
        this.age = age;
    }
    public String fly()
    {
        return age < 10 ? "flying" : "too old";
    }
    public String crawl()
    {
        return (age > 1) ? "crawling" : "too young";
    }
}
