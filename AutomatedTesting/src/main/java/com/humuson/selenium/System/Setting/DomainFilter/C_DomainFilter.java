package com.humuson.selenium.System.Setting.DomainFilter;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� ȯ�漳��
 * @�Һз� ������ ����
 */
public class C_DomainFilter extends Scenario {
	public C_DomainFilter(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		addChildScenario(new E_ClickDomainFilter(E, category, "������ ���� �޴� Ŭ��"));
		addChildScenario(new E_RegisterDomain(E, category, "������ ���� ���"));
		addChildScenario(new E_SearchDomain(E, category, "������ ���� �˻�"));
		addChildScenario(new E_ModifyDomain(E, category, "������ ���� ����"));
		addChildScenario(new E_DeleteDomain(E, category, "������ ���� ����"));
	}

	protected void DO() {
		execute();
	}
	
	
}
