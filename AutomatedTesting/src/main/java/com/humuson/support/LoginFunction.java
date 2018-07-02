package com.humuson.support;

import org.openqa.selenium.WebDriver;

import com.humuson.selenium.StartTesting;

/**
 * 로그인
 */
public class LoginFunction extends Scenario {
	protected static WebDriver driver = StartTesting.driver;

	protected static PropRead pr = StartTesting.pr;
	protected static InputInfo ii = StartTesting.ii;
	protected static ControlPage cp = StartTesting.cp;

	private String ID = ii.getStrArray().get(0)[2];
	private String PW = ii.getStrArray().get(0)[3];

	public LoginFunction() {
		this.title = "*로그인*";		
		login();
	}

	public LoginFunction(String ID, String PW) {
		this.title = "*로그인*";
		this.ID = ID;
		this.PW = PW;
	}

	private void login() {
		if (cp.movePage("/login")) {
			FEB("xpath", "//*[@id=\"j_username\"]", "ID 입력칸").sendKeys(ID);
			FEB("id", "j_password", "PW 입력칸").sendKeys(PW);
			FEB("id", "j_password", "로그인 버튼").submit();
			cp.dismissAlert();
			if(cp.checkPage("/dashboard/index")) {
				OK();
			} else {
				FAIL("로그인", driver.getCurrentUrl());
			}
		} else {
			ETC("O", "이미 로그인한 상태");
		}
	}
	
	public String login2() {
		if (cp.checkPage("/login")) {
			FEB("xpath", "//*[@id=\"j_username\"]", "ID 입력칸").sendKeys(ID);
			FEB("id", "j_password", "PW 입력칸").sendKeys(PW);
			FEB("id", "j_password", "로그인 버튼").submit();
			return cp.getAlertText();
		} else {
			FAIL("로그인 페이지로 이동", driver.getCurrentUrl());
			return null;
		}
	}
}

