package com.humuson.selenium.System.CodeManagement.SCManagement;

import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.humuson.support.LoginFunction;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 코드 관리
 * @소분류 시스템 코드 관리
 * @시나리오명 시스템코드 관리 메뉴 클릭
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
		WebElement w = FEB("xpath", "/html/body/header/header/div/div[2]/div/a[2]", "우측상단 드롭메뉴");
		if (driver.findElement(By.xpath("/html/body/header/header/div/div[2]/div")).getAttribute("class").equals("btn-group")) {
			w.click();
		}
		FEB("xpath", "/html/body/header/header/div/div[2]/div/ul/li[8]/a", "System 메뉴").click();

		if (Pattern.compile("sys").matcher(driver.getCurrentUrl()).find()) {
			w = FEB("xpath", "//*[@id=\"nav_side_list\"]/li[1]/ul", "코드 관리 메뉴(열린 부분)");
			if (w.getCssValue("display").equals("block")) {
				FEB("xpath", "//*[@id=\"nav_side_list\"]/li[1]/a", "코드 관리 메뉴").click();
			}
			FEB("xpath", "//*[@id=\"nav_side_list\"]/li[1]/ul/li[1]/a", "시스템코드 관리 메뉴").click();
		} else {
			cp.movePage("/sys/code");
		}
	}
	
	private boolean check() {
		return Pattern.compile("/sys/code").matcher(driver.getCurrentUrl()).find();
	}
}
