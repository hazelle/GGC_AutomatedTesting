package com.humuson.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.humuson.selenium.StartTesting;

/**
 * �α׾ƿ�
 */
public class LogoutFunction extends Scenario {
	protected static WebDriver driver = StartTesting.driver;

	protected static PropRead pr = StartTesting.pr;
	protected static InputInfo ii = StartTesting.ii;
	protected static ControlPage cp = StartTesting.cp;

	public LogoutFunction() {
		this.title = "*�α׾ƿ�*";
		logout();
	}

	private void logout() {
		if (cp.detectLogin()) {
			WebElement w = FEB("xpath", "/html/body/header/header/div/div[2]/div/a[2]", "������� ��Ӹ޴�");
			do {
				w.click();
			} while (w.getAttribute("aria-expanded").equals("false"));
			FEB("xpath", "/html/body/header/header/div/div[2]/div/ul/li[10]/a", "�α׾ƿ� �޴�").click();
			
			if(cp.checkPage("/login")) {
				OK();
			} else {
				FAIL("�α׾ƿ�", driver.getCurrentUrl());
			}
		}
	}
	
	
}
