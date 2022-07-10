package com.design.patterns.corepatterns.singleton;

import java.io.File;
import java.io.IOException;
// so if you have singleton constructor which can actually throw something, then you have to slightly
// change the approach like use of static block and try catch block,
public class StaticBlockSingleton {


    // now if I want to initialize a singleton, I can no longer have a private final static value

    private StaticBlockSingleton() throws IOException {
        System.out.println("Singleton is initializing");
        // invalid code
        File.createTempFile(".", ".");
    }
    private static StaticBlockSingleton instance;
    // instead I've to create a static block
    static{
        try{
            instance = new StaticBlockSingleton();
        }catch (Exception e){
            System.err.println("failed to create singleton");
        }
    }

    public static StaticBlockSingleton getInstance(){
        return instance;
    }
}
