package com.humuson.selenium.System.CodeManagement.SCManagement;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.Select;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� �ڵ� ����
 * @�Һз� �ý��� �ڵ� ����
 * @�ó������� �ڵ�� Ŭ��, �ڵ� ����
 * */
public class E_ClickCodeAndModify extends Scenario {
	private String original = "";
	private String change = "";
	
	public E_ClickCodeAndModify(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		click();
		modify();
//		wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//*[@id=\"listBody\"]/tr[1]/td[3]/a"))));
		CHECK(check());
	}
	
	private void click() {
		search_codeType(bs.getPropValue("systemcode.code_type"));
		FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[3]/a", "�ý����ڵ� ��� ù��° �׸�").click();
		CustomWait(3);
	}
	
	private void modify() {
		original = FEB("xpath", "//*[@id=\"code_name\"]", "�ڵ�� �Է�ĭ").getAttribute("value");
		change = original+"22";
		FEB("id", "code_name", "�ڵ�� �Է�ĭ").clear();
		FEB("id", "code_name", "�ڵ�� �Է�ĭ").sendKeys(change);
		CustomWait(3);
		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[3]", "���� ��ư").click();
		cp.acceptAlert();
	}
	
	private boolean check() {
		boolean result = false;
		try {
			result = FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[3]/a", "�ý����ڵ� ��� ù��° �׸� �ڵ��").getText().equals(change);
		} catch(StaleElementReferenceException e) {
			result = FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[3]/a", "�ý����ڵ� ��� ù��° �׸� �ڵ��").getText().equals(change);
		}
		return result;
	}
	
	public void search_codeType(String codetype) {
		Select dropbox = new Select(FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[1]/div/select", "�ڵ�Ÿ�� ��ӹڽ�"));
		dropbox.selectByValue(codetype);
	}
}
