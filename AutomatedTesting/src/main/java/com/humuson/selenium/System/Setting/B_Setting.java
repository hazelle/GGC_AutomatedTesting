package com.humuson.selenium.System.Setting;

import com.humuson.selenium.System.Setting.DomainFilter.C_DomainFilter;
import com.humuson.selenium.System.Setting.MonitoringServer.C_MonitoringServer;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 환경설정
 */
public class B_Setting extends Scenario {
	
	public B_Setting(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		addChildScenario(new C_DomainFilter(C, category, "도메인 필터"));
		addChildScenario(new C_MonitoringServer(C, category, "모니터링 서버"));
	}
	
	protected void DO() {
		execute();
	}
}
