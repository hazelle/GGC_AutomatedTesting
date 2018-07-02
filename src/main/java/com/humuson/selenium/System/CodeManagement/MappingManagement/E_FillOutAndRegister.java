package com.humuson.selenium.System.CodeManagement.MappingManagement;

import org.openqa.selenium.JavascriptExecutor;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� �ڵ� ����
 * @�Һз� ���� ����
 * @�ó������� �ϴ� ���� ������ �Է� �� ��� ��ư Ŭ��
 * */
public class E_FillOutAndRegister extends Scenario {
	private String show_seq = "";
	private String img_file_name = "";
	private String mapping_name = "";
	private String member_column_name = "";
	
	public E_FillOutAndRegister(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		setting();
		fillOut();
	}
	
	private void fillOut() {
		FEB("xpath", "//*[@id=\"show_seq\"]", "�����ڵ� �Է�ĭ").sendKeys(show_seq);
		FEB("xpath", "//*[@id=\"img_file_name\"]", "�̹��� ���ϸ� �Է�ĭ").sendKeys(img_file_name);
		FEB("xpath", "//*[@id=\"mapping_name\"]", "���θ�Ī �Է�ĭ").sendKeys(mapping_name);
		FEB("xpath", "//*[@id=\"member_column_name\"]", "�÷��� �Է�ĭ").sendKeys(member_column_name);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('title_mapping').setAttribute('value', 'N')");
		js.executeScript("document.getElementById('d_use_yn').setAttribute('value', 'N')");
		
		FEB("xpath", "//*[@id=\"saveBtn\"]", "��� ��ư").click();
		cp.acceptAlert();
	}
	
	private void setting() {
		this.show_seq = pr.getPropValue("mapping.show_seq");
		this.img_file_name = pr.getPropValue("mapping.img_file_name");
		this.mapping_name = pr.getPropValue("mapping.mapping_name");
		this.member_column_name = pr.getPropValue("mapping.member_column_name");
	}
}
