package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class ReadXLSdata {

    // DataProvider method to supply test data for tests
    @DataProvider(name = "testingData")
    public String[][] getData(Method m) throws IOException {
        // Retrieve the method name to use it as the Excel sheet name
        String excelSheetName = m.getName();
        
        // Specify the path to the Excel file containing the test data
        File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\testData\\testdata.xlsx");
        
        // Try-with-resources to ensure that the file input stream and workbook are closed after use
        try (FileInputStream fis = new FileInputStream(file); Workbook wb = new XSSFWorkbook(fis)) {
            // Access the specific sheet in the Excel file corresponding to the test method name
            Sheet sheetName = wb.getSheet(excelSheetName);

            // Get the total number of rows and columns in the sheet
            int totalRows = sheetName.getLastRowNum();
            Row rowCells = sheetName.getRow(0);
            int totalCols = rowCells.getLastCellNum();

            // DataFormatter to format the cell values as strings
            DataFormatter format = new DataFormatter();
            // Initialize a two-dimensional array to store the test data
            String[][] testData = new String[totalRows][totalCols];

            // Loop through the rows and columns to read and store the data from the Excel sheet
            for (int i = 1; i <= totalRows; i++) {
                for (int j = 0; j < totalCols; j++) {
                    // Store each cell value in the testData array, formatted as a string
                    testData[i - 1][j] = format.formatCellValue(sheetName.getRow(i).getCell(j));
                }
            }
            // Return the populated test data array
            return testData;
        }
    }
}
