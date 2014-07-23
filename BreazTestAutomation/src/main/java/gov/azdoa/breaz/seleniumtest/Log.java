package gov.azdoa.breaz.seleniumtest;

import gov.azdoa.breaz.seleniumtest.beans.TestCase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCreation;
import javax.xml.stream.events.EndDocument;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.thoughtworks.selenium.webdriven.commands.CreateCookie;



public class Log {
	/****************************************************
	* singleton instance
	* created when class is loaded.
	*****************************************************/
	private static Log oLog = new Log();
	
	//Declare Variables
	File fso ;
	FileOutputStream fos;
	String sFolder,sOutputWorkbookPath,sLogFilePath;
	//BufferedWriter out;
	//String [] sFunArray ;
	PrintWriter oWriter;
	public List<String> sFunArray = new ArrayList<String>();
	
		
 	public static Log getInstance()
	{
 		return oLog;
	}

 	public void close() throws IOException{
 		oWriter.close();
 	}
	
	//private static Log oLog = new Log();
	private Log() 
	{
		
		//Global.sWriteDebugLog = "false";
		String sTimeStamp = fnGetTimeStamp();
		sLogFilePath = Global.sRootDirPath + "Output\\Results\\" + "Log_" + sTimeStamp +".txt";
		
		try{
			oWriter = new PrintWriter(sLogFilePath);
		}
		catch (FileNotFoundException e){
			e.printStackTrace();
		}
		
		/*
		fso = new File(sLogFilePath);
		 try {
			out = new BufferedWriter(new FileWriter(sLogFilePath));
		} catch (IOException e1) {
			// TODO Auto-generated catch blockw
			e1.printStackTrace();
		}
		try {
			fso.createNewFile();
			fso = null;
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		fnMandatoryLog("***************Started Execution*********************");
		sFunArray.add("Driver");
		//sFunArray[0] = "Driver";
						
	}
	

	
/******************************************************************************************
* Function Name :fnWriteError
* Purpose     : To write information to log files when Error occurs :---
* Parameters    :  Comments - String
* Retrun Value  : None
* Pre-Conditions:None
* Usage         :NA
* Revisions     :1
* Date			:         				 												
* Addition/Change: 
* Developed By : Stuti S
********************************************************************************************/

	public void fnWriteError(String sComments)
	{
		oWriter.println(fnGetTimeStamp() + " : ERROR : "  + sComments);				
	}   
	

	
/********************************************************************************************
* Method Name :fnMandatoryLog
* Purpose     : To write manadatory steps to the log files  
* Parameters    :  Comments - String
* Retrun Value  : None
* Pre-Conditions:None
* Usage         :NA
* Revisions     :1
* Date         	:			Developed By :   												
* Addition/Change
*  		
**********************************************************************************************/	
		public void fnMandatoryLog(String sComments)
		{
			oWriter.println(fnGetTimeStamp() + " : " + sComments);
		}
	
/******************************************************************************************
* Function Name :push
* Purpose     : 	To push function name into the funarray 
* Parameters    :  Comments - String
* Retrun Value  : None
* Pre-Conditions:None
* Usage         :NA
* Revisions     :1
* No. 			Date         				Developed By    												
* Addition/Change
'***********************************************************************************************	*/
		public void push(String sFunName)
		{
		    int	iArr = sFunArray.size();
			//redim preserve sFunArray(iArr+1)
			sFunArray.add(iArr, sFunName);
			String sFunstr = "Entered into New Function:";
			for (int i=0; i<=iArr;i++)
			{
	            sFunstr = sFunstr +  sFunArray.get(i) + ">>";
			}
			
			fnMandatoryLog(sFunstr);
		
		}
		
/******************************************************************************************
* Method Name :pop
* Purpose     : To pop function name from the funarray
* Parameters    :  Comments - String
* Retrun Value  : None
* Pre-Conditions:None
* Usage         :NA
* Revisions     :1
* No. 			Date         				Developed By    												
* Addition/Change
* 1   					
'******************************************************************************************	*/
			public void pop(String sFunName)
			{
				int	iArr = sFunArray.size();
				if(iArr<0)
				{
					fnMandatoryLog("Function array is found empty, exit function from stack failed for : " + sFunName);
				}
				else if(sFunArray.get(iArr-1).equalsIgnoreCase(sFunName))
				{
					String sFunStr = "Exited out of Function :" +  sFunArray.get(iArr-1) + "   : : Remaining Function Stack : ";
					for(int i=0;i<iArr-2;i++)
					{
						sFunStr = sFunStr + sFunArray.get(i) + " >> ";
					}
					fnMandatoryLog(sFunStr);
				}
				else
				{
					fnMandatoryLog("Exit function from stack failed for : " + sFunName + " as next function to be poped is " + sFunArray.get(iArr-1));
				}
			}
	 

		 
	//Check if File exists
	public boolean CheckFileExist(String str)
	{
		 fso = new File(str);
		
		if (fso.exists())
		{
		
			return true;
		}
		else
		{	
			return false;
		}
	}
	
	//Push Function
/*	public  void fnPush(String sPushLog) throws Exception
	{
		
		sPushLog = sPushLog + ">>" ;
		//BufferedWriter out = new BufferedWriter(new FileWriter(dir));
		//fw = new FileWriter(dir + ".txt");
		//fw.write(sPushLog);
		///////////
		
        out.write(sPushLog);
        out.close();
		
	}*/
	
	//Create Folder at Runtime
	public void fnCreateOutputFolder() throws IOException
	{
		String stimeStamp = fnGetTimeStamp();
		sFolder = Global.sRootDirPath + "Output\\Results" + "\\" + "Run_" + stimeStamp;
		File fso = new File(sFolder);
		if(!fso.isDirectory())
		{
			fso.mkdir();
			fnCreateOutputFile();			
		}
	
	}
	
	
	//Create Excel File Containing List Of Test Cases
	public  void fnCreateOutputFile() throws IOException
	{
		//String sDestFolder = sFolder 
		//FileUtils.copyFile(new File(Global.sControllerFilePath), dfso);
		
		HSSFWorkbook wObj = new HSSFWorkbook();
		HSSFSheet sheetObj = wObj.createSheet("Output");
		Row row = sheetObj.createRow(0);
		for(int iCol =0; iCol <5;iCol++)
		{
			Cell cell = row.createCell(iCol);
			if(iCol==0)
			{
				cell.setCellValue("TestCaseName");
			}
			if(iCol==1)
			{
				cell.setCellValue("Execute");
			}
			if(iCol==2)
			{
				cell.setCellValue("Browser");
			}
			if(iCol==3)
			{
				cell.setCellValue("ExecutionStatus");
			}
			if(iCol==4)
			{
				cell.setCellValue("Comments");
			}
		}
		
		//Write Test Cases in the output file
		List<TestCase> breazTestCases;
		ReadDataUtils oReadDataUtils = new ReadDataUtils();
		breazTestCases = oReadDataUtils.getAllTestCases();
		
		for(int i=0;i<breazTestCases.size();i++)
		{
			TestCase tc = breazTestCases.get(i); 
			row = sheetObj.createRow(i+1);
			 Cell cell = row.createCell(0);
			cell.setCellValue(tc.getTestCaseName());
			cell = row.createCell(1);
			cell.setCellValue(tc.getExecute());
			cell = row.createCell(2);
			cell.setCellValue(tc.getBrowser());
		}
		
		try{
			//Write the workbook in file system
			sOutputWorkbookPath = sFolder +"\\output.xls";
			FileOutputStream oFileOutputStream = new FileOutputStream(new File(sOutputWorkbookPath));
			wObj.write(oFileOutputStream);
			oFileOutputStream.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		
		
		
	}
	
	
	
	//Create Function
	/*public void fnCreateFile()
	{
		
		fso = new File(dir + ".txt");
		try {
			fso.createNewFile();
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 
		
	}*/
	
	
	//Function to get TimeStamp
	public String fnGetTimeStamp()
	{
	
		   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
		   //get current date time with Date()
		   Date date = new Date();
		   String currDateTime = dateFormat.format(date).toString();
		   return (currDateTime);

		
	}
	
	
	

}
