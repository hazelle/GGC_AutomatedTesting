package com.humuson.selenium.System.Setting.DomainFilter;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� ȯ�漳��
 * @�Һз� ������ ����
 * @�ó������� ������ ���� ���
 */
public class E_RegisterDomain extends Scenario {
	WebElement engine = null;
	Select engineSelect = null;
	
	public E_RegisterDomain(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		for(int i=0; i<getEngineInfo(); i++) {
			selectEngine(i);
		}
	}
	
	private int getEngineInfo() {
		engine = FEB("id", "engTypeSelect", "�������� ��ӹڽ�");
		List<WebElement> options = engine.findElements(By.tagName("option"));
		engineSelect = new Select(engine);
		return options.size();
	}
	
	private void selectEngine(int index) {
		engineSelect.selectByIndex(index);
		FEB("xpath", "//*[@id=\"SearchEngForm\"]/div/div/div/span[2]/button", "�˻� ��ư").click();
		
		// 2018-05-17 09:48:14
		
		FEB("xpath", "//*[@id=\"timePicker\"]", "������������ �Է�ĭ").clear();
		CustomWait(1000);
//		FEB("xpath", "//*[@id=\"limitTime\"]", "������������ �Է�ĭ").sendKeys(bs.getPropValue("datepicker"));
		CustomWait(20);
		FEB("xpath", "//*[@id=\"timePicker\"]", "������������ �Է�ĭ").sendKeys("2019-02-14");
		CustomWait(20);
		FEB("xpath", "//*[@id=\"timePicker\"]", "������������ �Է�ĭ").sendKeys(Keys.TAB);
		CustomWait(20);
		FEB("xpath", "//*[@id=\"timePicker\"]", "������������ �Է�ĭ").sendKeys("01:01:01");
		CustomWait(20);
		FEB("id", "domain", "������ �Է�ĭ").clear();
		FEB("id", "domain", "������ �Է�ĭ").sendKeys(bs.getPropValue("domain.filter.address"));
		
		FEB("xpath", "//*[@id=\"domainFltRegForm\"]/div[2]/div/button[2]", "��� ��ư").click();
		CustomWait(3);
		cp.acceptAlert();
	}
}
