package com.humuson.selenium.MySite.SiteMngmt;

import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.humuson.support.LoginFunction;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� MySite
 * @�ߺз� ����Ʈ ����
 * @�ó������� MySite �޴� Ŭ��
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
		WebElement w = FEB("xpath", "/html/body/header/header/div/div[2]/div/a[2]", "������� ��Ӹ޴�");
		if (driver.findElement(By.xpath("/html/body/header/header/div/div[2]/div")).getAttribute("class").equals("btn-group")) {
			w.click();
		}
		FEB("xpath", "/html/body/header/header/div/div[2]/div/ul/li[6]", "MySite �޴�").click();
	}
	
	private boolean check() {
		if (Pattern.compile("/site").matcher(driver.getCurrentUrl()).find())
			return true;
		else return false;
	}
	
	
}
