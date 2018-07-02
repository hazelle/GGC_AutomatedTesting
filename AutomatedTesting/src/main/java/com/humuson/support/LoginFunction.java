package com.humuson.support;

import org.openqa.selenium.WebDriver;

import com.humuson.selenium.StartTesting;

/**
 * �α���
 */
public class LoginFunction extends Scenario {
	protected static WebDriver driver = StartTesting.driver;

	protected static PropRead pr = StartTesting.pr;
	protected static InputInfo ii = StartTesting.ii;
	protected static ControlPage cp = StartTesting.cp;

	private String ID = ii.getStrArray().get(0)[2];
	private String PW = ii.getStrArray().get(0)[3];

	public LoginFunction() {
		this.title = "*�α���*";		
		login();
	}

	public LoginFunction(String ID, String PW) {
		this.title = "*�α���*";
		this.ID = ID;
		this.PW = PW;
	}

	private void login() {
		if (cp.movePage("/login")) {
			FEB("xpath", "//*[@id=\"j_username\"]", "ID �Է�ĭ").sendKeys(ID);
			FEB("id", "j_password", "PW �Է�ĭ").sendKeys(PW);
			FEB("id", "j_password", "�α��� ��ư").submit();
			cp.dismissAlert();
			if(cp.checkPage("/dashboard/index")) {
				OK();
			} else {
				FAIL("�α���", driver.getCurrentUrl());
			}
		} else {
			ETC("O", "�̹� �α����� ����");
		}
	}
	
	public String login2() {
		if (cp.checkPage("/login")) {
			FEB("xpath", "//*[@id=\"j_username\"]", "ID �Է�ĭ").sendKeys(ID);
			FEB("id", "j_password", "PW �Է�ĭ").sendKeys(PW);
			FEB("id", "j_password", "�α��� ��ư").submit();
			return cp.getAlertText();
		} else {
			FAIL("�α��� �������� �̵�", driver.getCurrentUrl());
			return null;
		}
	}
}

