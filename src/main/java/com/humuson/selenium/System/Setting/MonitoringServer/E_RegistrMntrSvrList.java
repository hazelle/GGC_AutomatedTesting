package com.humuson.selenium.System.Setting.MonitoringServer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� ȯ�漳��
 * @�Һз� ����͸� ����
 * @�ó������� ����͸� ���� ����Ʈ ���
 */
public class E_RegistrMntrSvrList extends Scenario {
	private int cnt = 0;
	
	public E_RegistrMntrSvrList(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		click();
		fillOut();
		
		CHECK(check());
	}
	
	private void click() {
//		FEB("xpath", "//*[@id=\"page-wrapper\"]/div[1]/div/button", "��� ��ư").click();
//		if(FEB("xpath", "//*[@id=\"modalServer\"]", "���� ���� ���â").getAttribute("display").equals("display: none;")) {
//			FEB("xpath", "//*[@id=\"page-wrapper\"]/div[1]/div/button", "��� ��ư").click();
//		}
		do {
			FEB("xpath", "//*[@id=\"page-wrapper\"]/div[1]/div/button", "��� ��ư").click();
		} while(FEB("xpath", "//*[@id=\"modalServer\"]", "���� ����Ʈ ���� ���â").getAttribute("style").equals("display: none;"));
	}
	
	private void fillOut() {
		List<WebElement> servers = FEB("xpath", "//*[@id=\"ListDiv\"]/div/div/table/tbody", "���� ����Ʈ tbody").findElements(By.tagName("tr"));
		cnt = servers.size();
		
		wait.until(ExpectedConditions.visibilityOf(FEB("id", "server_name", "������ �Է�ĭ"))); 
		
		FEB("id", "server_name", "������ �Է�ĭ").clear();
		FEB("id", "server_name", "������ �Է�ĭ").sendKeys(bs.getPropValue("monitoring.server.name"));
		
		FEB("id", "max_hdd", "HDD ��ü �뷮 �Է�ĭ").clear();
		FEB("id", "max_hdd", "HDD ��ü �뷮 �Է�ĭ").sendKeys(bs.getPropValue("monitoring.server.max.hdd"));
		
		FEB("id", "max_db", "DB ��ü �뷮 �Է�ĭ").clear();
		FEB("id", "max_db", "DB ��ü �뷮 �Է�ĭ").sendKeys(bs.getPropValue("monitoring.server.max.db"));
		
		FEB("id", "warn_hdd", "��� �˶� HDD �Ӱ�ġ �Է�ĭ").clear();
		FEB("id", "warn_hdd", "��� �˶� HDD �Ӱ�ġ �Է�ĭ").sendKeys(bs.getPropValue("monitoring.server.warn.hdd"));
		
		FEB("id", "warn_db", "��� �˶� DB �Ӱ�ġ �Է�ĭ").clear();
		FEB("id", "warn_db", "��� �˶� DB �Ӱ�ġ �Է�ĭ").sendKeys(bs.getPropValue("monitoring.server.warn.db"));
		
		FEB("id", "hdd_path", "HDD ��� �Է�ĭ").clear();
		FEB("id", "hdd_path", "HDD ��� �Է�ĭ").sendKeys(bs.getPropValue("monitoring.server.hdd.path"));
		
		FEB("id", "serverRegistBtn", "���� ��ư").click();
		cp.acceptAlert();
	}
	
	private boolean check() {
		driver.navigate().refresh();
		List<WebElement> servers_ = FEB("xpath", "//*[@id=\"ListDiv\"]/div/div/table/tbody", "���� ����Ʈ tbody").findElements(By.tagName("tr"));
		
		if(servers_.size()==cnt) {
			FAIL("����͸� ���� �߰�");
			return false;
		} else {
			return true;
		}
	}
}
