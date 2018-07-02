package com.humuson.selenium.System.Setting.DomainFilter;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 환경설정
 * @소분류 도메인 필터
 * @시나리오명 도메인 필터 등록
 */
public class E_RegisterDomain extends Scenario {
	WebElement engine = null;
	Select engineSelect = null;
	
	public E_RegisterDomain(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		for(int i=0; i<getEngineInfo(); i++) {
			selectEngine(i);
		}
	}
	
	private int getEngineInfo() {
		engine = FEB("id", "engTypeSelect", "엔진선택 드롭박스");
		List<WebElement> options = engine.findElements(By.tagName("option"));
		engineSelect = new Select(engine);
		return options.size();
	}
	
	private void selectEngine(int index) {
		engineSelect.selectByIndex(index);
		FEB("xpath", "//*[@id=\"SearchEngForm\"]/div/div/div/span[2]/button", "검색 버튼").click();
		
		// 2018-05-17 09:48:14
		
		FEB("xpath", "//*[@id=\"timePicker\"]", "엔진마감일자 입력칸").clear();
		CustomWait(1000);
//		FEB("xpath", "//*[@id=\"limitTime\"]", "엔진마감일자 입력칸").sendKeys(bs.getPropValue("datepicker"));
		CustomWait(20);
		FEB("xpath", "//*[@id=\"timePicker\"]", "엔진마감일자 입력칸").sendKeys("2019-02-14");
		CustomWait(20);
		FEB("xpath", "//*[@id=\"timePicker\"]", "엔진마감일자 입력칸").sendKeys(Keys.TAB);
		CustomWait(20);
		FEB("xpath", "//*[@id=\"timePicker\"]", "엔진마감일자 입력칸").sendKeys("01:01:01");
		CustomWait(20);
		FEB("id", "domain", "도메인 입력칸").clear();
		FEB("id", "domain", "도메인 입력칸").sendKeys(bs.getPropValue("domain.filter.address"));
		
		FEB("xpath", "//*[@id=\"domainFltRegForm\"]/div[2]/div/button[2]", "등록 버튼").click();
		CustomWait(3);
		cp.acceptAlert();
	}
}
