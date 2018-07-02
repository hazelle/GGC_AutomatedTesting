package com.humuson.selenium.System.Setting.MonitoringServer;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� ȯ�漳��
 * @�Һз� ����͸� ����
 */
public class C_MonitoringServer extends Scenario {
	public C_MonitoringServer(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		addChildScenario(new E_ClickMntrSvr(E, category, "����͸� ���� �޴� Ŭ��"));
		addChildScenario(new E_RegistrMntrSvrList(E, category, "����͸� ���� ����Ʈ ���"));
		addChildScenario(new E_ModifyMntrSvrList(E, category, "����͸� ���� ����Ʈ ����"));
		addChildScenario(new E_DeleteMntrSvrList(E, category, "����͸� ���� ����Ʈ ����"));
		addChildScenario(new E_AddMntrSvr(E, category, "����͸� ���� �߰�"));
		
	}
	
	protected void DO() {
		execute();
	}
}
