package gov.azdoa.breaz.seleniumtest.beans;

public class TestCase {
	
	private String testCaseName;
	private String execute;  // whether or not to execute this test case
	private String testData; // whether or not this test uses test data
	private String browser; // what browser to test with
	private int TestCaseID; //unique TestCaseID corresponding to each Test Case
	
	public int getTestCaseID() {
		return TestCaseID;
	}
	public void setTestCaseID(int testCaseID) {
		TestCaseID = testCaseID;
	}
	public String getTestCaseName() {
		return testCaseName;
	}
	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getExecute() {
		return execute;
	}
	public void setExecute(String execute) {
		this.execute = execute;
	}
	public String getTestData() {
		return testData;
	}
	public void setTestData(String testData) {
		this.testData = testData;
	}
	
	
	
	
}
