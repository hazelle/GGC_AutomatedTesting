package com.humuson.support;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;

import com.humuson.selenium.StartTesting;

public class ControlPage extends CustomLogging {

	private static WebDriver driver = StartTesting.driver;
	private PropRead pr;
	private String baseUrl;

	public ControlPage() throws IOException {
		pr = StartTesting.pr;

		baseUrl = pr.getPropValue("base.url");
	}

	public boolean movePage(String tail) {
		driver.get(baseUrl + tail);

		return checkPage(tail);
	}

	public boolean movePage(String url, int check) {
		driver.get(url);
		
		return checkPage(url, check);
	}
	
	public boolean checkPage(String tail) {
//		System.out.println("*** current url : " + driver.getCurrentUrl());
//		System.out.println("***        tail : " + baseUrl+tail);
		if (driver.getCurrentUrl().equals(baseUrl + tail)) {
//			simpleLog(baseUrl+tail+"창 오픈 완료");
			return true;
		} else { return false; }
	}
	
	public boolean checkPage(String url, int check) {
		if (driver.getCurrentUrl().equals(url))
			return true;
		else
			return false;
	}
	
	public boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			logAlertText(dividedAlertText(driver.switchTo().alert().getText()));
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public void dismissAlert() {
		if(isAlertPresent()) {
			driver.switchTo().alert().dismiss();
		} else {}
	}
	
	public void acceptAlert() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if(isAlertPresent()) {
			driver.switchTo().alert().accept();
		} else {}
	}
	
	public boolean isAlertPresent(int no) {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public void dismissAlert(int no) {
		if(isAlertPresent(no)) {
			driver.switchTo().alert().dismiss();
		} else {}
	}
	
	public void acceptAlert(int no) {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		if(isAlertPresent(no)) {
			driver.switchTo().alert().accept();
		} else {}
	}
	
	public String getAlertText() {
		if(isAlertPresent()) {
			return driver.switchTo().alert().getText();
		} else {
			return null;
		}
	}
	
	public void logAlertText() {
		String[] dividedAlert = dividedAlertText(getAlertText());
		simpleLog("    ┌────────────────────────────────────────┐"); // -가 40개
		for(int i=0; i<dividedAlert.length; i++) {
			simpleLog("       "+String.format("%-36s", dividedAlert[i])+"  ");
		}
		simpleLog("    └────────────────────────────────────────┘"); // -가 40개
	}
	
	public void logAlertText(String[] dividedAlert) {
		simpleLog("    ┌────────────────────────────────────────┐"); // -가 40개
		for(int i=0; i<dividedAlert.length; i++) {
			simpleLog("       "+String.format("%-36s", dividedAlert[i])+"  ");
		}
		simpleLog("    └────────────────────────────────────────┘"); // -가 40개
	}

	public String[] dividedAlertText(String alertText) {
		return alertText.split("\\r?\\n");
	}
	
	public boolean detectLogin() {
		// /login 으로 이동이 가능하다 = 로그인하지 않은 상태
		// /login 으로 이동이 불가능하다 = 로그인한 상태
		return !movePage("/login");
	}
	
//	// 신규 사이트 등록 페이지로 이동
//	public void goToRegistSite(String[] strInput, boolean[] boolInput, String[] userInput) throws IOException {
//		StartTesting.driver.get(pr.getPropValue("base.url") + "/site/regist");
//		webLogging(driver);
//		if (driver.getCurrentUrl().equals(pr.getPropValue("base.url") + "/site/regist")) {
//			new RegistSite(strInput, boolInput, userInput);
//		} else {
//			logging("페이지 이동 실패 (신규 사이트 등록)", 0, false);
//		}
//	}
//
//	public void selectSite() {
//		driver.get(pr.getPropValue("base.url") + "/site/index?directYN=N");
//		List<WebElement> siteList = driver.findElements(By.cssSelector(".btn.btn-primary2.btn-lg.btn-block"));
//		WebElement click = siteList.get(0);
//		click.click();
//	}

}
