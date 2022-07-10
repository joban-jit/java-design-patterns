package com.design.patterns.corepatterns.factory;

public class TestFactory {

	public static void main(String[] args) throws NoSuchMethodException {
		PizzaStore ps = new PizzaStore();
		ps.orderPizza("cheese");
		ps.orderPizza("veggie");

	}

}
