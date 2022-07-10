package com.design.patterns.corepatterns.factory.abstractfactory;

public class DaoFactoryProducer {
	
	public static DaoAbstractFactory produce(String daoFactoryType) {
		DaoAbstractFactory daf = null;
		if(daoFactoryType.equals("xml")) {
			daf = new XMLDaoFactory();
		}else if (daoFactoryType.equals("db")) {
			daf = new DBDaoFactory();
		}
		return daf;
	}

}
