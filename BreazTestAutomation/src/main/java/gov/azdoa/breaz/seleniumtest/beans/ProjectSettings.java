package gov.azdoa.breaz.seleniumtest.beans;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="ProjectSettings")
public class ProjectSettings {
	

	private String projectName;
	private String projectVersion;
	private String defaultAgentTimeout;
	private String controllerFilePath;
	private String writeDebugLog;
	private String browserdriverpath;
	private String sRootDirPath;
	private String sAdvantageURL;
	private String sAdvantageUserID;
	private String sAdvantagePwd;
	
	public String getsAdvantageUserID() {
		return sAdvantageUserID;
	}

	@XmlElement(name="AdvUserID")
	public void setsAdvantageUserID(String sAdvantageUserID) {
		this.sAdvantageUserID = sAdvantageUserID;
	}

	public String getsAdvantagePwd() {
		return sAdvantagePwd;
	}

	@XmlElement(name="AdvPwd")
	public void setsAdvantagePwd(String sAdvantagePwd) {
		this.sAdvantagePwd = sAdvantagePwd;
	}
	

	public String getsAdvantageURL() {
		return sAdvantageURL;
	}


	@XmlElement(name="AdvURL")
	public void setsAdvantageURL(String sAdvantageURL) {
		this.sAdvantageURL = sAdvantageURL;
	}

	public String getBrowserdriverpath() {
		return browserdriverpath;
	}
	
	@XmlElement(name="BrowserDriverPath")
	public void setBrowserdriverpath(String browserdriverpath) {
		this.browserdriverpath = browserdriverpath;
	}
	public String getProjectName() {
		return projectName;
	}
	@XmlElement(name="ProjectName")
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectVersion() {
		return projectVersion;
	}
	@XmlElement(name="ProjectVersion")
	public void setProjectVersion(String projectVersion) {
		this.projectVersion = projectVersion;
	}
	public String getDefaultAgentTimeout() {
		return defaultAgentTimeout;
	}
	@XmlElement(name="DefaultAgentTimeout")
	public void setDefaultAgentTimeout(String defaultAgentTimeout) {
		this.defaultAgentTimeout = defaultAgentTimeout;
	}
	public String getControllerFilePath() {
		return controllerFilePath;
	}
	@XmlElement(name="ControllerFilePath")
	public void setControllerFilePath(String controllerFilePath) {
		this.controllerFilePath = controllerFilePath;
	}
	
	public String isWriteDebugLog() {
		return writeDebugLog;
	}
	@XmlElement(name="WriteDebugLog")
	public void setWriteDebugLog(String writeDebugLog) {
		this.writeDebugLog = writeDebugLog;
	}

	public String getsRootDirPath() {
		return sRootDirPath;
	}
	@XmlElement(name="RootDir")
	public void setsRootDirPath(String sRootDirPath) {
		this.sRootDirPath = sRootDirPath;
	}


}
