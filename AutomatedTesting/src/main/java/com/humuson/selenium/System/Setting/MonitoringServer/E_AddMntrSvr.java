package com.humuson.selenium.System.Setting.MonitoringServer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * 
 * @��з� System
 * @�ߺз� ȯ�漳��
 * @�Һз� ����͸� ����
 * @�ó������� ����͸� ���� �߰�
 */
public class E_AddMntrSvr extends Scenario {
	public E_AddMntrSvr(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if (type != E)
			category[type] = title;
	}
	
	protected void DO() {
		click();
		fillout();
		check();
	}
	
	private void click() {
		List<WebElement> servers = FEB("xpath", "//*[@id=\"ListDiv\"]/div/div/table/tbody", "���� ����Ʈ").findElements(By.tagName("tr"));
		for (int i = 1; i < servers.size() - 8; i++) {
			if (i % 2 == 1
					&& driver.findElement(By.xpath("//*[@id=\"ListDiv\"]/div/div/table/tbody/tr[" + i + "]/td[2]/a"))
							.getText().trim().equals(bs.getPropValue("monitoring.server.name"))) {
				driver.findElement(By.xpath("//*[@id=\"ListDiv\"]/div/div/table/tbody/tr[" + i + "]/td[4]/a[3]")).click();
			}
		}
		cp.acceptAlert();
	}
		
	private void fillout() {
		
	}
	
	private void check() {
		
	}
}



