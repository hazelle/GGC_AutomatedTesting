package com.humuson.selenium.System.CodeManagement.MappingManagement;

import org.openqa.selenium.StaleElementReferenceException;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� �ڵ� ����
 * @�Һз� ���� ����
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
		CHECK(check());
	}
	
	private void click() {
//		wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//*[@id=\"listBody\"]/tr[1]/td[2]/a"))));
		CustomWait(3);
		try {
		FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[2]/a", "�����ڵ� ��� ù��° �׸�").click();
		} catch(StaleElementReferenceException e) {
			System.out.println("stale �߻�1");
			FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[2]/a", "�����ڵ� ��� ù��° �׸�").click();
		}
	}
	
	private void modify() {
		original = FEB("xpath", "//*[@id=\"mapping_name\"]", "���θ�Ī �Է�ĭ").getAttribute("value");
		change = original+"22";
		FEB("id", "mapping_name", "���θ�Ī �Է�ĭ").clear();
		FEB("id", "mapping_name", "���θ�Ī �Է�ĭ").sendKeys(change);
		FEB("xpath", "//*[@id=\"updateBtn\"]", "���� ��ư").click();
		cp.acceptAlert();
	}
	
	private boolean check() {
		CustomWait(3);
		boolean result = false;
		try{
			result = FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[2]/a", "�����ڵ� ��� ù��° �׸� �ڵ��").getText().equals(change);
		} catch(StaleElementReferenceException e) {
			System.out.println("stale �߻�2");
			result = FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[2]/a", "�����ڵ� ��� ù��° �׸� �ڵ��").getText().equals(change);
		}
		return result;
//		return FEB("css", "#listBody > tr:nth-child(1) > td:nth-child(2) > a", "�����ڵ� ��� ù��° �׸� �ڵ��").getText().equals(change);
	}
}
