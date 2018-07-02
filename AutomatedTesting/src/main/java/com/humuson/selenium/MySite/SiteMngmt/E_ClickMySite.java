package com.humuson.selenium.MySite.SiteMngmt;

import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.humuson.support.LoginFunction;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 MySite
 * @중분류 사이트 관리
 * @시나리오명 MySite 메뉴 클릭
 * */
public class E_ClickMySite extends Scenario {
	public E_ClickMySite(int type, String[] category, String title) {
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
		FEB("xpath", "/html/body/header/header/div/div[2]/div/ul/li[6]", "MySite 메뉴").click();
	}
	
	private boolean check() {
		if (Pattern.compile("/site").matcher(driver.getCurrentUrl()).find())
			return true;
		else return false;
	}
	
	
}
