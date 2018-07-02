package com.humuson.selenium.System.Setting.MonitoringServer;

import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.humuson.support.LoginFunction;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� ȯ�漳��
 * @�Һз� ����͸� ����
 * @�ó������� ����͸� ���� �޴� Ŭ��
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

		WebElement w = FEB("xpath", "/html/body/header/header/div/div[2]/div/a[2]", "������� ��Ӹ޴�");
		if (driver.findElement(By.xpath("/html/body/header/header/div/div[2]/div")).getAttribute("class").equals("btn-group")) {
			w.click();
		}
		FEB("xpath", "/html/body/header/header/div/div[2]/div/ul/li[8]/a", "System �޴�").click();
		cp.movePage("/sys/mntrSvr");
	}
	
	private boolean check() {
		return Pattern.compile("/sys/mntrSvr").matcher(driver.getCurrentUrl()).find();
	}
}


