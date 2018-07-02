package com.humuson.selenium.MySite.SiteMngmt;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� MySite
 * @�ߺз� ����Ʈ ����
 * @�ó������� �ű� ����Ʈ ��� ��ư Ŭ��
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
		FEB("xpath", "//*[@id=\"registSite\"]/div", "�ű� ����Ʈ ��� ��ư").click();
	}
}
