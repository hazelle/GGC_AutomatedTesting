package com.humuson.selenium.System.CodeManagement.MappingManagement;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� �ڵ� ����
 * @�Һз� ���� ����
 */
public class C_MappingManagement extends Scenario {
	public C_MappingManagement(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		addChildScenario(new E_ClickMManagement(E, category, "���� ���� �޴� Ŭ��"));
		addChildScenario(new E_FillOutAndRegister(E, category, "�ϴ� ���� ������ �Է� �� ��� ��ư Ŭ��"));
		addChildScenario(new E_ClickCodeAndModify(E, category, "�ڵ�� Ŭ��, �ڵ� ����"));
		addChildScenario(new E_DeleteCode(E, category, "���� �ڵ� ���� �� ���� ��ư Ŭ��"));
		addChildScenario(new E_ResetForm(E, category, "�ű� ��ư Ŭ��"));
	}
	
	protected void DO() {
		execute();
	}
}
