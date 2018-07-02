package com.humuson.selenium.System.Setting.MonitoringServer;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 환경설정
 * @소분류 모니터링 서버
 */
public class C_MonitoringServer extends Scenario {
	public C_MonitoringServer(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		addChildScenario(new E_ClickMntrSvr(E, category, "모니터링 서버 메뉴 클릭"));
		addChildScenario(new E_RegistrMntrSvrList(E, category, "모니터링 서버 리스트 등록"));
		addChildScenario(new E_ModifyMntrSvrList(E, category, "모니터링 서버 리스트 수정"));
		addChildScenario(new E_DeleteMntrSvrList(E, category, "모니터링 서버 리스트 삭제"));
		addChildScenario(new E_AddMntrSvr(E, category, "모니터링 서버 추가"));
		
	}
	
	protected void DO() {
		execute();
	}
}
