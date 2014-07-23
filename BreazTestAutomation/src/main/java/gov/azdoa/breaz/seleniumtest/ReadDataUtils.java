package gov.azdoa.breaz.seleniumtest;

import gov.azdoa.breaz.seleniumtest.beans.TestCase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ReadDataUtils {

	//Call this method from main() to get a list of all test cases
	public List<TestCase> getAllTestCases() throws IOException { 
		
		//GetInstance of Log Class to write in the log
		Log oLog = Log.getInstance();
		oLog.push("clsReadDataUtils.getAllTestCases");
		
		// build list of all testCases in spreadsheet
		List<TestCase> breazTestCases = new ArrayList<TestCase>();

		try{
			FileInputStream fileObj = new FileInputStream(Global.sControllerFilePath);

			//Create Workbook Instance
			HSSFWorkbook wObj = new HSSFWorkbook(fileObj);

			//Get first sheet from the workbook
			HSSFSheet sheetObj = wObj.getSheetAt(0);

			Iterator <Row> rowIterator = sheetObj.iterator();

			//Check if there is Data in the row
			if (rowIterator.hasNext())
			{
				//Point to the Header of the Sheet
				Row row = rowIterator.next();

				//Initialize the Variables with the first row of the Excel Sheet
				rowIterator = sheetObj.iterator();
				// Pointing to the First Row of the Excel Sheet
				row = rowIterator.next();

				while(rowIterator.hasNext()) 
				{
					row = rowIterator.next();
					Iterator<Cell> cellIterator = row.cellIterator();
					TestCase tc = new TestCase();
					while(cellIterator.hasNext()) 
					{ 
						Cell cell = cellIterator.next();
						switch (cell.getColumnIndex())	 	   	
						{
						case 0:
							tc.setTestCaseID((int)cell.getNumericCellValue());
							break;
						case 1:
							tc.setExecute(cell.getStringCellValue());
							break;
						case 2:
							tc.setTestCaseName(cell.getStringCellValue());
							break;
						case 3:
							tc.setTestData(cell.getStringCellValue());
							break;
						case 4:
							tc.setBrowser(cell.getStringCellValue());
							break;
						
						}
					}

					breazTestCases.add(tc);
				}
			}



		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}

		oLog.pop("clsReadDataUtils.getAllTestCases");
		return breazTestCases;
	}
}
