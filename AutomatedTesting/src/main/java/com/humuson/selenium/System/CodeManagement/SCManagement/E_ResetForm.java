package com.humuson.selenium.System.CodeManagement.SCManagement;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� �ڵ� ����
 * @�Һз� �ý��� �ڵ� ����
 * @�ó������� �ű� ��ư Ŭ��
 * */
public class E_ResetForm extends Scenario {
	public E_ResetForm(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		fillOut();
		click();
		CHECK(check());
	}
	
	private boolean check() {
		boolean result = true;
		result &= FEB("xpath", "//*[@id=\"code_type\"]", "�з��ڵ� �Է�ĭ").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"code_code\"]", "���ڵ� �Է�ĭ").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"code_name\"]", "�ڵ�� �Է�ĭ").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"code1\"]", "Ÿ�����̺� �Է�ĭ").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"code2\"]", "�����÷� �Է�ĭ").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"code3\"]", "���۰� �Է�ĭ").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"code4\"]", "���ᰪ �Է�ĭ").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"code5\"]", "��� �Է�ĭ").getAttribute("value").equals("");
		
		return result;
	}
	
	private void click() {
		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[1]", "�ű� ��ư").click();
	}
	
	private void fillOut() {
		FEB("xpath", "//*[@id=\"code_type\"]", "�з��ڵ� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.code_type2"));
		FEB("xpath", "//*[@id=\"code_code\"]", "���ڵ� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.code_code2"));
		FEB("xpath", "//*[@id=\"code_name\"]", "�ڵ�� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.code_name2"));
		FEB("xpath", "//*[@id=\"code1\"]", "Ÿ�����̺� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.target_table2"));
		FEB("xpath", "//*[@id=\"code2\"]", "�����÷� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.ref_column2"));
		FEB("xpath", "//*[@id=\"code3\"]", "���۰� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.start_value2"));
		FEB("xpath", "//*[@id=\"code4\"]", "���ᰪ �Է�ĭ").sendKeys(bs.getPropValue("systemcode.end_value2"));
		FEB("xpath", "//*[@id=\"code5\"]", "��� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.etc2"));
	}
}



