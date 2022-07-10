package com.design.patterns.corepatterns.singleton;

import java.io.*;

public class BasicSingleton implements Serializable {

    private BasicSingleton(){

    }

    private static final BasicSingleton INSTANCE = new BasicSingleton();

    public static BasicSingleton getInstance(){
        return INSTANCE;
    }

    private int value =0;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // serializable issue can be resolved by implementing below "readResolve" method.
    // it gives JVM hint when ever serialization happens, it has to happen in the context of this instance
    /// it has to happen into the instance as opposed to making you object
    protected Object readResolve(){
        return INSTANCE;
    }
}

class Demo{

    static void saveToFile(BasicSingleton singleton, String filename) throws Exception{
        ;
        try(FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream out = new ObjectOutputStream(fileOut)){
            out.writeObject(singleton);
        }
    }

    static BasicSingleton readFromFile(String filename) throws Exception{
        try(FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream in = new ObjectInputStream(fileIn)){
            return (BasicSingleton) in.readObject();
        }
    }
    public static void main(String[] args) throws Exception {
//         BasicSingleton singleton = BasicSingleton.getInstance();
//         singleton.setValue(123);
//        System.out.println(singleton.getValue());
        // problem with singleton
        // 1. Reflection
        // private constructor can be defeated by reflection; you can use reflection to get to that constructor
        // and actually call it to make it public effectively and create new instances.
        // in this approach developer knows that he/she would be defeating the private constructor/singleton


        // 2. serialization - developer doesn't know if he/she breaking the singleton
        // when you deserialize an object, JVM doesn't care if you have private constructor and that still
        // going to construct an object anyway

        BasicSingleton singleton = BasicSingleton.getInstance();
        singleton.setValue(111);

        String filename = "singleton.bin";
        saveToFile(singleton, filename);
        singleton.setValue(222);
        BasicSingleton singleton1 = readFromFile(filename);
        System.out.println(singleton1 == singleton); //false
        System.out.println(singleton.getValue()); // 222
        System.out.println(singleton1.getValue()); // 111 ; after implementing 'readResolve" method=222;





    }
}