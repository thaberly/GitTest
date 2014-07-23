package gov.azdoa.breaz.seleniumtest;


import gov.azdoa.breaz.seleniumtest.beans.ProjectSettings;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;






import java.io.File;

public class Global 
{

	//Globals
	public static String sControllerFilePath,sProjectName,sWriteDebugLog,sProjVer,sBrowserDriverPath,sRootDirPath,sAdvantageURL, sAdvantageUserID,sAdvantagePwd;
		
	static String  xmlFilePath = "C:\\BreazAutomation\\Input\\config.xml";
	
	//Parse the XML File to load the Database Parameters  controller File Path
	public void readXMLFile() throws Exception, IOException
	{	
		File file = new File(xmlFilePath);
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(ProjectSettings.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ProjectSettings projectSettings = (ProjectSettings) jaxbUnmarshaller.unmarshal(file);
			
			sControllerFilePath = projectSettings.getControllerFilePath();
			sProjectName  = projectSettings.getProjectName();
			sWriteDebugLog = projectSettings.isWriteDebugLog();
			sProjVer = projectSettings.getProjectVersion();	
			sBrowserDriverPath = projectSettings.getBrowserdriverpath();
			sRootDirPath=projectSettings.getsRootDirPath();
			sAdvantageURL = projectSettings.getsAdvantageURL();
			sAdvantageUserID = projectSettings.getsAdvantageUserID();
			sAdvantagePwd = projectSettings.getsAdvantagePwd();
	 }catch(Exception e) {
		 e.printStackTrace(); 
	 }
	}
}
