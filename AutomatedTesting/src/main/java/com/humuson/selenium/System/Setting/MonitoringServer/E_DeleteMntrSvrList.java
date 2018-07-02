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
 * @�ó������� ����͸� ���� ����Ʈ ����
 */
public class E_DeleteMntrSvrList extends Scenario {
	private int cnt = 0;
	
	public E_DeleteMntrSvrList(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if (type != E)
			category[type] = title;
	}

	protected void DO() {
		click();
		CHECK(check());
	}

	private void click() {
		List<WebElement> servers = FEB("xpath", "//*[@id=\"ListDiv\"]/div/div/table/tbody", "���� ����Ʈ").findElements(By.tagName("tr"));
		cnt = servers.size();
		for (int i = 1; i < servers.size() - 8; i++) {
			if (i % 2 == 1
					&& driver.findElement(By.xpath("//*[@id=\"ListDiv\"]/div/div/table/tbody/tr[" + i + "]/td[2]/a"))
							.getText().trim().equals("������")) {
				driver.findElement(By.xpath("//*[@id=\"ListDiv\"]/div/div/table/tbody/tr[" + i + "]/td[4]/a[2]")).click();
			}
		}
		cp.acceptAlert();
	}
	
	private boolean check() {
		List<WebElement> servers = FEB("xpath", "//*[@id=\"ListDiv\"]/div/div/table/tbody", "���� ����Ʈ").findElements(By.tagName("tr"));
		FAIL("����͸� ���� ����");
		return cnt>servers.size();
	}

}
