package com.humuson.selenium.System.CodeManagement.MappingManagement;

import org.openqa.selenium.StaleElementReferenceException;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� �ڵ� ����
 * @�Һз� ���� ����
 * @�ó������� ���� �ڵ� ���� �� ���� ��ư Ŭ��
 * */
public class E_DeleteCode extends Scenario {
	private String show_seq = "";
	private String mapping_name = "";
	
	public E_DeleteCode(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		click();
		remember();
		delete();
		CHECK(check());
	}
	
	private void click() {
		FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[2]/a", "�����ڵ� ��� ù��° �׸�").click();
	}
	
	private void remember() {
		show_seq = FEB("xpath", "//*[@id=\"show_seq\"]", "�����ڵ� �Է�ĭ").getAttribute("value");
		mapping_name = FEB("xpath", "//*[@id=\"mapping_name\"]", "���θ�Ī �Է�ĭ").getAttribute("value");
	}
	
	private void delete() {
		FEB("xpath", "//*[@id=\"deleteBtn\"]", "���� ��ư").click();
		cp.acceptAlert();
	}
	
	private boolean check() {
		boolean result1 = false;
		boolean result2 = false;
		
		try{
			result1 = !FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[1]", "�����ڵ� ��� ù��° �׸� ���� �ڵ�").getText().equals(show_seq);
		} catch(StaleElementReferenceException e) {
			result1 = !FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[1]", "�����ڵ� ��� ù��° �׸� ���� �ڵ�").getText().equals(show_seq);
		}
		
		try{
			result2 = !FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[2]/a", "�����ڵ� ��� ù��° �׸� ���� ��Ī").getText().equals(mapping_name);
		} catch(StaleElementReferenceException e) {
			result2 = !FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[2]/a", "�����ڵ� ��� ù��° �׸� ���� ��Ī").getText().equals(mapping_name);
		}
		return result1&&result2;
	}
	
}

