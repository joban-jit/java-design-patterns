package com.design.patterns.corepatterns.factory;

import org.javatuples.Pair;
import org.reflections.Reflections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AbstractFactory_Demo {

    public static void main(String[] args) throws IOException {
        HotDrinkMachine machine = new HotDrinkMachine();
        HotDrink drink = machine.makeDrink();
        drink.consume();
    }

}

interface HotDrink{
    void consume();
}

class Tea implements HotDrink{
    @Override
    public void consume() {
        System.out.println("This tea is delicious");
    }
}
class Coffee implements HotDrink{
    @Override
    public void consume() {
        System.out.println("This Coffee is delicious");
    }
}

interface HotDrinkFactory{
    HotDrink prepare(int amount);
}

class TeaFactory implements HotDrinkFactory{
    @Override
    public HotDrink prepare(int amount) {
        System.out.println(
                "Put in tea bag, boil water, pour "+amount+"ml, add lemon, enjoy!"
        );
        return new Tea();
    }
}
class CoffeeFactory implements HotDrinkFactory{
    @Override
    public HotDrink prepare(int amount) {
        System.out.println(
                "Grind some beans, boil water, pour "+amount+"ml, add cream and sugar, enjoy!"
        );
        return new Coffee();
    }
}

class HotDrinkMachine {
    private List<Pair<String, HotDrinkFactory>> namedFactories = new ArrayList<>();

    public HotDrinkMachine() {
        // find the inheritance for HotDrinkFactory interface and stick instances inside list
        Set<Class<? extends HotDrinkFactory>> types =
                new Reflections("com.design.patterns.corepatterns.factory").getSubTypesOf(HotDrinkFactory.class);

        types.forEach(type -> {
            try {
                System.out.println(type);
                namedFactories.add(Pair.with(
                        type.getSimpleName().replace("Factory", ""),
                        type.getDeclaredConstructor().newInstance()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    public HotDrink makeDrink() throws IOException {
        System.out.println("Available drinks: ");
        for (int index = 0; index < namedFactories.size(); ++index) {
            Pair<String, HotDrinkFactory> item = namedFactories.get(index);
            System.out.println("" + index + ": " + item.getValue0());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true){
            String s;
            int i;
            int amount;
            if((s=reader.readLine())!=null && (i = Integer.parseInt(s))>=0 && i <namedFactories.size()){
                System.out.println("Specify amount:" );
                s = reader.readLine();
                if(s!=null && (amount = Integer.parseInt(s))>0){
                    return namedFactories.get(i).getValue1().prepare(amount);
                }
            }
            System.out.println("Incorrect input, please try again.");
        }

    }
}

