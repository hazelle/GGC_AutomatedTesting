package com.humuson.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.humuson.selenium.StartTesting;

/**
 * 로그아웃
 */
public class LogoutFunction extends Scenario {
	protected static WebDriver driver = StartTesting.driver;

	protected static PropRead pr = StartTesting.pr;
	protected static InputInfo ii = StartTesting.ii;
	protected static ControlPage cp = StartTesting.cp;

	public LogoutFunction() {
		this.title = "*로그아웃*";
		logout();
	}

	private void logout() {
		if (cp.detectLogin()) {
			WebElement w = FEB("xpath", "/html/body/header/header/div/div[2]/div/a[2]", "우측상단 드롭메뉴");
			do {
				w.click();
			} while (w.getAttribute("aria-expanded").equals("false"));
			FEB("xpath", "/html/body/header/header/div/div[2]/div/ul/li[10]/a", "로그아웃 메뉴").click();
			
			if(cp.checkPage("/login")) {
				OK();
			} else {
				FAIL("로그아웃", driver.getCurrentUrl());
			}
		}
	}
	
	
}
