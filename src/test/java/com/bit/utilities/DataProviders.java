package com.bit.utilities;

import com.bit.base.BaseTest;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;
import java.util.Hashtable;

public class DataProviders extends BaseTest {

    @DataProvider(name = "swagKLabsDP")
    public static Object[][] getDataOne(Method m)
    {
//        System.out.println("Method name is: "+m.getName());  //For debugging
//        ExcelReader excel = new ExcelReader(Constants.SUITE1_XL_PATH);
//        String testCase = m.getName();

        return new Object[0][];
    }

//    @DataProvider(name = "dp")
//    public Object[][] getData(Method m)
//    {
//        //ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/excel/Testdata.xlsx");
//        String sheetName = m.getName();
//        System.out.println("Method name is: "+sheetName);  //For debugging
//        int rows = excel.getRowCount(sheetName);
//        System.out.println("Rows: " + rows);
//        int cols = excel.getColumnCount(sheetName);
//        System.out.println("Cols: " + cols);
//        Object[][] data = new Object[rows - 1][cols];
//        for (int rowNum = 2; rowNum <= rows; rowNum++)
//        {
//            for (int colNum = 0; colNum < cols; colNum++)
//            {
//
//                data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
//            }
//        }
//        return data;
//    }

    @DataProvider(name = "dp")
    public Object[][] getData(Method m)
    {
        log.info("In start of the getData()");
//        ExcelReader excel = new ExcelReader(System.getProperty("user.dir") + "/src/test/resources/excel/Testdata.xlsx");
        String sheetName = m.getName();
        int rows = excel.getRowCount(sheetName);
        int cols = excel.getColumnCount(sheetName);

        Object[][] data = new Object[rows - 1][1];
        Hashtable<String, String> table = null;

        for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2
            table = new Hashtable<String, String>();
            for (int colNum = 0; colNum < cols; colNum++) {
                // data[0][0]
//                data[rowNum - 2][colNum] = excel.getCellData(sheetName, colNum, rowNum);
                table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
                data[rowNum - 2][0] = table;
            }
        }
        log.info("In end of the getData()");
        return data;
    }
}
