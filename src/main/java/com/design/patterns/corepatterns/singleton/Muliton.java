package com.design.patterns.corepatterns.singleton;

import java.util.HashMap;

// it has finite number of instances - restricting number of instances created
public class Muliton {
    public static void main(String[] args) {
        Printer main = Printer.get(SubSystem.PRIMARY);
        Printer aux = Printer.get(SubSystem.AUXILIARY);
        Printer aux1 = Printer.get(SubSystem.AUXILIARY); // won't create a instance as instance is already created in above line

    }
}

enum SubSystem{
    PRIMARY,
    AUXILIARY,
    FALLBACK
}
class Printer{
    private static int instanceCount = 0;
    private Printer(){
        instanceCount++;
        System.out.println("A total of "+ instanceCount + " instances created so far.");
    }
    private static HashMap<SubSystem, Printer> instances = new HashMap<>();
    public static Printer get(SubSystem ss){
        if(instances.containsKey(ss)){
            return instances.get(ss);
        }else{
            Printer instance = new Printer();
            instances.put(ss, instance);
            return instance;
        }

    }
}
