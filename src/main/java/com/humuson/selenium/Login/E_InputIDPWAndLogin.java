package com.humuson.selenium.Login;

import com.humuson.support.LoginFunction;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * ��з� : �α���
 * �ߺз� : �α���
 * �ó����� �� : ���̵�, �н����� �Է��Ͽ� �α��� ����
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

