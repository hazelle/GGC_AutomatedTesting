package com.humuson.selenium.System.CodeManagement.MappingManagement;

import org.openqa.selenium.StaleElementReferenceException;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 코드 관리
 * @소분류 매핑 관리
 * @시나리오명 코드명 클릭, 코드 수정
 * */
public class E_ClickCodeAndModify extends Scenario {
	private String original = "";
	private String change = "";
	
	public E_ClickCodeAndModify(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		click();
		modify();
		CHECK(check());
	}
	
	private void click() {
//		wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//*[@id=\"listBody\"]/tr[1]/td[2]/a"))));
		CustomWait(3);
		try {
		FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[2]/a", "매핑코드 목록 첫번째 항목").click();
		} catch(StaleElementReferenceException e) {
			System.out.println("stale 발생1");
			FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[2]/a", "매핑코드 목록 첫번째 항목").click();
		}
	}
	
	private void modify() {
		original = FEB("xpath", "//*[@id=\"mapping_name\"]", "매핑명칭 입력칸").getAttribute("value");
		change = original+"22";
		FEB("id", "mapping_name", "매핑명칭 입력칸").clear();
		FEB("id", "mapping_name", "매핑명칭 입력칸").sendKeys(change);
		FEB("xpath", "//*[@id=\"updateBtn\"]", "수정 버튼").click();
		cp.acceptAlert();
	}
	
	private boolean check() {
		CustomWait(3);
		boolean result = false;
		try{
			result = FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[2]/a", "매핑코드 목록 첫번째 항목 코드명").getText().equals(change);
		} catch(StaleElementReferenceException e) {
			System.out.println("stale 발생2");
			result = FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[2]/a", "매핑코드 목록 첫번째 항목 코드명").getText().equals(change);
		}
		return result;
//		return FEB("css", "#listBody > tr:nth-child(1) > td:nth-child(2) > a", "매핑코드 목록 첫번째 항목 코드명").getText().equals(change);
	}
}
