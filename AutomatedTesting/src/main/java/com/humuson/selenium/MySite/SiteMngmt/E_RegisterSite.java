package com.humuson.selenium.MySite.SiteMngmt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� MySite
 * @�ߺз� ����Ʈ ����
 * @�ó������� ����Ʈ ���
 */
public class E_RegisterSite extends Scenario {
	private Map<String, Boolean> channel = ii.getChannel();
	private String[] chns = ii.chns;
	private static String sitename = "";

	public E_RegisterSite(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}

	public void DO() {
		fillOut();
		click();
		CHECK(check());
	}
	
	private void click() {
		CustomWait(10);
		FEB("xpath", "//*[@id=\"btnSave\"]", "���� ��ư").click();
		CustomWait(10);
	}

	private void fillOut() {
		image();
		name();
		channel();
	}

	private void image() {
		FEB("xpath", "//*[@id=\"siteImageFile\"]", "�̹��� Upload ��ư").sendKeys(pr.getPropValue("site.image.path"));
	}

	private void name() {
		sitename = pr.getPropValue("site.name")+"_"+currentTime();
		pr.setPropValue("site.name", sitename);
		FEB("xpath", "//*[@id=\"siteName\"]", "����Ʈ�� �Է�ĭ").sendKeys(sitename);
	}

	private void channel() {
		for (int i = 0; i < 4; i++) {
			WebElement w = FEB("id", chns[i] + "Flag", chns[i] + " üũ�ڽ�");
			if (channel.get(chns[i])) {
				if (!w.isSelected()) {	w.click();	}
			} else {
				if (w.isSelected()) {	w.click();	}
			}
		}
	}
	
	private String currentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm"); 
		return sdf.format(new Date()).toString();
	}
	
	private boolean check() {
		try{
			FEB("xpath", "//*[@id=\"container\"]/div/h3", "ä�κ� �߼۰��� ���");
			return true;
		} catch(WebDriverException e) {
			return false;
		}
	}
}

