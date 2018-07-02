package com.humuson.selenium.System.Setting.MonitoringServer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * 
 * @��з� System
 * @�ߺз� ȯ�漳��
 * @�Һз� ����͸� ����
 * @�ó������� ����͸� ���� ����Ʈ ����
 */
public class E_ModifyMntrSvrList extends Scenario {
	private String add = "2";
	
	public E_ModifyMntrSvrList(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if (type != E)
			category[type] = title;
	}

	protected void DO() {
		driver.navigate().refresh();
		register();
		click();
		if(check_contents()) modify();
		CHECK(check_contents(add));
		
	}
	
	private void register() {
		driver.navigate().refresh();
		do {
			FEB("xpath", "//*[@id=\"page-wrapper\"]/div[1]/div/button", "��� ��ư").click();
		} while(FEB("xpath", "//*[@id=\"modalServer\"]", "���� ����Ʈ ���� ���â").getAttribute("style").equals("display: none;"));
		wait.until(ExpectedConditions.visibilityOf(FEB("id", "server_name", "������ �Է�ĭ"))); 
		FEB("id", "server_name", "������ �Է�ĭ").clear();
		FEB("id", "server_name", "������ �Է�ĭ").sendKeys("������");
		
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
		
		FEB("xpath", "//*[@id=\"serverRegistBtn\"]", "��� ��ư").click();
		cp.acceptAlert();
	}

	private void click() {
		driver.navigate().refresh();
		List<WebElement> servers = FEB("xpath", "//*[@id=\"ListDiv\"]/div/div/table/tbody", "���� ����Ʈ").findElements(By.tagName("tr"));
		for (int i = 1; i < servers.size() - 8; i++) {
			if (i % 2 == 1
					&& driver.findElement(By.xpath("//*[@id=\"ListDiv\"]/div/div/table/tbody/tr[" + i + "]/td[2]/a"))
							.getText().trim().equals("������")) {
				driver.findElement(By.xpath("//*[@id=\"ListDiv\"]/div/div/table/tbody/tr[" + i + "]/td[4]/a[1]")).click();
			}
		}
	}

	private boolean check_contents() {
		boolean result = true;
		
		result &= FEB("id", "server_name", "������ �Է�ĭ").getAttribute("value")
				.equals("������");
		result &= FEB("id", "max_hdd", "HDD ��ü �뷮 �Է�ĭ").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.max.hdd"));
		result &= FEB("id", "max_db", "DB ��ü �뷮 �Է�ĭ").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.max.db"));
		result &= FEB("id", "warn_hdd", "��� �˶� HDD �Ӱ�ġ �Է�ĭ").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.warn.hdd"));
		result &= FEB("id", "warn_db", "��� �˶� DB �Ӱ�ġ �Է�ĭ").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.warn.db"));
		result &= FEB("id", "hdd_path", "HDD ��� �Է�ĭ").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.hdd.path"));
		
		return result;
	}
	private void modify() {
		FEB("id", "server_name", "������ �Է�ĭ").clear();
		FEB("id", "server_name", "������ �Է�ĭ").sendKeys("������");
		
		FEB("id", "max_hdd", "HDD ��ü �뷮 �Է�ĭ").clear();
		FEB("id", "max_hdd", "HDD ��ü �뷮 �Է�ĭ").sendKeys(bs.getPropValue("monitoring.server.max.hdd")+add);
		
		FEB("id", "max_db", "DB ��ü �뷮 �Է�ĭ").clear();
		FEB("id", "max_db", "DB ��ü �뷮 �Է�ĭ").sendKeys(bs.getPropValue("monitoring.server.max.db")+add);
		
		FEB("id", "warn_hdd", "��� �˶� HDD �Ӱ�ġ �Է�ĭ").clear();
		FEB("id", "warn_hdd", "��� �˶� HDD �Ӱ�ġ �Է�ĭ").sendKeys(bs.getPropValue("monitoring.server.warn.hdd")+add);
		
		FEB("id", "warn_db", "��� �˶� DB �Ӱ�ġ �Է�ĭ").clear();
		FEB("id", "warn_db", "��� �˶� DB �Ӱ�ġ �Է�ĭ").sendKeys(bs.getPropValue("monitoring.server.warn.db")+add);
		
		FEB("id", "hdd_path", "HDD ��� �Է�ĭ").clear();
		FEB("id", "hdd_path", "HDD ��� �Է�ĭ").sendKeys(bs.getPropValue("monitoring.server.hdd.path")+add);
		
		FEB("xpath", "//*[@id=\"serverEditBtn\"]", "��� ��ư").click();
		cp.acceptAlert();
	}
	
	private boolean check_contents(String add) {
		boolean result = true;
		
		boolean result1 = FEB("id", "server_name", "������ �Է�ĭ").getAttribute("value")
				.equals("������");
		if(!result1) FAIL("������ �Է�ĭ ����");
		boolean result2 = FEB("id", "max_hdd", "HDD ��ü �뷮 �Է�ĭ").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.max.hdd")+add);
		if(!result2) FAIL("HDD ��ü �뷮 ����");
		boolean result3 = FEB("id", "max_db", "DB ��ü �뷮 �Է�ĭ").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.max.db")+add);
		if(!result3) FAIL("DB ��ü �뷮 ����");
		boolean result4 = FEB("id", "warn_hdd", "��� �˶� HDD �Ӱ�ġ �Է�ĭ").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.warn.hdd")+add);
		if(!result4) FAIL("��� �˶� HDD �Ӱ�ġ ����");
		boolean result5 = FEB("id", "warn_db", "��� �˶� DB �Ӱ�ġ �Է�ĭ").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.warn.db")+add);
		if(!result5) FAIL("��� �˶� DB �Ӱ�ġ ����");
		boolean result6 = FEB("id", "hdd_path", "HDD ��� �Է�ĭ").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.hdd.path")+add);
		if(!result6) FAIL("HDD ��� ����");
		return result;
	}
	
}


