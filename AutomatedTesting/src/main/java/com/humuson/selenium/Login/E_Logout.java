package com.humuson.selenium.Login;

import com.humuson.support.LogoutFunction;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * ��з� : �α���
 * �ߺз� : �α���
 * �ó����� �� : �α׾ƿ� ��ư�� ���� �α׾ƿ� ����
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
