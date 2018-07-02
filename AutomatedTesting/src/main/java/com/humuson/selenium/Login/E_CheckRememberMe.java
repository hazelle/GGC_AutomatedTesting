package com.humuson.selenium.Login;

import com.humuson.support.LoginFunction;
import com.humuson.support.LogoutFunction;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� �α���
 * @�ߺз� �α���
 * @�ó������� remember me üũ
 */
public class E_CheckRememberMe extends Scenario {
	public E_CheckRememberMe(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		new LogoutFunction();
		click();
		CHECK(cp.detectLogin());
	}

	private void click() {
		FEB("xpath", "//*[@id=\"loginForm\"]/fieldset/div[2]/div[2]/div/input", "remember me üũ").click();
		OK("remember me üũ");
		new LoginFunction();
	}
}

