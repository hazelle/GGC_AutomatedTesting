package com.humuson.selenium.MySite.SiteMngmt;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 MySite
 * @중분류 사이트 관리
 * @시나리오명 신규 사이트 등록 버튼 클릭
 * */
public class E_ClickNewSite extends Scenario {
	public E_ClickNewSite(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		click();
		CHECK(cp.checkPage("/site/regist"));
	}
	
	private void click() {
		FEB("xpath", "//*[@id=\"registSite\"]/div", "신규 사이트 등록 버튼").click();
	}
}
