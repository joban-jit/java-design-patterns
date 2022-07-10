package com.design.patterns.corepatterns.factory.abstractfactory;

public class TestAbstractFactory {

	public static void main(String[] args) {

		DaoAbstractFactory daf = DaoFactoryProducer.produce("db");
		var dao = daf.createDao("dept");
		dao.save();
		
	}

}
