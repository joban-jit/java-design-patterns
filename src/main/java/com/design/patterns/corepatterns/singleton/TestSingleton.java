package com.design.patterns.corepatterns.singleton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class TestSingleton {

	public static void main(String[] args) {
		var dateUtil = DateUtil.getInstance();
		DateUtil dateUtil1 = null;
		try(var oos = new ObjectOutputStream(new FileOutputStream(new File("dateUtil.ser")));
			var ois = new ObjectInputStream(new FileInputStream("dateUtil.ser"))){
			oos.writeObject(dateUtil);
			dateUtil1 = (DateUtil) ois.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(dateUtil1 == dateUtil);

	}

}
