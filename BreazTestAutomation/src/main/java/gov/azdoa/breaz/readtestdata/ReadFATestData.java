package gov.azdoa.breaz.readtestdata;

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

import gov.azdoa.breaz.seleniumtest.Global;
import gov.azdoa.breaz.seleniumtest.beans.*;

public class ReadFATestData {
public List<FATestData> getFATestData() throws IOException {
		
		// build list of test data in a spreadsheet
		List<FATestData> faTestData = new ArrayList<FATestData>();
		try{
			//FileInputStream fileObj = new FileInputStream("C:\\Users\\904062\\Desktop\\TestDataSheet.xls");
			FileInputStream fileObj = new FileInputStream(Global.sControllerFilePath);	
			
			//Create Workbook Instance
			HSSFWorkbook wObj = new HSSFWorkbook(fileObj);

			//Get sheet from the workbook with a sheet name = 'CreateFADocument'
			HSSFSheet sheetObj = wObj.getSheet("CreateFADocument");

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
					FATestData testdata = new FATestData();
					while(cellIterator.hasNext()) 
					{ 
						Cell cell = cellIterator.next();
						cell.setCellType(Cell.CELL_TYPE_STRING); 
						switch (cell.getColumnIndex())	 	   	
						{
						case 0:
							testdata.setTestDataID(cell.getStringCellValue());
							break;
						case 1:
							testdata.setDocDept(cell.getStringCellValue());
							break;
						case 2:
							testdata.setDocID(cell.getStringCellValue());
							break;
						case 3:
							testdata.setDocName(cell.getStringCellValue());
							break;
						case 4:
							testdata.setRecDate(cell.getStringCellValue());
							break;
						case 5:
							testdata.setBudgetFiscalYear(cell.getStringCellValue());
							break;
						case 6:
							testdata.setFiscalYear(cell.getStringCellValue());
							break;
						case 7:
							testdata.setAccPeriod(cell.getStringCellValue());
							break;
						case 8:
							testdata.setFaDesc(cell.getStringCellValue());
							break;
						case 9:
							testdata.setRespDept(cell.getStringCellValue());
							break;
						case 10:
							testdata.setUnit(cell.getStringCellValue());
							break;
						case 11:
							testdata.setAcqDate(cell.getStringCellValue());
							break;
						case 12:
							testdata.setCompNumber(cell.getStringCellValue());
							break;
						case 13:
							testdata.setCommodity(cell.getStringCellValue());
							break;
						case 14:
							testdata.setCompUnit(cell.getStringCellValue());
							break;
						case 15:
							testdata.setManufacturer(cell.getStringCellValue());
							break;
						case 16:
							testdata.setVin(cell.getStringCellValue());
							break;
						case 17:
							testdata.setAcqDateTwo(cell.getStringCellValue());
							break;
						case 18:
							testdata.setVendor(cell.getStringCellValue());
							break;
						case 19:
							testdata.setAcqMethod(cell.getStringCellValue());
							break;
						case 20:
							testdata.setMemoDispVal(cell.getStringCellValue());
							break;
						case 21:
							testdata.setLocation(cell.getStringCellValue());
							break;
						case 22:
							testdata.setSubLocation(cell.getStringCellValue());
							break;
						case 23:
							testdata.setFaClassification(cell.getStringCellValue());
							break;
						case 24:
							testdata.setFaCatalogue(cell.getStringCellValue());
							break;
						case 25:
							testdata.setFaType(cell.getStringCellValue());
							break;
						case 26:
							testdata.setFaGrp(cell.getStringCellValue());
							break;
						case 27:
							testdata.setUsefulLife(cell.getStringCellValue());
							break;
						case 28:
							testdata.setInServDate(cell.getStringCellValue());
							break;
						case 29:
							testdata.setDepMethod(cell.getStringCellValue());
							break;
						case 30:
							testdata.setDepStruct(cell.getStringCellValue());
							break;
						case 31:
							testdata.setAccLineAmt(cell.getStringCellValue());
							break;
						case 32:
							testdata.setAccBFY(cell.getStringCellValue());
							break;
						case 33:
							testdata.setAccFY(cell.getStringCellValue());
							break;
						case 34:
							testdata.setAccAccountingPeriod(cell.getStringCellValue());
							break;
						case 35:
							testdata.setFundFY(cell.getStringCellValue());
							break;
						case 36:
							testdata.setFundBFY(cell.getStringCellValue());
							break;
						case 37:
							testdata.setRespCentPost(cell.getStringCellValue());
							break;
						case 38:
							testdata.setAccFund(cell.getStringCellValue());
							break;
						case 39:
							testdata.setAccDept(cell.getStringCellValue());
							break;
						case 40:
							testdata.setAccUnit(cell.getStringCellValue());
							break;
						case 41:
							testdata.setApprUnit(cell.getStringCellValue());
							break;
						case 42:
							testdata.setAccObj(cell.getStringCellValue());
							break;
												
						}
					}
					faTestData.add(testdata);
				}
			}
		}catch(FileNotFoundException e ){
			
			e.printStackTrace();
		}
		return faTestData;
	}
}
