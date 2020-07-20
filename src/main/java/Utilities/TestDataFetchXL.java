package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class TestDataFetchXL {
	
	
	 public static Object[][] getTestData(String sheetname) throws Exception{
	 //file path where excel file placed, containing test data.
		ConfigFileReader prop=new ConfigFileReader();
	  String filePath = System.getProperty("user.dir")+prop.getPropertyValue("TestData_relativeFilepath")+prop.getPropertyValue("TestData_FileName");
	  
	  //read excel file using file input stream, using Apache POI
	  FileInputStream fis = new FileInputStream(new File (filePath));
	  XSSFWorkbook wb = new XSSFWorkbook(fis);
	  XSSFSheet sheet = wb.getSheet(sheetname);
	  
	  //calculate total number of rows and columns so that we can iterate over it.
	  int totalNumberOfRows = sheet.getLastRowNum()+1;
	  int totalNumberOfCols = sheet.getRow(0).getLastCellNum();
	  
	  //create an object array. which will store the test data from excel file
	  Object[][] testdata = new Object[totalNumberOfRows][totalNumberOfCols];

	  
	  for (int i = 0; i <totalNumberOfRows; i++ ){
	   for (int j = 0; j <totalNumberOfCols; j++){

	  testdata[i][j] =  sheet.getRow(i).getCell(j).toString();   
	   }
	  }
	  return testdata;
	 }

	
	 
	 public static Object[][] getTestDataMap(String sheetname) throws Exception{
	 //file path where excel file placed, containing test data.
		ConfigFileReader prop=new ConfigFileReader();
	  String filePath = System.getProperty("user.dir")+prop.getPropertyValue("TestData_relativeFilepath")+prop.getPropertyValue("TestData_FileName");
	  
	  //read excel file using file input stream, using Apache POI
	  FileInputStream fis = new FileInputStream(new File (filePath));
	  XSSFWorkbook wb = new XSSFWorkbook(fis);
	  XSSFSheet sheet = wb.getSheet(sheetname);
	  
	  //calculate total number of rows and columns so that we can iterate over it.
	  int totalNumberOfRows = sheet.getLastRowNum();
	  int totalNumberOfCols = sheet.getRow(0).getLastCellNum();
	  //we need 1 by 1 [i][0] as we are returning hashmap  
	  //and dataprovider uses 2 d object array 
	  //meaning object can hold any datatype like String,map(collections),int
	  Object testdata[][]=new Object[totalNumberOfRows][1];
	  
	  for (int i=0;i<totalNumberOfRows;i++)
	  {
		  Map<Object,Object> datamap=new HashMap<Object,Object>();
		  for (int j=0;j<totalNumberOfCols;j++)
		  {
			 //key and value pair
			  datamap.put(sheet.getRow(0).getCell(j).toString(),sheet.getRow(i+1).getCell(j).toString());
		
		  }
		  testdata[i][0]=datamap;
	  }
	  return testdata;
}
}
	  
		  
		  
