package com.design.patterns.corepatterns.factory;

public class PizzaStore {

	public Pizza orderPizza(String typeOfPizza) throws NoSuchMethodException {

				Pizza pizza = PizzaFactory.createPizza(typeOfPizza);
		// moved below commented out code to pizzaFactory to handle that logic
//
//		switch (typeOfPizza) {
//			case "cheese" :
//				pizza = new CheesePizza();
//				break;
//			case "chicken" :
//				pizza = new ChickenPizza();
//				break;
//			case "veggie" :
//				pizza = new VeggiePizza();
//				break;
//			default :
//				throw new NoSuchMethodException("We don't server that");
//		}
		
		pizza.prepare();
		pizza.bake();
		pizza.cut();

		return pizza;
	}
}
