package com.automation.utils;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

    public static Object[][] getSheetData(String filePath, String sheetName) {
        Object[][] data = null;
        try (FileInputStream fis = new FileInputStream(filePath);
             XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            
            XSSFSheet sheet = workbook.getSheet(sheetName);
            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();
            
            // Subtracting 1 to skip the header row in the test execution loop
            data = new Object[rowCount - 1][colCount];
            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i < rowCount; i++) {
                XSSFRow row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    XSSFCell cell = row.getCell(j);
                    // Formats cell values cleanly into standard Strings automatically
                    data[i - 1][j] = formatter.formatCellValue(cell);
                }
            }
        } catch (IOException e) {
            System.out.println("💥 Failed to read Excel workbook path: " + e.getMessage());
        }
        return data;
    }
}