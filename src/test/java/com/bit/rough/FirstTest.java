package com.bit.rough;

import org.testng.annotations.Test;

import java.util.Date;

public class FirstTest {//extends BaseTest{

	public FirstTest(String path) {

	}

	@Test
	public void firstTest()
	{
//		int rows,cols;
//		System.out.println("First test");
//		String path = "src/test/resources/excel/Testdata.xlsx";
//
//		ExcelReader excelReader = new ExcelReader(path);
//		rows = excelReader.getRowCount("LoginTest");
//		cols = excelReader.getColumnCount("LoginTest");
//		System.out.println("Row count: "+ rows);   //For debugging
//		System.out.println("Column count: "+ cols);  //For debugging
//		excelReader.getCellValues("LoginTest",rows,cols);

		System.out.println("In First Test method");
		String fileName = new Date().getTime() + ".txt";
		System.out.println("File name: "+fileName);

	}

}
