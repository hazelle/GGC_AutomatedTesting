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
//			simpleLog(baseUrl+tail+"â ���� �Ϸ�");
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
		simpleLog("    ������������������������������������������������������������������������������������"); // -�� 40��
		for(int i=0; i<dividedAlert.length; i++) {
			simpleLog("       "+String.format("%-36s", dividedAlert[i])+"  ");
		}
		simpleLog("    ������������������������������������������������������������������������������������"); // -�� 40��
	}
	
	public void logAlertText(String[] dividedAlert) {
		simpleLog("    ������������������������������������������������������������������������������������"); // -�� 40��
		for(int i=0; i<dividedAlert.length; i++) {
			simpleLog("       "+String.format("%-36s", dividedAlert[i])+"  ");
		}
		simpleLog("    ������������������������������������������������������������������������������������"); // -�� 40��
	}

	public String[] dividedAlertText(String alertText) {
		return alertText.split("\\r?\\n");
	}
	
	public boolean detectLogin() {
		// /login ���� �̵��� �����ϴ� = �α������� ���� ����
		// /login ���� �̵��� �Ұ����ϴ� = �α����� ����
		return !movePage("/login");
	}
	
//	// �ű� ����Ʈ ��� �������� �̵�
//	public void goToRegistSite(String[] strInput, boolean[] boolInput, String[] userInput) throws IOException {
//		StartTesting.driver.get(pr.getPropValue("base.url") + "/site/regist");
//		webLogging(driver);
//		if (driver.getCurrentUrl().equals(pr.getPropValue("base.url") + "/site/regist")) {
//			new RegistSite(strInput, boolInput, userInput);
//		} else {
//			logging("������ �̵� ���� (�ű� ����Ʈ ���)", 0, false);
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
