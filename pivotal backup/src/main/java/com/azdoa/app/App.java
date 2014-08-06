package com.azdoa.app;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class App  {
	
	private static String siteUrl = "https://www.pivotaltracker.com/projects/1032006/overview"; 
	protected static String downloadPath = "S:\\ASET\\BREAZ\\Interfaces\\Pivotal Tracker Backups";
	protected static String projectName = "BREAZ-INTERFACES";
	
    public static void main(String[] args) {
       
    	String FFBinaryPath = args != null && args.length > 0 ? args[0] : "";
    	FirefoxBinary ffbinary = null;
    	
    	if(StringUtils.isNotBlank(FFBinaryPath)) {
    		File ffpath = new File(FFBinaryPath);
    		ffbinary = new FirefoxBinary(ffpath);
    	}
    	
    	//firefox file download params 
        FirefoxProfile fp = new FirefoxProfile();
        fp.setPreference("browser.download.folderList", 2);
        fp.setPreference("browser.download.manager.showWhenStarting",Boolean.FALSE);
        fp.setPreference("browser.download.dir", downloadPath);
        fp.setPreference("browser.helperApps.neverAsk.saveToDisk","text/csv;application/octet-stream");

        // Create a new instance of the Firefox driver
        WebDriver driver;
        if(ffbinary!=null) {
          driver = new FirefoxDriver(ffbinary,fp);
        }else {
        	driver = new FirefoxDriver(fp);
        }
         
        
        navigateAndExport(driver);        
        driver.quit();
    }

	private static void navigateAndExport(WebDriver driver) {
		
        // Navigate to Pivotal Tracker Website
        driver.get(siteUrl);
        System.out.println("Page title is: " + driver.getTitle());

        //Overview page
        List<WebElement> sections = driver.findElements(By.className("content_section"));		
		for(Iterator<WebElement> secIt = sections.iterator(); secIt.hasNext();){
			WebElement section = secIt.next();
			if("Actions".equals(section.findElement(By.tagName("h2")).getText())){
				List<WebElement> actionLinks = section.findElements(By.xpath("//table/tbody/tr[1]/td/a"));
				actionLinks.get(0).click();
				break;
			}
		}
		
		// Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 20)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("export csv");
            }
        });
        
        //Export Page
        System.out.println("Page title is: " + driver.getTitle());		
		List<WebElement> forms = driver.findElements(By.tagName("form"));
		
		for(Iterator<WebElement> formIt = forms.iterator(); formIt.hasNext();){
			WebElement form = formIt.next();
			if(form.getAttribute("action").contains("export")){
				WebElement optionAllEpics = form.findElement(By.xpath("//table/tbody/tr[4]/td[2]/input[@id='options_include_epics']"));
				optionAllEpics.click();				
				form.submit();
				break;
			}
		}
		//Wait 10 sec for the file to finish downloading.
		try {
		    Thread.sleep(10000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		        
	}

}