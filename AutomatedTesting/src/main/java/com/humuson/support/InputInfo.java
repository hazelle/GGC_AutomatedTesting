package com.humuson.support;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.humuson.selenium.StartTesting;

public class InputInfo {
	
	private PropRead pr = StartTesting.pr;
	
	public static WebDriver driver;
	private ArrayList<String[]> strArray;
	private Map<String, Boolean> channel = new HashMap<String, Boolean>();
	public String[] chns = {"email", "push", "sms", "kakao"};

	private String chromeDriverPath; 	// 0
	private String strurl; 				// 1 - http://119.207.76.92/TMS
	private String strid; 				// 2
	private String strpw; 				// 3
	private String strsitename; 		// 4
	private String strImagePath; 		// 5
	private String senderName;
	private String senderEmail;
	private String returnEmail;
	
	/*private String chromeDriverPath = "C:/App/Selenium/chromedriver.exe"; 	// 0
	private String strurl = "http://127.0.0.1:8080/TMS"; 					// 1 - http://119.207.76.92/TMS
	private String strid = "tmsaaa"; 										// 2
	private String strpw = "humuson123!"; 									// 3
	private String strsitename = "Auto"; 									// 4
	private String strImagePath = "D:/cat.jpg"; 							// 5
	private String senderName = "김희재테스트";
	private String senderEmail = "heejae@humuson.com";
	private String returnEmail = "heejae@humuson.com";*/
	
	public InputInfo() throws IOException {
		this.setters();
		this.setStrArray();
		this.setChannel();
		this.setDriver(strArray.get(0)[0]);
	}

	public void setDriver(String exe) {
		System.setProperty("webdriver.chrome.driver", exe);

		LoggingPreferences logprep = new LoggingPreferences();
		logprep.enable(LogType.BROWSER, Level.ALL);
//		logprep.enable(LogType.CLIENT, Level.ALL);
		logprep.enable(LogType.DRIVER, Level.ALL);
		logprep.enable(LogType.PERFORMANCE, Level.ALL);
		logprep.enable(LogType.PROFILER, Level.ALL);
//		logprep.enable(LogType.SERVER, Level.ALL);
		DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
		desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logprep);

		driver = new ChromeDriver(desiredCapabilities);
		driver.manage().window().maximize();
	}
	
	public WebDriver getDriver() {
		return InputInfo.driver;
	}
	
	public void setStrArray() {
		this.strArray = new ArrayList<String[]>();

		SimpleDateFormat time = new SimpleDateFormat("ddhhmm");
		String currentTime = time.format(new Date());

		String[] strInput = { chromeDriverPath, strurl, strid, strpw, strsitename + currentTime, strImagePath };

		String[] userInput = { senderName + currentTime, senderEmail, returnEmail };

		this.strArray.add(strInput);
		this.strArray.add(userInput);
	}
	
	public ArrayList<String[]> getStrArray() {
		return this.strArray;
	}
	
	public void setChannel() {
		channel.put(chns[0], Boolean.valueOf(this.pr.getPropValue("site.channel.email")).booleanValue()); 	// 0
		channel.put(chns[1], Boolean.valueOf(this.pr.getPropValue("site.channel.push")).booleanValue()); 	// 1
		channel.put(chns[2], Boolean.valueOf(this.pr.getPropValue("site.channel.sms")).booleanValue()); 	// 2
		channel.put(chns[3], Boolean.valueOf(this.pr.getPropValue("site.channel.kakao")).booleanValue()); 	// 3
	}
	
	public void setters() {
		this.setChromeDriverPath(this.pr.getPropValue("base.chromeDriverPath"));
		this.setStrurl(this.pr.getPropValue("base.url"));
		this.setStrid(this.pr.getPropValue("login.id"));
		this.setStrpw(this.pr.getPropValue("login.pw"));
		this.setStrsitename(this.pr.getPropValue("site.name"));
		this.setStrImagePath(this.pr.getPropValue("site.image.path"));
		this.setSenderName(this.pr.getPropValue("site.sender.name"));
		this.setSenderEmail(this.pr.getPropValue("site.sender.email"));
		this.setReturnEmail(this.pr.getPropValue("site.return.email"));
	}
	
	public String getChromeDriverPath() {
		return chromeDriverPath;
	}

	public void setChromeDriverPath(String chromeDriverPath) {
		this.chromeDriverPath = chromeDriverPath;
	}

	public Map<String, Boolean> getChannel() {
		return this.channel;
	}

	public String getStrurl() {
		return strurl;
	}

	public void setStrurl(String strurl) {
		this.strurl = strurl;
	}

	public String getStrid() {
		return strid;
	}

	public void setStrid(String strid) {
		this.strid = strid;
	}

	public String getStrpw() {
		return strpw;
	}

	public void setStrpw(String strpw) {
		this.strpw = strpw;
	}

	public String getStrsitename() {
		return strsitename;
	}

	public void setStrsitename(String strsitename) {
		this.strsitename = strsitename;
	}

	public String getStrImagePath() {
		return strImagePath;
	}

	public void setStrImagePath(String strImagePath) {
		this.strImagePath = strImagePath;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public String getReturnEmail() {
		return returnEmail;
	}

	public void setReturnEmail(String returnEmail) {
		this.returnEmail = returnEmail;
	}

	public void setStrArray(ArrayList<String[]> strArray) {
		this.strArray = strArray;
	}
	
	
	
}
