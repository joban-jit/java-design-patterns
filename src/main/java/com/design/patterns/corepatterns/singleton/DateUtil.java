package com.design.patterns.corepatterns.singleton;

import java.io.Serializable;
import java.util.Optional;
import java.util.function.Supplier;

public class DateUtil implements Serializable,
Cloneable // to make sure our single object is not cloneable we implement this Cloneable interface
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static volatile DateUtil instance;
	// Eager initialization - not preferred
//	private static DateUtil instance = new DateUtil();
//	private static DateUtil instance;
	// Eager initialization using static block- not preferred
//	static {
//		instance = new DateUtil();
//	}

	
	
	private DateUtil() {
		
	}
	
	public static  DateUtil getInstance() {
		// lazy initialization - preferred
		if(Optional.ofNullable(instance).isEmpty()) {
		synchronized(DateUtil.class) {// to make sure it is thread safe
			if(Optional.ofNullable(instance).isEmpty()) {
				instance = new DateUtil();
			}
		}
		}
		return instance;
	}
	// private or protected
	// this is method is being called by ObjectInputStream to de-serialize the object - you don't need to 
	// define it but if don't define it will return different object so , singleton pattern would break
	private Object readResolve() {
		return instance;
	}
	
	
	// to make sure our single object is not cloneable
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		// if some class extends our dateUtil and if we "return super.clone()" in this method, singleton would break
		throw new CloneNotSupportedException(this.getClass().getName()+" is a singleton Class");
	}
}




