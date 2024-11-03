package com.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyFileUtil {
	
	
	public static String readDataFromPropertyFile(String fileName,String propetyKey) {
		String filePath = "./data/"+fileName+".properties";
		File oFile = new File(filePath);
		String propertyValue = "";
		try {
			FileInputStream oFIS = new FileInputStream(oFile);
			Properties oProp = new Properties();
			oProp.load(oFIS);
			propertyValue = oProp.getProperty(propetyKey);
			return propertyValue;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return propertyValue;
	}

}
