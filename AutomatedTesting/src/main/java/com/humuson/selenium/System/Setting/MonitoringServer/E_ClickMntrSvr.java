package com.humuson.selenium.System.Setting.MonitoringServer;

import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.humuson.support.LoginFunction;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 환경설정
 * @소분류 모니터링 서버
 * @시나리오명 모니터링 서버 메뉴 클릭
 */
public class E_ClickMntrSvr extends Scenario {
	public E_ClickMntrSvr(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		if (cp.detectLogin())
			move();
		else {
			new LoginFunction();
			move();
		}
		CHECK(check());
	}
	
	private void move() {
		CustomWait(5);

		WebElement w = FEB("xpath", "/html/body/header/header/div/div[2]/div/a[2]", "우측상단 드롭메뉴");
		if (driver.findElement(By.xpath("/html/body/header/header/div/div[2]/div")).getAttribute("class").equals("btn-group")) {
			w.click();
		}
		FEB("xpath", "/html/body/header/header/div/div[2]/div/ul/li[8]/a", "System 메뉴").click();
		cp.movePage("/sys/mntrSvr");
	}
	
	private boolean check() {
		return Pattern.compile("/sys/mntrSvr").matcher(driver.getCurrentUrl()).find();
	}
}


