package com.humuson.support;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.humuson.selenium.StartTesting;

public class CustomLogging {
	protected static Logger logger = LoggerFactory.getLogger(StartTesting.class);
	
	public CustomLogging() {
	}
	
	public void logging(String msg) {
		simpleLog(msg);
	}
	
	public void logging(String msg, int type) {		// information sentences
		switch(type) {
		case 0:		// simple log
			simpleLog(msg);
			break;
		case 1:		// detailed log
			simpleLog(msg);
			break;
		case 2:		// error log
			errorLog(msg);
			break;
		case 9:
			popupLog(msg);
			break;
		default:
			logging(msg);
		}
	}
	
	public void logging(String msg, int type, boolean ok) {	// test results
		String result = "";
		if(ok) {	// success
			result = "O";
		} else {
			result = "X";
		}
		
		if(type==0) {
			result += "  ";	    // 띄어쓰기 두 번
		} else {
			result += "     ";	// 띄어쓰기 다섯 번
		}
		logging(result+msg);
	}

	public void simpleLog(String msg) {	
		logger.info(String.format("%-20s", this.getClass().getSimpleName())+" | "+msg);
	}
	
	public void errorLog(String msg) {
		logger.info(String.format("%-20s", this.getClass().getSimpleName())+" | "+msg);
	}

	public void popupLog(String msg) {
		logger.info(String.format("%-20s", this.getClass().getSimpleName())+" | "+msg);
	}
	
	public void headerLog() {
		logging("");
		logging("");
		logging("*** Automated Testing Program START ***");
//		logging("  **   ******   **   *****  ******");
//		logging(" ****  ******  ****  ****** ******");
//		logging("**  **   **   **  ** **  **   **");
//		logging("**       **   **  ** **  **   **");
//		logging("");
//		logging("");
//		logging("");
//		logging("");
//		logging("");
//		logging("");
//		logging("");
	}
	
	// 새로 생긴 팝업을 찾는다. 팝업이 여러개 떠있으면 에러 생길수 있음. * @throws InterruptedException
	public void detectPopup(WebDriver driver) throws InterruptedException {
		Set<String> handles = driver.getWindowHandles(); // get all window
															// handles
		Iterator<String> iterator = handles.iterator();
		String mainWindow = iterator.next();
		String popupWindow = iterator.next();

		driver.switchTo().window(popupWindow);
		logger.info("팝업창: " + driver.getTitle());
		Thread.sleep(1000);
		driver.close();
		driver.switchTo().window(mainWindow);
	}

//	public void webLogging2() {
//		AutoTesting.logEntries = AutoTesting.logs.get(LogType.DRIVER);
//		for (LogEntry le : AutoTesting.logEntries)
//			logger.info(le.getMessage());
//	}
	
	
	
	public void webLogging(WebDriver driver) {
		LogEntries logEntries = driver.manage().logs().get("browser");
		for(LogEntry le : logEntries)
			logger.info(le.getMessage());
	}
	
	public void printWebException(String original) {
		String blank = "                                          ";
		String[] separated = original.split("\\r?\\n");
		String toreturn = separated[0];
		for(int i=1; i<separated.length; i++) {
			toreturn += "\n"+blank+separated[i];
		}
		simpleLog(toreturn);
	}
	
	public String popupLogging(WebDriver driver) {
		String errormsg = driver.switchTo().alert().getText();
//		logging("===> 팝업 창 내용\n\n" + errormsg+"\n", 9);	// 빈칸 26칸  
		return errormsg;
	}
	
	public String getException(Exception e) {
		return e.toString().split("}")[0]+"}";
	}
	
	public void showError(String category, String task, Exception e) {
		logging("X  "+category+" : "+task+"\n"+e.toString(), 2);
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		CustomLogging.logger = logger;
	}
	
	
	
}
