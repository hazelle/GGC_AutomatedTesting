package com.humuson.selenium.System.Setting.DomainFilter;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� ȯ�漳��
 * @�Һз� ������ ����
 * @�ó������� ������ ���� ����
 */
public class E_DeleteDomain extends Scenario {
	public E_DeleteDomain(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		
	}
}
