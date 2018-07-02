package com.humuson.selenium.MySite.SiteMngmt;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 MySite
 * @중분류 사이트 관리
 * @시나리오명 사이트 수정
 * */
public class E_ModifySite extends Scenario {
	private String[] chns = ii.chns;
	
	private String newname = pr.getPropValue("site.name2");
	
	public E_ModifySite(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		if(chooseSite(pr.getPropValue("site.name"))) {
			modify();
		} else {
			FAIL();
		}
		
		CHECK(check());
	}
	
	private void modify() {
		image(pr.getPropValue("site.image.path2"));
		name();
		channel();
		click();
	}
	
	private boolean check() {
		boolean result = true;
		
		chooseSite(pr.getPropValue("site.name2"));
//		result &= FEB("xpath", "//*[@id=\"siteImageFile\"]", "이미지 Upload 버튼").getAttribute("value").equals(pr.getPropValue("site.image.path2"));
//		if(!FEB("xpath", "//*[@id=\"siteImageFile\"]", "이미지 Upload 버튼").getAttribute("value").equals(pr.getPropValue("site.image.path2")))	FAIL("이미지 경로Î");
		saveScreenShot(category, "이미지 바뀌었는지 확인", ".jpg");
		result &= FEB("xpath", "//*[@id=\"siteName\"]", "사이트명 입력칸").getAttribute("value").trim().equals(newname);
		if(!FEB("xpath", "//*[@id=\"siteName\"]", "사이트명 입력칸").getAttribute("value").trim().equals(newname)) FAIL("»çÀÌÆ®¸í");
		
		return result;
	}
	
	private boolean chooseSite(String sitename) {
		cp.movePage("/site/index");
		List<WebElement> sitenamelist = driver.findElements(By.className("cn_1"));
		for(int i=0; i<sitenamelist.size(); i++) {
			if(sitenamelist.get(i).findElement(By.className("cn_tt_lg")).getText().equals(sitename)) {
				sitenamelist.get(i).findElement(By.xpath("//*[@id=\"container\"]/div/center/div/div[1]/div[1]/span/a[2]")).click();
				return true;
			}
		}
		return false;
	}
	
	private void image(String path2) {
		FEB("xpath", "//*[@id=\"siteImageFile\"]", "이미지 Upload 버튼").sendKeys(path2);
	}

	private void name() {
		FEB("xpath", "//*[@id=\"siteName\"]", "사이트명 입력칸").clear();
		newname += "_"+currentTime();
		pr.setPropValue("site.name2", newname);
		FEB("xpath", "//*[@id=\"siteName\"]", "사이트명 입력칸").sendKeys(newname);
	}

	private void channel() {
		boolean origin = true;
		boolean test = true;
		boolean result = true;
		
		for (int i = 0; i < 4; i++) {
			WebElement w = FEB("id", chns[i] + "Flag", chns[i] + " 체크박스");
			origin = w.isSelected();
			w.click();
			w.click();
			test = w.isSelected();
			result &= !(origin^test);
		}
		
		if(!result) {
			FAIL("채널 선택");
		}
	}
	
	private void click() {
		FEB("xpath", "//*[@id=\"btnSave\"]", "저장 버튼").click();
	}
	
	private String currentTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("hh:mm"); 
		return sdf.format(new Date()).toString();
	}
}
