package com.humuson.selenium.Login;

import com.humuson.support.LoginFunction;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * 대분류 : 로그인
 * 중분류 : 로그인
 * 시나리오 명 : 아이디, 패스워드 입력하여 로그인 성공
 * */
public class E_InputIDPWAndLogin extends Scenario {
	public E_InputIDPWAndLogin(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
		
	protected void DO() {
		new LoginFunction();
		CHECK(cp.detectLogin());
	}
}

