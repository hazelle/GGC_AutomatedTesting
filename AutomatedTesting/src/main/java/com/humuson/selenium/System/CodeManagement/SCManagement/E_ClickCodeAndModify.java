package com.humuson.selenium.System.CodeManagement.SCManagement;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.Select;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 코드 관리
 * @소분류 시스템 코드 관리
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
//		wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.xpath("//*[@id=\"listBody\"]/tr[1]/td[3]/a"))));
		CHECK(check());
	}
	
	private void click() {
		search_codeType(bs.getPropValue("systemcode.code_type"));
		FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[3]/a", "시스템코드 목록 첫번째 항목").click();
		CustomWait(3);
	}
	
	private void modify() {
		original = FEB("xpath", "//*[@id=\"code_name\"]", "코드명 입력칸").getAttribute("value");
		change = original+"22";
		FEB("id", "code_name", "코드명 입력칸").clear();
		FEB("id", "code_name", "코드명 입력칸").sendKeys(change);
		CustomWait(3);
		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[3]", "수정 버튼").click();
		cp.acceptAlert();
	}
	
	private boolean check() {
		boolean result = false;
		try {
			result = FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[3]/a", "시스템코드 목록 첫번째 항목 코드명").getText().equals(change);
		} catch(StaleElementReferenceException e) {
			result = FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[3]/a", "시스템코드 목록 첫번째 항목 코드명").getText().equals(change);
		}
		return result;
	}
	
	public void search_codeType(String codetype) {
		Select dropbox = new Select(FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[1]/div/select", "코드타입 드롭박스"));
		dropbox.selectByValue(codetype);
	}
}
