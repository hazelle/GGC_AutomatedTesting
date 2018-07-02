package com.humuson.selenium.System.Setting.MonitoringServer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * 
 * @대분류 System
 * @중분류 환경설정
 * @소분류 모니터링 서버
 * @시나리오명 모니터링 서버 리스트 삭제
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
		List<WebElement> servers = FEB("xpath", "//*[@id=\"ListDiv\"]/div/div/table/tbody", "서버 리스트").findElements(By.tagName("tr"));
		cnt = servers.size();
		for (int i = 1; i < servers.size() - 8; i++) {
			if (i % 2 == 1
					&& driver.findElement(By.xpath("//*[@id=\"ListDiv\"]/div/div/table/tbody/tr[" + i + "]/td[2]/a"))
							.getText().trim().equals("삭제용")) {
				driver.findElement(By.xpath("//*[@id=\"ListDiv\"]/div/div/table/tbody/tr[" + i + "]/td[4]/a[2]")).click();
			}
		}
		cp.acceptAlert();
	}
	
	private boolean check() {
		List<WebElement> servers = FEB("xpath", "//*[@id=\"ListDiv\"]/div/div/table/tbody", "서버 리스트").findElements(By.tagName("tr"));
		FAIL("모니터링 서버 삭제");
		return cnt>servers.size();
	}

}
