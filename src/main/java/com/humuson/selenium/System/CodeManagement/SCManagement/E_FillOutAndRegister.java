package com.humuson.selenium.System.CodeManagement.SCManagement;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� �ڵ� ����
 * @�Һз� �ý��� �ڵ� ����
 * @�ó������� �ý��� �ڵ� ���� �Է� ���� ������ �Է� �� ��� ��ư Ŭ��
 */
public class E_FillOutAndRegister extends Scenario {
	private String code_type = "";
	private String code_code = "";
	private String code_name = "";
	private String target_table = "";
	private String ref_column = "";
	private String start_value = "";
	private String end_value = "";
	private String etc = "";
	
	public E_FillOutAndRegister(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		setting();
		fillOut();
		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[2]", "��� ��ư").click();
		cp.acceptAlert();
	}
	
	private void fillOut() {
		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[1]", "�ű� ��ư").click();
		FEB("xpath", "//*[@id=\"code_type\"]", "�з��ڵ� �Է�ĭ").sendKeys(code_type);
		FEB("xpath", "//*[@id=\"code_code\"]", "���ڵ� �Է�ĭ").sendKeys(code_code);
		FEB("xpath", "//*[@id=\"code_name\"]", "�ڵ�� �Է�ĭ").sendKeys(code_name);
		FEB("xpath", "//*[@id=\"code1\"]", "Ÿ�����̺� �Է�ĭ").sendKeys(target_table);
		FEB("xpath", "//*[@id=\"code2\"]", "�����÷� �Է�ĭ").sendKeys(ref_column);
		FEB("xpath", "//*[@id=\"code3\"]", "���۰� �Է�ĭ").sendKeys(start_value);
		FEB("xpath", "//*[@id=\"code4\"]", "���ᰪ �Է�ĭ").sendKeys(end_value);
		FEB("xpath", "//*[@id=\"code5\"]", "��� �Է�ĭ").sendKeys(etc);
	}
	
	private void setting() {
		this.code_type = bs.getPropValue("systemcode.code_type");
		this.code_code = bs.getPropValue("systemcode.code_code");
		this.code_name = bs.getPropValue("systemcode.code_name");
		this.target_table = bs.getPropValue("systemcode.target_table");
		this.ref_column = bs.getPropValue("systemcode.ref_column");
		this.start_value = bs.getPropValue("systemcode.start_value");
		this.end_value = bs.getPropValue("systemcode.end_value");
		this.etc = bs.getPropValue("systemcode.etc");
	}
}
