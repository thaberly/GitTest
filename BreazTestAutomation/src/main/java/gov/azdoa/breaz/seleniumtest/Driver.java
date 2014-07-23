package gov.azdoa.breaz.seleniumtest;



//import org.openqa.selenium.chrome.ChromeDriver;



public class Driver {
	
	
	public static void main(String[] args) throws Exception
	{
		//Create object of Global Class
		Global oGL = new Global();
		
		// Read XML File
		oGL.readXMLFile();
		
		Log oLog = Log.getInstance();
		
		try{
				//Check If Controller File Exists at the specific location
				if (oLog.CheckFileExist(Global.sControllerFilePath)== true)
					{
												
						//Create object of Controller Class
						Controller oCtrl = new Controller();
						
						
						//Check if File is Not Blank
						if(oCtrl.getNumberOfTestCases() > 0)
						{
							//Create RunID Output folder
							oLog.fnCreateOutputFolder();
							
							//Read TestCase Sheet		
							oCtrl.ReadTestCaseSheet();
							
							//Loop through the Controller File
							 
						}
						else
						{
							oLog.fnWriteError("Controller File is Blank");
						}
					}
				else
					{		
						oLog.fnWriteError("Controller File not found in the specific location" + Global.sControllerFilePath);
					
					}
			
			

		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{
			oLog.close();
		}
		
	}
}

