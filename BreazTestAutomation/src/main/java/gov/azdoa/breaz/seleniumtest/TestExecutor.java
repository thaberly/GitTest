package gov.azdoa.breaz.seleniumtest;

import org.testng.annotations.Test;

import gov.azdoa.breaz.readtestdata.ReadFATestData;
import gov.azdoa.breaz.seleniumtest.beans.FATestData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.webbitserver.WebbitException;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;
import com.google.common.base.Predicate;
import com.thoughtworks.selenium.SeleniumLogLevels;
import com.thoughtworks.selenium.webdriven.commands.IsTextPresent;
import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;


// Put all test execution logic here
public class TestExecutor {
	
	gov.azdoa.breaz.seleniumtest.Log oLog = gov.azdoa.breaz.seleniumtest.Log.getInstance();
	public boolean sResultsFlag = false; 
	public WebDriver driver;
	FileInputStream fso;
	HSSFWorkbook wObj;
	HSSFSheet sheetObj;
	public String sComments ="";
	Results oResult = new  Results();
	String sTCName;
	int sTestCaseID;
	public WebElement element;
	
		//Default Constructor
		public TestExecutor()
		{
			
		}
		
	
		//test Login
		@Test
		public void testLogin(String sBrowserName, String sTestDataFlag, String sTCName,int sTestCaseID) throws IOException, ElementNotFoundException, InterruptedException
		{
			this.sTCName = sTCName;
			this.sTestCaseID = sTestCaseID;
			if(checkTestDataSheetExists()==true)
			{
				Iterator <Row> rowIterator = sheetObj.iterator();
				
				//Check if there is Data in the row
			    if (rowIterator.hasNext())
			    {
			    	//Point to the Header of the Sheet
			    	Row row = rowIterator.next();
			    				    	    	
			    	//Set the pointer to the column header 
			    	Iterator<Cell> cellIterator = row.cellIterator();
			    				    	
	 		 		//row = rowIterator.next();
	 		 		
	 		 		//cellIterator = row.cellIterator();
	 		 		
	 		 		while(rowIterator.hasNext())
			    	{
	 		 			//initialize the variables with null value 
	 		 			String sURL = "" ,sPassword="" , sUserName="";
	 		 			int sTestDataID ;
	 		 			
	 		 			// Pointing to Second Row of the Excel Sheet
	 		 			row = rowIterator.next();
		 		 		
		 		 		cellIterator = row.cellIterator();
	 		 			while(cellIterator.hasNext())
	 		 			{
	 		 				Cell cell = cellIterator.next();
	 		 				
	 		 				//Read the row under the first Column header
	 		 				if(cell.getColumnIndex()==0)
	 		 				{
	 		 					sTestDataID = (int) cell.getNumericCellValue();
	 		 					oResult.setTestDataID(sTestDataID);
	 		 				}
	 		 				
	 		 				//Read the row under the first Column Header
	 		 				if(cell.getColumnIndex()==1)
	 		 				{
	 		 					sUserName = cell.getStringCellValue();
	 		 				}
	 		 				
	 		 				//Read the row under the Second Column Header
	 		 				if(cell.getColumnIndex()==2)
	 		 				{
	 		 					sPassword = cell.getStringCellValue();
	 		 				}
	 		 				
	 		 				//Read the row under the third Column Header
	 		 				if(cell.getColumnIndex()==3)
	 		 				{
	 		 					sURL = cell.getStringCellValue();
	 		 				}
	 		 			}
	 		 			
	 		 			// Set Browser Property
	 					setBrowserProperty(sBrowserName);
	 		 			
	 		 			//Open Application//
 		 				fnOpenApp(sURL);
 		 				
 		 			 		 				
 		 				//Input UserID
 		 				fnSetText("id","login",sUserName);
 		 				
 		 				//Input Password
 		 				fnSetText("id","password",sPassword);
 		 				
 		 				//Click Login Button
 		 				fnClickElement("id","vslogin");
 		 				
 		 				
 		 				//driver.close();
 		 				//String acb = switchWindow();
 		 				
 		 				//driver.switchTo().frame("Startup");
 		 				//driver.quit();
 		 			//	fnClickButton("id","Logout");		 				
 		 				
 		 				
 		 				//Switch the window handle to next window
 		 			/*	Set <String> s = driver.getWindowHandles();
 		 				
 		 				//this method will you handle of all opened windows

 		 				Iterator <String> ite=s.iterator();
 		 				int i = s.size();
 		 				//s.contains(o);
 		 				while(ite.hasNext())
 		 				{
 		 				     subWindowHandler =ite.next();
 		 				     str = driver.getTitle();
 		 				    if(driver.getTitle().equalsIgnoreCase("Logout"))
 		 				    {
 		 				                driver.switchTo().window(subWindowHandler);
 		 				                break;
 		 				               // here you can perform operation in pop-up window**
 		 				                //After finished your operation in pop-up just select the main window again
 		 				             //   driver.switchTo().window(popupHandle);
 		 				    }
 		 				}*/
 		 				
 		 				//WaitForPageToLoad obj = new WaitForPageToLoad();
 		 				//setParentWindow("CGI Advantage");
 		 				 
 		 				//fnClickButton("xpath","/html/body/form/div/div/div[2]/div[2]/a/img[@id='Logout']");
 		 				

 		 			/*	for (String winHandle : driver.getWindowHandles()) 
 		 				{
 		 					int i = driver.getWindowHandles().size();
 		 					if(driver.getTitle().equalsIgnoreCase("CGI Advantage"))
 		 					{
 		 						driver.switchTo().window(winHandle);
 		 						fnClickButton("id","Logout");
 		 					}
 		 				   
 		 				   //String str =  driver.getCurrentUrl();
 		 				   // switch focus of WebDriver to the next found window handle (that's your newly opened window)
 		 				    System.out.print(winHandle);
 		 				}*/

 		 				//Thread.sleep(3000);
 		 				
 		 				//Set Parent Window
 		 				//setParentWindow("CGI Advantage");
 		 				//Thread.sleep(2000);
 		 				// Click LogOut Button
 		 				
 		 				//driver.quit();
 		 				
			    	}//End of while loop 
	 		 		
	 		 		oResult.fnUpdateResult(sTCName);
			    }
			    else
			    {
			    	System.out.print("Test Data Sheet is empty");
			    }
			}
			else
			{
				System.out.print("Test case sheet not found");
			}
			
			
		}
		
		//Create FA Document
		@Test
		public void testCreateFADocument(String sBrowserName, String sTestDataFlag, String sTCName,int sTestCaseID) throws InterruptedException
		{
			setBrowserProperty(sBrowserName);
			this.sTCName = sTCName;
			this.sTestCaseID = sTestCaseID;
			try {
					ReadFATestData faObj = new ReadFATestData();
					List <FATestData> faTestData = faObj.getFATestData();
					
					//Loop through the rows
					for(int i=0; i<faTestData.size();i++)
					{
						FATestData faTest = faTestData.get(i);
						
						//Check if Login is successful
						if(isLoginAdvantage())
						{
						
							//Check if Alert is present
							if(isAlertPresent())
							{
								driver.switchTo().alert().accept();
							}
							//Maximize Window
							fnMaxWindow();
						
							//Switch Window
							switchWindow();
							
							//Switch Frame
							switchFrame("Secondary");
							switchFrame("AppNav");
							//Click Search
							fnClickSearch();
							
							//Click Document Catalog
							fnClickDocumentCatalogue();
							
							//Exit out of the frames
							driver.switchTo().defaultContent();
							
							//Switch to Frame
							switchFrame("Display");
							
							//Enter Document Code
							fnSetText("id", "DOC_CD", "FA");
							
							//Enter Document Department
							fnSetText("id", "DOC_DEPT_CD", faTest.getDocDept());
							
							//Enter Document ID
							fnSetText("id", "DOC_ID", faTest.getDocID());
							
							//Click Create Link
							fnClickLink("id", "DocSearchCreateMode");
							
							//Click Header
							fnClickLink("id", "T1DOC_HDRinsert");
							
							//Click General Information
							fnClickTab("General Information");
							
							//Enter Document Name
							fnSetText("id", "txtT1DOC_NM", faTest.getDocName());
							
							//Enter Record Date
							fnSetText("id", "txtT1DOC_REC_DT_DC", faTest.getRecDate());
							
							//Enter Budget Fiscal Year
							fnSetText("id", "txtT1DOC_BFY", faTest.getBudgetFiscalYear());
							
							//Enter Fiscal Year
							fnSetText("id", "txtT1DOC_FY_DC", faTest.getFiscalYear());
							
							//Enter Period
							fnSetText("id", "txtT1DOC_PER_DC", faTest.getAccPeriod());
							
							//Enter Fixed Asset Description
							fnSetText("id", "txtT1FA_DSCR", faTest.getFaDesc());
							
							//Check the checkbox - Auto Generate FA number
							//element = driver.findElement(By.id("txtT1AUTO_GEN_FA_NO_FL"));
							//driver.findElement(By.xpath("//input[contains(@title,'Auto Generate FA Number')]"));
							//element.click();
							fnClickElement("id", "txtT1AUTO_GEN_FA_NO_FL");
							//Navigate to Responsibility center
							fnClickTab("Responsibility center");
							
							//Enter Department Name
							fnSetText("id", "txtT1DEPT_CD", faTest.getRespDept());
							
							//Enter Unit field
							fnSetText("id", "txtT1UNIT_CD", faTest.getUnit());
							
							//Navigate to Composite Asset Information
							fnClickTab("Composite Asset Information");
							
							//Enter Acquisition Date
							fnSetText("id", "txtT1ACQ_DT", faTest.getAcqDate());
							
							//Click Save
							fnClickTab("Save");
							
							//Click Component Section
							fnClickElement("id", "FA_CompComponentActionSelect");
							
							//Click Insert New Line
							fnClickElement("id", "FA_CompComponentGridActionInsertNew");
							
							//Enter Component Number
							fnSetText("id", "txtT2COMP_NO", faTest.getCompNumber());
							
							//Enter Commodity
							fnSetText("id", "txtT2COMM_CD", faTest.getCommodity());
							
							//Enter Units
							fnSetText("id", "txtT2UNIT", faTest.getCompUnit());
							
							//Check the checkbox Base ASSET
							fnClickElement("id", "txtT2BASE_AST_FL");
							
							//Navigate to Specifications
							fnClickTab("Specifications");
							//Enter Manufacturer
							fnSetText("id", "txtT2MANFR_NM", faTest.getManufacturer());
							
							//Enter VIN
							fnSetText("id", "txtT2VIN", faTest.getVin());
							
							//Navigate to Acqusition Details
							fnClickTab("Acquisition Details");
							
							//Enter Acquisition Date
							fnSetText("id", "txtT2ACQ_DT", faTest.getAcqDateTwo());
							
							//Enter Vendor
							fnSetText("id", "txtT2VEND_CUST_CD", faTest.getVendor());
							
							//Enter Acquisition Method
							fnSetText("id", "txtT2ACQ_METH", faTest.getAcqMethod());
							
							//Enter Memo Disposal Value
							fnSetText("id", "txtT2MMO_DSP_VL", faTest.getMemoDispVal());
							
							//Navigate to Component Location Details
							fnClickTab("Component Location Details");
							
							//Enter Location 
							fnSetText("id", "txtT2LOC_CD", faTest.getLocation());
							
							//Enter Sublocation
							fnSetText("id", "txtT2SLOC_CD", faTest.getSubLocation());
							
							//Go to Component Classification
							fnClickTab("Component Classification");
							
							//Select Fixed Assset Classification
							fnSelectText("id","txtT2FA_CLS_IND",faTest.getFaClassification());
							
							//Enter Fixed Asset Catalog
							fnSetText("id", "txtT2FA_CTLG", faTest.getFaCatalogue());
							
							//Enter Fixed Asset type
							fnSetText("id", "txtT2FA_TYP", faTest.getFaType());
							
							//Enter Fixed Asset Group
							fnSetText("id", "txtT2FA_GRP", faTest.getFaGrp());
							
							//Enter Useful Life
							fnSetText("id", "txtT2USE_LIFE", faTest.getUsefulLife());
							
							//Enter In Service Date
							fnSetText("id", "txtT2IN_SVC_DT", faTest.getInServDate());
							
							//Navigate to Cost, Valuation, & Depriciation
							fnClickTab("Cost, Valuation & Depreciation");
							
							//Select Depriciation Method
							fnSelectText("id", "txtT2DEPR_METH", faTest.getDepMethod());
							
							//Select Depriciation Structure
							fnSelectText("id", "txtT2DEPR_STRU_IND", faTest.getDepStruct());
							
							//Go to Accounting Line Section
							fnClickElement("id", "AccountingComponentActionSelect");
							
							//Click Insert New Line
							fnClickElement("id", "AccountingComponentGridActionInsertNew");
							
							//Enter Line Amount
							fnSetText("id", "txtT3LN_AM", faTest.getAccLineAmt());
							
							//Enter Budget FY
							fnSetText("id", "txtT3BFY", faTest.getAccBFY());
							
							//Enter Fiscal Year
							fnSetText("id", "txtT3FY_DC", faTest.getAccFY());
							
							//Enter Accounting Period
							fnSetText("id", "txtT3PER_DC", faTest.getAccAccountingPeriod());
							
							//Enter Funding Fiscal Year
							fnSetText("id", "txtT3FUND_FY_DC", faTest.getFundFY());
							
														
							//Enter Funding Budget FY
							fnSetText("id", "txtT3FUND_BFY", faTest.getFundBFY());

							//Enter Responsibility Center Posting
							fnSelectText("id", "txtT3RESP_CTR_PST_FL", faTest.getRespCentPost());
							
							//Go to Fund Accounting
							fnClickTab("Fund Accounting");
							
							//Enter Fund
							fnSetText("id", "txtT3FUND_CD", faTest.getAccFund());
							
							//Enter Department 
							fnSetText("id", "txtT3DEPT_CD", faTest.getAccDept());
							
							//Enter Unit
							fnSetText("id", "txtT3UNIT_CD", faTest.getAccUnit());
							
							//Enter Appr Unit
							fnSetText("id", "txtT3APPR_CD", faTest.getApprUnit());
							
							//Enter Object
							fnSetText("id","txtT3OBJ_CD",faTest.getAccObj());
							
							//Click Validate
							fnClickElement("id", "DocumentActionValidate");
							
							//Check if Document is Validated successfully
							if(isDocValidateSuccess())
							{
								//Bypass Approvals and Click Submit
								fnBypassApprovalandSubmit();
							}
							
							//Close the Document
							fnClickElement("id", "DocumentActionClose");
							
							//Check document Created Successfully
							if(isDocCreatedSuccessfully(faTest.getDocDept(),faTest.getDocID()))
							{
								oResult.fnUpdateResult(sTCName);
							}
						}
					}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public boolean isDocCreatedSuccessfully(String docDept, String docID) throws InterruptedException
		{
			//Exit out of the frames
			driver.switchTo().defaultContent();
			
			//Switch to Frame
			switchFrame("Display");
			
			//Enter Document Code
			fnSetText("id", "DOC_CD", "FA");
			
			//Enter Document Department
			fnSetText("id", "DOC_DEPT_CD", docDept);
			
			//Enter Document ID
			fnSetText("id", "DOC_ID", docID);
			
			//Click Search Button
			fnClickElement("id","DocSearchSearchMode");
			
			//Click Browse Button
			fnClickElement("id","AMSBrowse");
			
			//Check if Doc name exists in the table
			if (isDocNameInTableExists(docID))
			{
				
				return true;
				
			}
			else
			{
				return false;
			}
			
			
		}
		
		public boolean isDocNameInTableExists(String docName) throws InterruptedException
		{
			// Grab the table
			WebElement table = driver.findElement(By.name("tblT1DOC_HDR"));
			List <WebElement> allRows = table.findElements(By.tagName("tr"));
			
			//And Iterate over them getting the cells
			for (WebElement row : allRows) 
			{
				 List<WebElement> cells = row.findElements(By.tagName("td"));
				 for (WebElement cell : cells) 
				 {
					String sDocName = cell.getText();
					if(sDocName.equalsIgnoreCase(docName))
					{
						return true;
					}
					
				 }
			}
			return false;
			
		}
		
		public void fnBypassApprovalandSubmit() throws InterruptedException
		{
			
			//Click button Workflow
			fnClickElement("id", "yui-gen13");
			
			
			//Click Bypass Approvals
			fnClickElement("id", "DocumentActionBypassApprovals");
			
			//Click submit Button
			fnClickElement("id","DocumentActionSubmit");
					
					
		}
			
		public boolean isDocValidateSuccess()
		{
			element = driver.findElement(By.id("MsgTxt"));
			
			if(element.getText().contains("Document validated successfully"))
			{
				return true;
			}
			else
			{
				return false;
			}
					
			
		}
	
		public boolean isLoginAdvantage() throws InterruptedException
		{
			oLog.push("clsTestExecutor.isLoginAdvantage");
			if(fnOpenApp(Global.sAdvantageURL))
			{
				//Input UserID
	 			fnSetText("id","login",Global.sAdvantageUserID);
	 			
	 			//Input Password
	 			fnSetText("id","password",Global.sAdvantagePwd);
	 			
	 			//Click Login Button
	 			fnClickElement("id","vslogin");
	 			oLog.pop("clsTestExecutor.isLoginAdvantage");
	 			return true;
			}
			else
			{
				oLog.pop("clsTestExecutor.isLoginAdvantage");
				return false;
			}
		}


		//Set the Browser Properties
		public void setBrowserProperty(String sBrowserName)
		{
		//Declare local Variables
		DesiredCapabilities iCapabilities;
		//Check if Browser is IE
			if (sBrowserName.equalsIgnoreCase("IE"))
			{
				iCapabilities = DesiredCapabilities.internetExplorer();
				iCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				File file = new File(Global.sBrowserDriverPath + "IEDriverServer.exe");
				System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
				driver = new InternetExplorerDriver(iCapabilities);
				
			}
			else if(sBrowserName.equalsIgnoreCase("Chrome"))
			{
				iCapabilities = DesiredCapabilities.chrome();
				File file = new File(Global.sBrowserDriverPath + "chromedriver.exe");
				System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
				driver = new ChromeDriver(iCapabilities);
			}
			else if(sBrowserName.equalsIgnoreCase("Fire Fox"))
			{
				iCapabilities = DesiredCapabilities.firefox();
				driver = new FirefoxDriver();
			}
		
			oResult.setDriver(driver);
		}
	
		//Check if Test Data Sheet Exists
		public boolean checkTestDataSheetExists() throws IOException
		{
				fso = new FileInputStream(Global.sControllerFilePath);
				wObj = new HSSFWorkbook(fso);
				sheetObj = wObj.getSheet(sTCName);
				if(!sheetObj.equals(null))
				{
					return true;
				}
				else
				{
					return false;
				}
		
		}
	
		
		//Set TextBox

		/* /***********Utility Methods ******************* 
		 * 	
		 ************************************************/
		//Open Application
		public boolean fnOpenApp(String sURL)
		{
			oLog.push("clsTestExecutor.fnOpenApp");
			if(!sURL.isEmpty())
			{
				driver.get(sURL);
				//fnMaxWindow();
				oResult.setResults(sTestCaseID,sTCName, "Pass","Opened URL:" + sURL);
				oLog.pop ("clsTestExecutor.fnOpenApp");
				return true;
			}
			else
			{
				oResult.setResults(sTestCaseID,sTCName, "Fail", "URL string is Empty");
				oLog.pop ("clsTestExecutor.fnOpenApp");
				return false;
			}
			
			
		}
		
		//maximize Window
		public void fnMaxWindow()
		{
			driver.manage().window().maximize();
		}
		//Set Text in Textbox
		public void fnSetText(String sUniqueProperty, String sIdentifier,String sInputValue)
		{
			oLog.push("clsTestExecutor.fnSetText");
			if(!sInputValue.isEmpty())
			{
				if(sUniqueProperty.equalsIgnoreCase("id"))
				{
					element =driver.findElement(By.id(sIdentifier));
					element.clear();
					element.sendKeys(sInputValue);
					oResult.setResults(sTestCaseID,sTCName, "Pass","Set " + sInputValue + " in TextBox");
				}
				
			}
			else
			{
				oResult.setResults(sTestCaseID,sTCName, "Fail","Input Value is Blank");
			}
			oLog.pop ("clsTestExecutor.fnSetText");
			
		}
		
		//Click Button
		public void fnClickElement(String sUniqueProperty, String sIdentifier) throws InterruptedException
		{
			oLog.push("clsTestExecutor.fnClickElement");
			if(sUniqueProperty.equalsIgnoreCase("id"))
			{
				WebElement oWebButton =driver.findElement(By.id(sIdentifier));
				oWebButton.click();
				if(isAlertPresent())
				{
					driver.switchTo().alert().accept();
				}
				oResult.setResults(sTestCaseID,sTCName, "Pass","Web Element  " + sIdentifier + " clicked successfully");
			}
			else if(sUniqueProperty.equalsIgnoreCase("xpath"))
			{
				WebElement oWebButton = driver.findElement(By.xpath(sIdentifier));
				oWebButton.click();
				oResult.setResults(sTestCaseID,sTCName, "Pass","Web Element  " + sIdentifier + " clicked successfully");
			}
			oLog.pop("clsTestExecutor.fnClickElement");
			Thread.sleep(1000);
		}
		
		//Click Link
		public void fnClickLink(String sUniqueProperty, String sIdentifier) throws InterruptedException
		{
			oLog.push("clsTestExecutor.fnClickLink");
			if(sUniqueProperty.equalsIgnoreCase("id"))
			{
				WebElement oWebLink =driver.findElement(By.id(sIdentifier));
				oWebLink.click();
				if(isAlertPresent())
				{
					driver.switchTo().alert().accept();
				}
				oResult.setResults(sTestCaseID,sTCName, "Pass","Web Link  " + sIdentifier + " clicked successfully");
			}
			else if(sUniqueProperty.equalsIgnoreCase("xpath"))
			{
				WebElement oWebLink = driver.findElement(By.xpath(sIdentifier));
				oWebLink.click();
				oResult.setResults(sTestCaseID,sTCName, "Pass","Web Link  " + sIdentifier + " clicked successfully");
			}
			else if(sUniqueProperty.equalsIgnoreCase("linkText"))
			{
				WebElement oWebLink = driver.findElement(By.linkText(sIdentifier));
				oWebLink.click();
				oResult.setResults(sTestCaseID,sTCName, "Pass","Web Link  " + sIdentifier + " clicked successfully");
			}
			
			oLog.pop("clsTestExecutor.fnClickLink");
			Thread.sleep(1000);
		}
		
		
		
		public boolean isAlertPresent() 
		{ 
		    try 
		    { 
		        driver.switchTo().alert(); 
		        return true; 
		    }   // try 
		    catch (NoAlertPresentException Ex) 
		    { 
		        return false; 
		    }   // catch 
		}
		public String switchWindow()
		{
			
	        try {
	        	   	for(String winHandle : driver.getWindowHandles()){
	        	   		driver.switchTo().window(winHandle);
	        	}
	        }catch(Exception e){
	        	return "Unable to Switch Window" + e.getMessage();
	        }
	        return "Success";
	    }
		public void switchFrame(String sFrame){
			driver.switchTo().frame(sFrame);
		}
		public void fnClickSearch() throws InterruptedException
		{
			List <WebElement> eList = driver.findElements(By.className("folderonly"));
			for(int i=0; i<eList.size();i++)
			{
				String str, webElementText;
				str = eList.get(i).getAttribute("id");
				webElementText = driver.findElement(By.id(str)).getText().trim();
				if(webElementText.equalsIgnoreCase("Search"))
				{
					element= driver.findElement(By.id(str));
					element.click();
					Thread.sleep(1500);
					break;
				}
				
			}
		}
		public void fnClickDocumentCatalogue() throws InterruptedException
		{
			List <WebElement> eList = driver.findElements(By.xpath("//a[contains(@title,'Document Catalog')]"));
			for(int i=0; i<eList.size();i++)
			{
				String str, webElementText;
				str = eList.get(i).getAttribute("id");
				webElementText = driver.findElement(By.id(str)).getText().trim();
				if(webElementText.equalsIgnoreCase("Document Catalog"))
				{
					element= driver.findElement(By.id(str));
					element.click();
					Thread.sleep(1500);
					break;
				}
				
			}
		}
		public void fnClickTab(String sTabName) throws InterruptedException
		{
			List <WebElement> eList = driver.findElements(By.xpath("//span"));
			
			for(WebElement ele:eList)
			{
				String str;
				str = ele.getText();
				//webElementText = driver.findElement(By.id(str)).getText().trim();
				if(str.equalsIgnoreCase(sTabName))
				{
					ele.click();
					Thread.sleep(1500);
					break;
				}
					
			}
		}
		public void fnSelectText(String sUniqueProperty, String sIdentifier,String sInputValue)
		{
			oLog.push("clsTestExecutor.fnSelectText");
			if(!sInputValue.isEmpty())
			{
				if(sUniqueProperty.equalsIgnoreCase("id"))
				{
					WebElement oTextBox =driver.findElement(By.id(sIdentifier));
					
					oTextBox.sendKeys(sInputValue);
					oResult.setResults(sTestCaseID,sTCName, "Pass","Set " + sInputValue + " in dropdown");
				}
				
			}
			else
			{
				oResult.setResults(sTestCaseID,sTCName, "Fail","Input Value is Blank");
			}
			oLog.pop ("clsTestExecutor.fnSelectText");
			
		}

}
