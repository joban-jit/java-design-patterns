package com.design.patterns.corepatterns.singleton;

import java.io.*;
import java.util.EnumMap;

enum EnumBasedSingleton {
    // no problems with reflection
    // serializable by default.- but not the kind of serialization we used before that will let you preserve
    // state of singleton
    // and there are other concerns like you can't inherit it and there is no way of inheriting.
    // so use this if you don't need any state to be persisted, means
    // let's suppose you have getter and setters of that value
    INSTANCE;

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // enum already have private default constructor so if you don't add any constructor here it is no problem
    // as people  can't make instances of enum anyway
    // but you can make a constructor and you don't even have to add private keyword here
    EnumBasedSingleton() {
        this.value = 42;
    }
    // problem with this approach , if you serialize this enum, the value we have here (42) won't be serialized,
    // because when you serialize an enum even though they're implicitly serializable, you don't have to implement
    // Serializable interface
    // Fields value aren't serialized , only names of field INSTANCE is serialized. So that's can lead to a really confusing kind
    // of problem


}


class Demo_enum{
    static void saveToFile(EnumBasedSingleton singleton, String filename) throws Exception {
        try(FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut)){
            out.writeObject(singleton);
        }
    }

    static EnumBasedSingleton readFromFile(String filename) throws Exception{
        try(FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(fileIn)){
            return (EnumBasedSingleton) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception{
        String filename = "myfile.bin";
//        EnumBasedSingleton singleton = EnumBasedSingleton.INSTANCE;
//        singleton.setValue(111);
//        saveToFile(singleton , filename);
//
        EnumBasedSingleton singleton1 = readFromFile(filename);
        System.out.println(singleton1.getValue()); // 111, if we don't use singleton.setValue and saveToFile...means you
        // not serialize , then you get correct value 42.
        // but constructor has value  = 42



    }
}


