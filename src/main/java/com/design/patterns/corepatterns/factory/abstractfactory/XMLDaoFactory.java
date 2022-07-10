package com.design.patterns.corepatterns.factory.abstractfactory;

public class XMLDaoFactory extends DaoAbstractFactory {

	@Override
	public Dao createDao(String daoType) {
		Dao dao = null;
		if(daoType.equals("emp")) {
			dao = new XMLEmpDao(); 
		}else if (daoType.equals("dept")) {
			dao = new XMLDeptDao();
		}
		return dao;
	}

}
