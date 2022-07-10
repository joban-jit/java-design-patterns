package com.design.patterns.corepatterns.srp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
// Demo for Single Responsibility Principle(SRP) - one class should do only one thing.
public class SRP_Demo {
    public static void main(String[] args) throws FileNotFoundException {
        Journal j = new Journal();
        j.addEntry("I cried today");
        j.addEntry("I ate a bug");
        System.out.println(j);

        Persistence p = new Persistence();
        String filename = "./src/main/java/com/design/patterns/corepatterns/srp/journal.txt";
        p.saveToFile(j, filename, true);


    }
}

class Journal{
    private final List<String> entries = new ArrayList<>();
    private static int count = 0;

    public void addEntry(String text){
        entries.add(" " + (++count)+": "+text);
    }

    public void removeEntry(int index){
        entries.remove(index);
    }

    // here below we are voilating the SRP as we are using this class to
    // do other tasks i.e. save to some file, load from file, or URL,
    // so what we can do is move these functionalities to other class
    // e.g. Persistance class below
//    public void save(String filename) throws FileNotFoundException {
//        try(PrintStream out = new PrintStream(filename)){
//            out.println(toString());
//        }
//    }
//
//    public void load(String filename){
//
//    }
//
//    public void load(URL url){
//
//    }

    @Override
    public String toString() {
        return String.join(System.lineSeparator(), entries);
    }
}

class Persistence{
    public void saveToFile(Journal journal,
                           String filename,
                           boolean overwrite) throws FileNotFoundException {
        if (overwrite || new File(filename).exists()) {
                try(PrintStream out = new PrintStream(filename)) {
                    out.println(journal.toString());
                }
        }
    }
}
