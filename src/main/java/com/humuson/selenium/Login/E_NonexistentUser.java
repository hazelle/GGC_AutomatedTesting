package com.humuson.selenium.Login;

import java.util.regex.Pattern;

import com.humuson.support.LoginFunction;
import com.humuson.support.LogoutFunction;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * ��з� : �α���
 * �ߺз� : �α���
 * �ó����� �� : �������� �ʴ� ������� ���
 * */
public class E_NonexistentUser extends Scenario {
	private String ID = "zzzzz";
	private String PW = "123";
	
	public E_NonexistentUser(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		String alert = "";
		if(cp.movePage("/login")) {
			alert = new LoginFunction(ID, PW).login2();
		} else {
			new LogoutFunction();
			alert = new LoginFunction(ID, PW).login2();
		}
		cp.dismissAlert(0);
		if(Pattern.compile("��������").matcher(alert).find()) {
			OK();
		} else {
			FAIL("(ID : "+ID+", PW : "+PW+")");
		}
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getPW() {
		return PW;
	}

	public void setPW(String pW) {
		PW = pW;
	}
}