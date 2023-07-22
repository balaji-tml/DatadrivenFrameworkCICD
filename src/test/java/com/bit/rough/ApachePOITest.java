package com.bit.rough;

import org.testng.annotations.Test;

import java.io.IOException;

public class ApachePOITest {

    @Test
    public void apachePOITest() throws IOException {
        String userDir = System.getProperty("user.dir");
        String path = userDir + "/src/test/resources/excel/SwagLabSuite.xlsx";
        String sheetName;
        int rows,cols;
        Object[][] data;
        ApachePOI apachePOI = new ApachePOI(path);
//        try {
//            apachePOI = new ApachePOI(path);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        sheetName = "LoginTest";
        rows = apachePOI.getRowCount(sheetName);
        cols = apachePOI.getColumnCount(sheetName);
        data=new Object[rows][cols];
        System.out.println("Row count: "+rows);
        System.out.println("Column Count: "+cols);
        data = apachePOI.getDellValues(sheetName,rows, cols);

        for(int i=0;i<rows;i++)
        {
            for(int j=0;j<cols;j++)
            {
                System.out.print("\tCell data at row: "+i+" and column: "+j+" is: "+data[i][j]);
            }
            System.out.println();
        }
    }
}
