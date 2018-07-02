package com.humuson.selenium.System.CodeManagement.SCManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� �ڵ� ����
 * @�Һз� �ý��� �ڵ� ����
 * @�ó������� �ý��� �ڵ� ���� �� ���� ��ư Ŭ��
 * */
public class E_DeleteCode extends Scenario {
	private String code_type = "";
	private String code_name = "";
	
	public E_DeleteCode(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		click();
		CHECK(check());
	}
	
	private void click() {
		search_codeType(bs.getPropValue("systemcode.code_type"));
		FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[3]/a", "�ý����ڵ� ��� ù��° �׸�").click();
		remember();
		delete();
		search_codeType(code_type);
		search_codeName(code_name);
		FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[3]/div/span/button", "�˻� ��ư").click();
	}
	
	private void remember() {
		code_type = FEB("xpath", "//*[@id=\"code_type\"]", "�з��ڵ� �Է�ĭ").getAttribute("value");
		code_name = FEB("xpath", "//*[@id=\"code_name\"]", "�ڵ�� �Է�ĭ").getAttribute("value");
	}
	
	private void delete() {
		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[4]", "���� ��ư").click();
		cp.acceptAlert();
	}
	
	private boolean check() {
		try {
			WebElement w = FEB("id", "listBody", "�ý����ڵ� ����Ʈ body").findElement(By.xpath(".//*"));
			if(w.getAttribute("class").equals("pointbg"))
				return true;
			else return false;
		} catch(StaleElementReferenceException e) {
			return false;
		}
	}
	
	public void search_codeType(String codetype) {
		Select dropbox = new Select(driver.findElement(By.xpath("//*[@id=\"SearchForm\"]/div/div[1]/div/select")));
		dropbox.selectByValue(codetype);
	}
	
	private void search_codeName(String codename) {
		FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[2]/div/input", "�ڵ�� �Է�ĭ").clear();
		FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[2]/div/input", "�ڵ�� �Է�ĭ").sendKeys(codename);
	}
}

