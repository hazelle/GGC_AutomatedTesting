package com.humuson.selenium.MySite.SiteMngmt;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 MySite
 * @중분류 사이트 관리 (사이트 선택 & 사이트 등록,수정,삭제)
 * */
public class B_SiteMngmt extends Scenario {
	public B_SiteMngmt(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		addChildScenario(new E_ClickMySite(E, category, "MySite 메뉴 클릭"));
		addChildScenario(new E_ClickNewSite(E, category, "신규 사이트 등록 버튼 클릭"));
		addChildScenario(new E_RegisterSite(E, category, "사이트 등록"));
		addChildScenario(new E_ModifySite(E, category, "사이트 수정"));
		addChildScenario(new E_DeleteSite(E, category, "사이트 삭제"));
	}
	
	protected void DO() {
		execute();
	}
}
