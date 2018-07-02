package com.humuson.selenium.System.CodeManagement.SCManagement;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System 
 * @�ߺз� �ڵ� ���� 
 * @�Һз� �ý��� �ڵ� ���� 
 */
public class C_SCManagement extends Scenario {
	public C_SCManagement(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}

	protected void makeScenario() {
		addChildScenario(new E_ClickSCManagement(E, category, "�ý����ڵ� ���� �޴� Ŭ��"));
		addChildScenario(new E_FillOutAndRegister(E, category, "�ý��� �ڵ� ���� �Է� ���� ������ �Է� �� ��� ��ư Ŭ��"));
		addChildScenario(new E_CodeTypeNameSearch(E, category, "�ڵ�Ÿ��, �ڵ�� �Է� �� �˻� ��ư Ŭ��"));
		addChildScenario(new E_ClickCodeAndModify(E, category, "�ڵ�� Ŭ��, �ڵ� ����"));
		addChildScenario(new E_DeleteCode(E, category, "�ý��� �ڵ� ���� �� ���� ��ư Ŭ��"));
		addChildScenario(new E_ResetForm(E, category, "�ű� ��ư Ŭ��"));
	}

	protected void DO() {
		execute();
	}
}
