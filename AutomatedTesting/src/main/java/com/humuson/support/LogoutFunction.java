package com.humuson.support;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.humuson.selenium.StartTesting;

/**
<<<<<<< HEAD
 * 占싸그아울옙
=======
 * 로그아웃
>>>>>>> 564511cdf5ddc160a9654db86be9ad4e6e56e939
 */
public class LogoutFunction extends Scenario {
	protected static WebDriver driver = StartTesting.driver;

	protected static PropRead pr = StartTesting.pr;
	protected static InputInfo ii = StartTesting.ii;
	protected static ControlPage cp = StartTesting.cp;

	public LogoutFunction() {
<<<<<<< HEAD
		this.title = "*占싸그아울옙*";
=======
		this.title = "*로그아웃*";
>>>>>>> 564511cdf5ddc160a9654db86be9ad4e6e56e939
		logout();
	}

	private void logout() {
		if (cp.detectLogin()) {
<<<<<<< HEAD
			WebElement w = FEB("xpath", "//div[@id='header2']/div/div[2]/div/a[2]", "占쏙옙占쏙옙占쏙옙占� 占쏙옙疸濱占�");
=======
			WebElement w = FEB("xpath", "/html/body/header/header/div/div[2]/div/a[2]", "우측상단 드롭메뉴");
>>>>>>> 564511cdf5ddc160a9654db86be9ad4e6e56e939
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
<<<<<<< HEAD
			w = FEB("linkText", "로그아웃", "로그아웃");
			w.click();
=======
			FEB("xpath", "/html/body/header/header/div/div[2]/div/ul/li[10]/a", "로그아웃 메뉴").click();
>>>>>>> 564511cdf5ddc160a9654db86be9ad4e6e56e939
			
			if(cp.checkPage("/login")) {
				OK();
			} else {
<<<<<<< HEAD
				FAIL("占싸그아울옙", driver.getCurrentUrl());
=======
				FAIL("로그아웃", driver.getCurrentUrl());
>>>>>>> 564511cdf5ddc160a9654db86be9ad4e6e56e939
			}
		}
	}
	
	
}
