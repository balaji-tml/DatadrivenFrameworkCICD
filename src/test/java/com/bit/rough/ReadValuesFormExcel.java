package com.bit.rough;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import static org.apache.poi.ss.usermodel.CellType.*;

public class ReadValuesFormExcel {

    public FileOutputStream fos;
    public FileInputStream fis = null;
    private XSSFWorkbook workbook  = null;
    private XSSFSheet sheet  = null;
    private XSSFRow row  = null;
    private XSSFCell cell  = null;

    public String path  = null;

    public ReadValuesFormExcel(String path) {
        this.path = path;
        try {
            fis = new FileInputStream(path);
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            fis.close();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    @Test
    public void testReadValuesFormExcel()
    {
        String path = "src/test/resources/excel/Testdata.xlsx";

        ReadValuesFormExcel excel = new ReadValuesFormExcel(path);
    }


    public int getRowCount(String sheetName)
    {
        sheet = workbook.getSheet(sheetName);
        return sheet.getLastRowNum()+1;
    }

    public int getColumnCount(String sheetName)
    {
        sheet = workbook.getSheet(sheetName);
        return sheet.getRow(1).getPhysicalNumberOfCells();
    }

    public String getCellValues(String sheetName,int rows,int cols)
    {
        System.out.println("Rows: "+rows+" and Cols: "+cols);  //For debugging
        String cellValue = null;
        try{
            sheet = workbook.getSheet(sheetName);
            for(int i=1;i<rows;i++)
            {
                row = sheet.getRow(i);
                for(int j=1;j<=cols;j++)
                {
                    cell = row.getCell(j);
                    switch (cell.getCellType())
                    {
                        case STRING:    //field that represents string cell type
                            cellValue = cell.getStringCellValue();
                            break;
                        case NUMERIC:    //field that represents number cell type
                            cellValue = String.valueOf(cell.getNumericCellValue());
                            break;
                        default:
                            cellValue = "";
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return cellValue;
    }

    public String getCellData(String sheetName, int colNum, int rowNum) {
        try {
            if (rowNum <= 0)
                return "";

            int index = workbook.getSheetIndex(sheetName);

            if (index == -1)
                return "";

            sheet = workbook.getSheetAt(index);
            row = sheet.getRow(rowNum - 1);
            if (row == null)
                return "";
            cell = row.getCell(colNum);
            if (cell == null)
                return "";

            if (cell.getCellType() == STRING)
                return cell.getStringCellValue();
            else if (cell.getCellType() == NUMERIC || cell.getCellType() == FORMULA)
            {
                String cellText = String.valueOf(cell.getNumericCellValue());
                return cellText;
            }
            else if (cell.getCellType() == BLANK)
                return "";
            else if (cell.getCellType() == BOOLEAN)
                return String.valueOf(cell.getBooleanCellValue());
            else
                return "";
        } catch (Exception e) {

            e.printStackTrace();
            return "row " + rowNum + " or column " + colNum + " does not exist  in xls";
        }
    }
}
