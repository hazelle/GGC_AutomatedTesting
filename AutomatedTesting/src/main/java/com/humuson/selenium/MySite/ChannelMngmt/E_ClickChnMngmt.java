package com.humuson.selenium.MySite.ChannelMngmt;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 MySite
 * @중분류 사이트 관리
 * @시나리오명 채널 관리 버튼 클릭
 * */
public class E_ClickChnMngmt extends Scenario {
	public E_ClickChnMngmt(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		if(chooseSite(pr.getPropValue("site.name2"))) {
			
		} else {
			FAIL("채널 관리 화면 진입");
		}
		
		CHECK(check());
	}
	
	private boolean check() {
		return Pattern.compile("/site/account").matcher(driver.getCurrentUrl()).find();
	}
	
	private boolean chooseSite(String sitename) {
		cp.movePage("/site/index");
		List<WebElement> sitenamelist = driver.findElements(By.className("cn_1"));
		for(int i=0; i<sitenamelist.size(); i++) {
			if(sitenamelist.get(i).findElement(By.className("cn_tt_lg")).getText().equals(sitename)) {
				sitenamelist.get(i).findElement(By.name("grpUpdate")).click();
				return true;
			}
		}
		return false;
	}
}

