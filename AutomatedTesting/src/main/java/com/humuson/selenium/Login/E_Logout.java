package com.humuson.selenium.Login;

import com.humuson.support.LogoutFunction;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * 대분류 : 로그인
 * 중분류 : 로그인
 * 시나리오 명 : 로그아웃 버튼을 눌러 로그아웃 성공
 * */
public class E_Logout extends Scenario {
	public E_Logout(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		new LogoutFunction();
		
		CHECK(!cp.detectLogin());
	}
}
