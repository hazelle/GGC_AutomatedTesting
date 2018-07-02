package com.humuson.selenium.MySite.SiteMngmt;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 MySite
 * @중분류 사이트 관리
 * @시나리오명 사이트 삭제
 * */
public class E_DeleteSite extends Scenario {
	private String deletename = "삭제용";
	
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
			FAIL("사이트 리스트 페이지 이동");
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
			FAIL("삭제하면 안 되는 사이트 삭제버튼 클릭");
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
		FEB("xpath", "//*[@id=\"registSite\"]/div", "신규 사이트 등록 버튼").click();
		FEB("xpath", "//*[@id=\"siteImageFile\"]", "이미지 Upload 버튼").sendKeys(pr.getPropValue("site.image.path"));
		FEB("xpath", "//*[@id=\"siteName\"]", "사이트명 입력칸").sendKeys(deletename);
		FEB("xpath", "//*[@id=\"btnSave\"]", "저장 버튼").click();
		
		cp.movePage("/site/index");
	}
}
