package com.humuson.selenium.MySite.SiteMngmt;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� MySite
 * @�ߺз� ����Ʈ ���� (����Ʈ ���� & ����Ʈ ���,����,����)
 * */
public class B_SiteMngmt extends Scenario {
	public B_SiteMngmt(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		addChildScenario(new E_ClickMySite(E, category, "MySite �޴� Ŭ��"));
		addChildScenario(new E_ClickNewSite(E, category, "�ű� ����Ʈ ��� ��ư Ŭ��"));
		addChildScenario(new E_RegisterSite(E, category, "����Ʈ ���"));
		addChildScenario(new E_ModifySite(E, category, "����Ʈ ����"));
		addChildScenario(new E_DeleteSite(E, category, "����Ʈ ����"));
	}
	
	protected void DO() {
		execute();
	}
}
