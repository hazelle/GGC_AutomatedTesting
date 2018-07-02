package com.humuson.selenium.System.CodeManagement.MappingManagement;

import org.openqa.selenium.JavascriptExecutor;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� �ڵ� ����
 * @�Һз� ���� ����
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
		result &= FEB("xpath", "//*[@id=\"show_seq\"]", "�����ڵ� �Է�ĭ").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"img_file_name\"]", "�̹��� ���ϸ� �Է�ĭ").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"mapping_name\"]", "���θ�Ī �Է�ĭ").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"member_column_name\"]", "�÷��� �Է�ĭ").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"title_mapping\"]", "Ÿ��Ʋ ���ο��� radio").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"d_use_yn\"]", "SMS���� ������� radio").getAttribute("value").equals("N");
		
		return result;
	}
	
	private void click() {
		FEB("xpath", "//*[@id=\"saveForm\"]/div[4]/div/a[1]", "�ű� ��ư").click();
	}
	
	private void fillOut() {
		FEB("xpath", "//*[@id=\"show_seq\"]", "�����ڵ� �Է�ĭ").sendKeys(pr.getPropValue("mapping.show_seq"));
		FEB("xpath", "//*[@id=\"img_file_name\"]", "�̹��� ���ϸ� �Է�ĭ").sendKeys(pr.getPropValue("mapping.img_file_name"));
		FEB("xpath", "//*[@id=\"mapping_name\"]", "���θ�Ī �Է�ĭ").sendKeys(pr.getPropValue("mapping.mapping_name"));
		FEB("xpath", "//*[@id=\"member_column_name\"]", "�÷��� �Է�ĭ").sendKeys(pr.getPropValue("mapping.member_column_name"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('title_mapping').setAttribute('value', 'N')");
		js.executeScript("document.getElementById('d_use_yn').setAttribute('value', 'N')");
	}
}
