package com.testng;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExcelDataProvider {

	@DataProvider(name = "excel-data")
	public Object[][] excelDP() throws IOException {
		// We are creating an object from the excel sheet data by calling a method that
		// reads data from the excel stored locally in our system
		//String[][] arrObj = getExcelData("C://SeleniumDrivers/DataFile.xlsx", "Sheet1");
		String[][] arrObj = getExcelData("src/test/resources/DataFile.xlsx", "Sheet1");
		
		return arrObj;
	}

	// This method handles the excel - opens it and reads the data from the
	// respective cells using a for-loop & returns it in the form of a string array
	public String[][] getExcelData(String fileName, String sheetName) {

		String[][] data = null;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet sh = wb.getSheet(sheetName);
			XSSFRow row = sh.getRow(0);
			int noOfRows = sh.getPhysicalNumberOfRows();
			int noOfCols = row.getLastCellNum();
			Cell cell;
			data = new String[noOfRows - 1][noOfCols];

			for (int i = 1; i < noOfRows; i++) {
				for (int j = 0; j < noOfCols; j++) {

					row = sh.getRow(i);
					cell = row.getCell(j);
                 /* code to chose first matching element
  		    	   if(cell.getStringCellValue().equalsIgnoreCase("Google")) {
  		    		 data[i-1][j] = cell.getStringCellValue();
  		    	   }
  		    	   else
  		    		   continue;*/
					if (cell == null) {
						data[i - 1][j] = "bullybull";
						break;

					}

					switch (cell.getCellType()) {

					case Cell.CELL_TYPE_STRING:
						data[i - 1][j] = cell.getStringCellValue();
						break;

					case Cell.CELL_TYPE_NUMERIC:
						// data[i-1][j] = cell.getNumericCellValue();
						break;

					case Cell.CELL_TYPE_BLANK:
						data[i - 1][j] = "";
						break;

					default:

						data[i - 1][j] = null;
						break;
					}
					System.out.println(data[i - 1][j]);
				}
			}

			//

		}

		catch (Exception e) {
			System.out.println("The exception is: " + e.getMessage());
		}
		return data;
	}

}
