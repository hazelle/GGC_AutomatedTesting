package com.humuson.selenium.System.Setting;

import com.humuson.selenium.System.Setting.DomainFilter.C_DomainFilter;
import com.humuson.selenium.System.Setting.MonitoringServer.C_MonitoringServer;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� ȯ�漳��
 */
public class B_Setting extends Scenario {
	
	public B_Setting(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		addChildScenario(new C_DomainFilter(C, category, "������ ����"));
		addChildScenario(new C_MonitoringServer(C, category, "����͸� ����"));
	}
	
	protected void DO() {
		execute();
	}
}
