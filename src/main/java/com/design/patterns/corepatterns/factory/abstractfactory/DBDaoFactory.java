package com.design.patterns.corepatterns.factory.abstractfactory;

public class DBDaoFactory extends DaoAbstractFactory {

	@Override
	public Dao createDao(String daoType) {
		Dao dao = null;
		if(daoType.equals("emp")) {
			dao = new DBEmpDao(); 
		}else if (daoType.equals("dept")) {
			dao = new DBDeptDao();
		}
		return dao;
	}

}
