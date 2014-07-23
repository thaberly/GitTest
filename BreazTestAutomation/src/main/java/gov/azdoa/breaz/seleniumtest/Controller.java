package gov.azdoa.breaz.seleniumtest;
import gov.azdoa.breaz.seleniumtest.beans.TestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.bcel.verifier.structurals.ExceptionHandler;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.usermodel.HSSFName;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;





public class Controller {
	
	
	String sTCName;
	private List<TestCase> breazTestCases;
	
	
	
	//Constructor
	public Controller() {
		ReadDataUtils oReadDataUtils = new ReadDataUtils();
		try {
			breazTestCases = oReadDataUtils.getAllTestCases();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getNumberOfTestCases(){
		
		return breazTestCases.size();
	}
	
	//Loop through the Test Suite Sheet
	@SuppressWarnings({ "unchecked" })
	public void ReadTestCaseSheet() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException, NoSuchMethodException, SecurityException, InstantiationException, ClassNotFoundException
	{
	
		Log oLog = Log.getInstance();
		oLog.push("ClsController.ReadTestCaseSheet");
				
		for(int i = 0; i < breazTestCases.size(); i++)
		{
			TestCase tc = breazTestCases.get(i);
			if (tc.getExecute().equalsIgnoreCase("Yes"))
			{
				if(!tc.getTestCaseName().isEmpty())
				{
					//Invoke the respective Method
					//Load the TestExecutor at runtime
					
					Class[] paramString = new Class[4];
					paramString[0]=String.class;
					paramString[1] = String.class;
					paramString[2]=String.class;
					paramString[3]=Integer.TYPE;
					Class cls = Class.forName("gov.azdoa.breaz.seleniumtest.TestExecutor");
					sTCName = tc.getTestCaseName();
					String methodName = "test" + sTCName;
					Object obj = cls.newInstance();
					try{
						Method method = cls.getDeclaredMethod(methodName,paramString);
						method.invoke(obj, tc.getBrowser(), tc.getTestData(), tc.getTestCaseName(),tc.getTestCaseID());	
					}catch(NoSuchMethodException e){
						e.printStackTrace();
					}
					
					 
				}
				else
				{
					oLog.fnWriteError("TestCase name is blank");
				}
			}
			 
		}
		oLog.pop("clsController.ReadTestCaseSheet");
	}
	

	

}

