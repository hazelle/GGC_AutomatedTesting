package com.humuson.selenium.MySite.SiteMngmt;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� MySite
 * @�ߺз� ����Ʈ ����
 * @�ó������� ����Ʈ ����
 * */
public class E_DeleteSite extends Scenario {
	private String deletename = "������";
	
	public E_DeleteSite(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		if(cp.movePage("/site/index")) {
			registerSite();
			chooseSite(deletename);
			alert();
		} else {
			FAIL("����Ʈ ����Ʈ ������ �̵�");
		}
		CHECK(check());
	}
	
	private boolean check() {
		driver.navigate().refresh();
		List<WebElement> sitenamelist = driver.findElements(By.className("cn_1"));
		for(int i=0; i<sitenamelist.size(); i++) {
			if(sitenamelist.get(i).findElement(By.className("cn_tt_lg")).getText().equals(deletename)) {
				return false;
			}
		}
		return true;
	}
	
	private void alert() {
		CustomWait(3);
		
		String alert = cp.getAlertText();
		if(Pattern.compile(deletename).matcher(alert).find()) {
			cp.acceptAlert(0);
		} else {
			FAIL("�����ϸ� �� �Ǵ� ����Ʈ ������ư Ŭ��");
		}
	}
	
	private boolean chooseSite(String sitename) {
		cp.movePage("/site/index");
		List<WebElement> sitenamelist = driver.findElements(By.className("cn_1"));
		for(int i=0; i<sitenamelist.size(); i++) {
			if(sitenamelist.get(i).findElement(By.className("cn_tt_lg")).getText().equals(sitename)) {
				sitenamelist.get(i).findElement(By.name("siteDelete")).click();
				return true;
			}
		}
		return false;
	}
	
	private void registerSite() {
		FEB("xpath", "//*[@id=\"registSite\"]/div", "�ű� ����Ʈ ��� ��ư").click();
		FEB("xpath", "//*[@id=\"siteImageFile\"]", "�̹��� Upload ��ư").sendKeys(pr.getPropValue("site.image.path"));
		FEB("xpath", "//*[@id=\"siteName\"]", "����Ʈ�� �Է�ĭ").sendKeys(deletename);
		FEB("xpath", "//*[@id=\"btnSave\"]", "���� ��ư").click();
		
		cp.movePage("/site/index");
	}
}
