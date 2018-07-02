package com.humuson.selenium.System.Setting.DomainFilter;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 환경설정
 * @소분류 도메인 필터
 */
public class C_DomainFilter extends Scenario {
	public C_DomainFilter(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		addChildScenario(new E_ClickDomainFilter(E, category, "도메인 필터 메뉴 클릭"));
		addChildScenario(new E_RegisterDomain(E, category, "도메인 필터 등록"));
		addChildScenario(new E_SearchDomain(E, category, "도메인 필터 검색"));
		addChildScenario(new E_ModifyDomain(E, category, "도메인 필터 수정"));
		addChildScenario(new E_DeleteDomain(E, category, "도메인 필터 삭제"));
	}

	protected void DO() {
		execute();
	}
	
	
}
