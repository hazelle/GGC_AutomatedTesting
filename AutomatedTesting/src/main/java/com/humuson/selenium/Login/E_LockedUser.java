package com.humuson.selenium.Login;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * ��з� : �α���
 * �ߺз� : �α���
 * �ó����� �� : ���ó���� ������ ���
 * */
public class E_LockedUser extends Scenario {
	public E_LockedUser(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
}

