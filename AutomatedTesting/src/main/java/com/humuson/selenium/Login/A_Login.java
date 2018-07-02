package com.humuson.selenium.Login;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� �α���
 * */
public class A_Login extends Scenario {
	
	public A_Login(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		addChildScenario(new E_InputIDPWAndLogin(E, category, "���̵�, �н����� �Է��Ͽ� �α��� ����"));
		addChildScenario(new E_CheckRememberMe(E, category, "remember me üũ"));
		addChildScenario(new E_Logout(E, category, "�α׾ƿ� ��ư�� ���� �α׾ƿ� ����"));
		addChildScenario(new E_NonexistentUser(E, category, "�������� �ʴ� ������� ���"));
		addChildScenario(new E_WrongPassword(E, category, "��й�ȣ Ʋ�� ���"));
//		addChildScenario(new E_LockedUser("���ó���� ������ ���"));
	}
	
	protected void DO() {
		if(cp.movePage("/login")) {
			execute();
		}
	}
}

