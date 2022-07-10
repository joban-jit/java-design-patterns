package com.design.patterns.corepatterns.factory;

public class PizzaFactory {

	public static Pizza createPizza(String typeOfPizza) throws NoSuchMethodException {
		Pizza pizza = null;

		switch (typeOfPizza) {
			case "cheese" :
				pizza = new CheesePizza();
				break;
			case "chicken" :
				pizza = new ChickenPizza();
				break;
			case "veggie" :
				pizza = new VeggiePizza();
				break;
			default :
				throw new NoSuchMethodException("We don't server that");
		}
		
		return pizza;
	}
}
