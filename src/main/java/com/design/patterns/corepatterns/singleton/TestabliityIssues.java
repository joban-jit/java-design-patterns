package com.design.patterns.corepatterns.singleton;

import com.google.common.collect.Iterables;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class TestabliityIssues {
}
interface Database{
    int getPopulation(String name);
}
class SingletonDatabase implements  Database{
    private Map<String, Integer> capitals = new HashMap<>();
    private static int instanceCount = 0;
    public static int getCount(){
        return instanceCount;
    }

    private SingletonDatabase(){
        instanceCount++;
        System.out.println("Initialization database");
        try{
            // getting the path of this class
            File file = new File(
                    SingletonDatabase.class.getProtectionDomain().getCodeSource().getLocation().getPath()
            );
            // getting the path of capitals.txt file using path of this class which we got from previous step
            Path fullPath = Paths.get(file.getPath(), "capitals.txt");
            List<String> lines = Files.readAllLines(fullPath);
            // below method would split e.g [a, b, c, d,e, f] - > [ [a,b], [c,d], [e,f]]
            Iterable<List<String>> partitionList = Iterables.partition(lines, 2);
            partitionList.forEach(kv -> capitals.put(
                    kv.get(0).trim(),
                    Integer.parseInt(kv.get(1).trim())
            ));

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    private static final SingletonDatabase INSTANCE = new SingletonDatabase();
    public static SingletonDatabase getInstance(){
        return INSTANCE;
    }
    @Override
    public int getPopulation(String name){
        return capitals.get(name);
    }
}


class ConfigurableRecordFinder{
    private Database database;

    public ConfigurableRecordFinder(Database database) {
        this.database = database;
    }

    public int getTotalPopulation(List<String> names){
        int result = 0;
        for(String name : names){
            result+=database.getPopulation(name);
        }
        return result;
    }
}


class SingletonRecordFinder {
    public int getTotalPopulation(List<String> names) {
        int result = 0;
        for (String name : names) {
            result += SingletonDatabase.getInstance().getPopulation(name);

        }
        return result;
    }
}
