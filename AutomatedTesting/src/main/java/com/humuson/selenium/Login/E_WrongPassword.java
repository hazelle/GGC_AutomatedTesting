package com.humuson.selenium.Login;

import java.util.regex.Pattern;

import com.humuson.support.LoginFunction;
import com.humuson.support.LogoutFunction;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * 대분류 : 로그인
 * 중분류 : 로그인
 * 시나리오 명 : 비밀번호 틀린 경우
 * */
public class E_WrongPassword extends Scenario {
	private String ID = ii.getStrArray().get(0)[2];
	private String PW = "zzzzz123456";
	
	public E_WrongPassword(int type, String[] category, String title) {
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
		if(Pattern.compile("로그인에").matcher(alert).find()) {
			OK();
		} else {
			FAIL("(ID : "+ID+", PW : "+PW+")");
		}
	}
}
