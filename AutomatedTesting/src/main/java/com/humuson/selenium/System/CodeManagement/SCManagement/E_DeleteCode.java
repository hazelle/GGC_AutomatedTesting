package com.humuson.selenium.System.CodeManagement.SCManagement;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 코드 관리
 * @소분류 시스템 코드 관리
 * @시나리오명 시스템 코드 선택 후 삭제 버튼 클릭
 * */
public class E_DeleteCode extends Scenario {
	private String code_type = "";
	private String code_name = "";
	
	public E_DeleteCode(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		click();
		CHECK(check());
	}
	
	private void click() {
		search_codeType(bs.getPropValue("systemcode.code_type"));
		FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[3]/a", "시스템코드 목록 첫번째 항목").click();
		remember();
		delete();
		search_codeType(code_type);
		search_codeName(code_name);
		FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[3]/div/span/button", "검색 버튼").click();
	}
	
	private void remember() {
		code_type = FEB("xpath", "//*[@id=\"code_type\"]", "분류코드 입력칸").getAttribute("value");
		code_name = FEB("xpath", "//*[@id=\"code_name\"]", "코드명 입력칸").getAttribute("value");
	}
	
	private void delete() {
		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[4]", "삭제 버튼").click();
		cp.acceptAlert();
	}
	
	private boolean check() {
		try {
			WebElement w = FEB("id", "listBody", "시스템코드 리스트 body").findElement(By.xpath(".//*"));
			if(w.getAttribute("class").equals("pointbg"))
				return true;
			else return false;
		} catch(StaleElementReferenceException e) {
			return false;
		}
	}
	
	public void search_codeType(String codetype) {
		Select dropbox = new Select(driver.findElement(By.xpath("//*[@id=\"SearchForm\"]/div/div[1]/div/select")));
		dropbox.selectByValue(codetype);
	}
	
	private void search_codeName(String codename) {
		FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[2]/div/input", "코드명 입력칸").clear();
		FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[2]/div/input", "코드명 입력칸").sendKeys(codename);
	}
}

