package com.humuson.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.humuson.selenium.StartTesting;

/**
 * 占싸그아울옙
 */
public class LogoutFunction extends Scenario {
	protected static WebDriver driver = StartTesting.driver;

	protected static PropRead pr = StartTesting.pr;
	protected static InputInfo ii = StartTesting.ii;
	protected static ControlPage cp = StartTesting.cp;

	public LogoutFunction() {
		this.title = "*占싸그아울옙*";
		logout();
	}

	private void logout() {
		if (cp.detectLogin()) {
			WebElement w = FEB("xpath", "//div[@id='header2']/div/div[2]/div/a[2]", "占쏙옙占쏙옙占쏙옙占� 占쏙옙疸濱占�");
			do {
				w.click();
				
				try {
					System.out.println(w.getAttribute("aria-expanded"));
					Thread.sleep(1000);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				w = FEB("linkText", "로그아웃", "로그아웃");
				System.out.println(w);
				w.click();
			} while (w.getAttribute("aria-expanded").equals("false"));
			w = FEB("linkText", "로그아웃", "로그아웃");
			w.click();
			
			if(cp.checkPage("/login")) {
				OK();
			} else {
				FAIL("占싸그아울옙", driver.getCurrentUrl());
			}
		}
	}
	
	
}
