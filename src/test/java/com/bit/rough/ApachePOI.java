package com.bit.rough;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ApachePOI {

    protected FileInputStream fis;
    protected XSSFWorkbook workbook;
    protected XSSFSheet sheet;
    protected XSSFRow row;
    protected XSSFCell cell;

    public ApachePOI(String path) throws IOException
    {
        this.fis = new FileInputStream(path);
        this.workbook = new XSSFWorkbook(fis);
    }

    public int getRowCount(String sheetName)
    {
        sheet = workbook.getSheet(sheetName);
        return sheet.getLastRowNum() + 1;
    }

    public int getColumnCount(String sheetName)
    {
        sheet = workbook.getSheet(sheetName);
        row = sheet.getRow(1);
        return row.getLastCellNum();
    }

    public void closeExcelFile()
    {
        workbook = null;
        try {
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Object[][] getDellValues(String sheetName,int rows, int cols)
    {
        Object[][] data = new Object[rows][cols];
        sheet = workbook.getSheet(sheetName);
        for(int i=0;i<rows;i++) {
            row = sheet.getRow(i);
            for (int j = 0; j < cols; j++) {
                cell = row.getCell(j);
                data[i][j] = cell.getStringCellValue();
            }
        }
        return data;
    }


}
