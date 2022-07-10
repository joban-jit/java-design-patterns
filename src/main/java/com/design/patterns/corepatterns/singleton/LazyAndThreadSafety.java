package com.design.patterns.corepatterns.singleton;

public class LazyAndThreadSafety {
}

class InnerStaticSingleton{
    private InnerStaticSingleton() {

    }
    // this approach avoid the problem with synchronization and it guarantees effectively that
    // whenever you initialize the instance , you are only going to get one instance (because it is final), no extra need
    // for thread safety
    private static class Impl{
        // as we know inner classes can access private members of outer class
        // so we can access private constructor of outer class
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();

    }
    public InnerStaticSingleton getInstance(){
        return Impl.INSTANCE;
    }
}

class LazySingleton{
    private static LazySingleton instance;

    private LazySingleton(){
        System.out.println("Initializing a lazy singleton");

    }
    // Problem with this is that it raises race condition if several threads accessing this getInstance method
    // and creating new object. e.g. if thread 1 and 2 check at sametime the null condition and found instance = null
    // then both thread 1 & 2 creates new instances , so would have two instances

    // 1. one way to fix this above problem is use "synchronized" keyword, but it has performance issue
    // public static synchronized LazySingleton getInstance(){
    public static synchronized LazySingleton getInstance(){
        // only creating if instance is null: lazy

        if(instance == null){
            instance = new LazySingleton();
        }
        return instance;
    }
    // 2. (outdated) double-checked locking - not recommended; just for education purposes.
//    public static LazySingleton getInstance() {
//
//        if (instance == null) {
//            synchronized (LazySingleton.class){
//                if(instance == null){
//                    instance = new LazySingleton();
//                }
//            }
//        }
//        return instance;
//    }
}
