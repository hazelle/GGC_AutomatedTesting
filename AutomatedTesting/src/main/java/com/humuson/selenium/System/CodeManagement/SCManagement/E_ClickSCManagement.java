package com.humuson.selenium.System.CodeManagement.SCManagement;

import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.humuson.support.LoginFunction;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� �ڵ� ����
 * @�Һз� �ý��� �ڵ� ����
 * @�ó������� �ý����ڵ� ���� �޴� Ŭ��
 */
public class E_ClickSCManagement extends Scenario {

	public E_ClickSCManagement(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}

	protected void DO() {
		if (cp.detectLogin())
			clickMenu();
		else {
			new LoginFunction();
			clickMenu();
		}
		
		CHECK(check());
	}

	private void clickMenu() {
		WebElement w = FEB("xpath", "/html/body/header/header/div/div[2]/div/a[2]", "������� ��Ӹ޴�");
		if (driver.findElement(By.xpath("/html/body/header/header/div/div[2]/div")).getAttribute("class").equals("btn-group")) {
			w.click();
		}
		FEB("xpath", "/html/body/header/header/div/div[2]/div/ul/li[8]/a", "System �޴�").click();

		if (Pattern.compile("sys").matcher(driver.getCurrentUrl()).find()) {
			w = FEB("xpath", "//*[@id=\"nav_side_list\"]/li[1]/ul", "�ڵ� ���� �޴�(���� �κ�)");
			if (w.getCssValue("display").equals("block")) {
				FEB("xpath", "//*[@id=\"nav_side_list\"]/li[1]/a", "�ڵ� ���� �޴�").click();
			}
			FEB("xpath", "//*[@id=\"nav_side_list\"]/li[1]/ul/li[1]/a", "�ý����ڵ� ���� �޴�").click();
		} else {
			cp.movePage("/sys/code");
		}
	}
	
	private boolean check() {
		return Pattern.compile("/sys/code").matcher(driver.getCurrentUrl()).find();
	}
}
