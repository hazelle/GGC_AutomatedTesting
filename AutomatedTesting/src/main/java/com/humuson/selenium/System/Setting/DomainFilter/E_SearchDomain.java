package com.humuson.selenium.System.Setting.DomainFilter;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 환경설정
 * @소분류 도메인 필터
 * @시나리오명 도메인 필터 검색
 */
public class E_SearchDomain extends Scenario {
	public E_SearchDomain(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		
	}
}
