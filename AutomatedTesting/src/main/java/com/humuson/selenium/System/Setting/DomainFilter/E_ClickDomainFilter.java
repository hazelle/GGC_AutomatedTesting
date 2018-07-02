package com.humuson.selenium.System.Setting.DomainFilter;

import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.humuson.support.LoginFunction;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� ȯ�漳��
 * @�Һз� ������ ����
 * @�ó������� ������ ���� �޴� Ŭ��
 */
public class E_ClickDomainFilter extends Scenario {
	public E_ClickDomainFilter(int type, String[] category, String title) {
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
		
		if (Pattern.compile("sys").matcher(driver.getCurrentUrl()).find()) {
			w = FEB("xpath", "//*[@id=\"nav_side_list\"]/li[2]/ul", "ȯ�漳�� �޴�(���� �κ�)");
			if (!w.getCssValue("display").equals("block")) {
				FEB("xpath", "//*[@id=\"nav_side_list\"]/li[2]/a", "ȯ�漳�� �޴�").click();
			}
			FEB("xpath", "//*[@id=\"nav_side_list\"]/li[2]/ul/li[1]/a", "������ ���� ���� �޴�").click();
		} else {
			cp.movePage("/sys/domainFlt");
		}
	}
	
	private boolean check() {
		return Pattern.compile("/sys/domainFlt").matcher(driver.getCurrentUrl()).find();
	}
}
