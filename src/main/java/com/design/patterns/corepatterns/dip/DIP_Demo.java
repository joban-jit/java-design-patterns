package com.design.patterns.corepatterns.dip;


// DIP: Dependency Inversion Principle
// not same as Dependency Injection:

// Definition of DI:

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.javatuples.Triplet;

/**
 * A. High-level modules should not depend on low-level modules.
 * Both should depend on abstractions.
 *
 * B. Abstractions should not depend on details.
 * Details should depend on abstractions.
 *
 *
 *  Abstraction: abstract class or interface - you get the signature, but not concrete implementation
 *  High-Level
 */
public class DIP_Demo {

    public static void main(String[] args) {
        Person parent = new Person("John");
        Person child1 = new Person("Chris");
        Person child2 = new Person("Matt");

        Relationships relationships = new Relationships();
        relationships.addParentAndChild(parent, child1);
        relationships.addParentAndChild(parent, child2);

        new Research(relationships);
    }
}

enum Relations{
    PARENT,
    CHILD,
    SIBLING
}

class Person
{
    public String name;

    public Person(String name){
        this.name = name;
    }
}

class Relationships
 implements RelationshipBrowser // here we have implemented this to add abstraction
{ // low-level implementation: close to data layer
    private List<Triplet<Person, Relations, Person>> relationList
             = new ArrayList<>();

    public List<Triplet<Person, Relations, Person>> getRelationList() {
        return relationList;
    }

    public void addParentAndChild(Person parent, Person child){
        relationList.add(new Triplet<>(parent, Relations.PARENT, child));
        relationList.add(new Triplet<>(child, Relations.CHILD, parent));
    }

    @Override
    public List<Person> findAllChildrenOf(String name) {
        return relationList.stream()
                .filter(x->x.getValue0().name.equals(name) && x.getValue1()==Relations.PARENT)
                .map(t->t.getValue2())
                .collect(Collectors.toList());
    }
}

class Research{// high level
    // ISSUE:
   /* public Research(Relationships relationships){

        // Problem: here we are exposing the relationsList to public using getRelationList() method;

        // and here high level module depends on low-level module.
        // so to fix it use Abstraction. e.g. RelationshipBrowser
        List<Triplet<Person, Relations, Person>> relationList = relationships.getRelationList();
        relationList.stream()
                .filter(x->x.getValue0().name.equals("John") && x.getValue1()==Relations.PARENT)
                .forEach(x-> System.out.println(
                        "John has a child called " + x.getValue2().name
                ));
    }*/

    // Solution:
    public Research(RelationshipBrowser browser){
        List<Person> relationsList = browser.findAllChildrenOf("John");
        relationsList.stream()
                .forEach(x-> System.out.println(
                        "John has a child called " + x.name
                ));
    }
}

interface RelationshipBrowser{
    List<Person> findAllChildrenOf(String name);
}