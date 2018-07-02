package com.humuson.selenium.System;

import com.humuson.selenium.System.CodeManagement.B_CodeManagement;
import com.humuson.selenium.System.Setting.B_Setting;
import com.humuson.support.LoginFunction;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * */
public class A_System extends Scenario {
	public A_System(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		new LoginFunction();
		addChildScenario(new B_CodeManagement(B, category, "�ڵ� ����"));
		addChildScenario(new B_Setting(B, category, "ȯ�漳��"));
	}
	
	protected void DO() {
		execute();
	}
}