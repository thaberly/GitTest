package gov.azdoa.breaz.seleniumtest;

import gov.azdoa.breaz.seleniumtest.beans.TestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Results {
	Log oLog = Log.getInstance();
	
	String sCommentsOld,sFailFlag,destFile;
	int sTestDataID;
	WebDriver driver;
	public Results()
	{
		//Push the function in the function stack for logs
		oLog.push("clsResults.Results");
		//Initialize variable used for storing comments
		sCommentsOld = "";
		sFailFlag = "false";
		
		// Remove the function from the function stack for logs
		oLog.pop ("clsResults.Results");
	}
	
	//Set Driver
	public void setDriver(WebDriver driver){
		this.driver = driver;
	}
	
	//Set TestData ID
	public void setTestDataID(int sTestDataID){
		this.sTestDataID = sTestDataID;
	}
	
	
	public void setResults(int sTestCaseID, String sTCName, String sResult, String sComments)
	{
		//Push the function in the function stack for logs
		oLog.push("clsResults.setResults");
		if(sResult.equalsIgnoreCase("pass"))
		{
			sCommentsOld = sCommentsOld  + "\n" + sComments;
		}
		else
		{
			sFailFlag = "true";
			if(CaptureScreen(sTestCaseID,sTCName)==false)
			{
				sCommentsOld = sCommentsOld + "\n" + sComments + "Not able to take screenshot";
			}
			else
			{
				sCommentsOld =  sCommentsOld + "\n" + sComments + " - Refer Screenshot to view the error at Location - " + destFile ;
			}
		}
		
		//Remove the function from the function stack for logs	
		oLog.pop ("clsResults.setResults");
	}
	
	//Update output.xls with comments
	public void fnUpdateResult( String sTestCaseName ) throws IOException
	{
		FileInputStream fin = new FileInputStream(oLog.sOutputWorkbookPath);
		HSSFWorkbook wObj = new HSSFWorkbook(fin);
		HSSFSheet sheetObj = wObj.getSheet("Output");
		List<TestCase> breazTestCases;
		ReadDataUtils oReadDataUtils = new ReadDataUtils();
		breazTestCases = oReadDataUtils.getAllTestCases();
		
		for(int rownum =0;rownum<breazTestCases.size();rownum++)
		{
			TestCase tc = breazTestCases.get(rownum); 
			if(tc.getTestCaseName().equalsIgnoreCase(sTestCaseName))
			{
				Row row = sheetObj.getRow(rownum+1);
				for(int iCol =0;iCol < 5; iCol++)
				{
					if(iCol==3 && tc.getExecute().equalsIgnoreCase("Yes"))
					{
						Cell cell = row.createCell(iCol);
						if(sFailFlag.equalsIgnoreCase("true"))
						{
							cell.setCellValue("Failed");
						}
						else
						{
							cell.setCellValue("Passed");
						}
					}
					else if(iCol==3 && tc.getExecute().equalsIgnoreCase("No"))
					{
						Cell cell = row.createCell(iCol);
						cell.setCellValue("Not Executed");
					}
					else if(iCol==4  && tc.getExecute().equalsIgnoreCase("Yes"))
					{
						Cell cell = row.createCell(iCol);
						cell.setCellValue(sCommentsOld);
					}
				
				}
			}
			
		}
		try{
			//Write the workbook in file system
			FileOutputStream oFileOutputStream = new FileOutputStream(new File(oLog.sOutputWorkbookPath));
			wObj.write(oFileOutputStream);
			oFileOutputStream.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		
		
				
		//sheetObj.createRow(rownum)
	}
	
	public boolean CaptureScreen(int sTestCaseID, String sTCName)
	{
		//Push the function in the function stack for logs
		oLog.push("clsResults.CaptureScreen");
		//TestExecutor oTestExec = new TestExecutor();
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
				
		String sErrorScreenShotPath = oLog.sFolder + "\\" +  sTCName + sTestCaseID;
		File fso = new File(sErrorScreenShotPath);
		if(!fso.isDirectory())
		{
			fso.mkdir();
				
		}
		
		destFile = sErrorScreenShotPath + "\\" + sTCName + "_" + sTestDataID + ".png";
		try {
			FileUtils.copyFile(srcFile,new File (destFile));
		}
		catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
		
		return true ;
		
	}
	
}
