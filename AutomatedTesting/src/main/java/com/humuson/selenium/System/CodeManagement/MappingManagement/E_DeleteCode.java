package com.humuson.selenium.System.CodeManagement.MappingManagement;

import org.openqa.selenium.StaleElementReferenceException;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 코드 관리
 * @소분류 매핑 관리
 * @시나리오명 매핑 코드 선택 후 삭제 버튼 클릭
 * */
public class E_DeleteCode extends Scenario {
	private String show_seq = "";
	private String mapping_name = "";
	
	public E_DeleteCode(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		click();
		remember();
		delete();
		CHECK(check());
	}
	
	private void click() {
		FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[2]/a", "매핑코드 목록 첫번째 항목").click();
	}
	
	private void remember() {
		show_seq = FEB("xpath", "//*[@id=\"show_seq\"]", "매핑코드 입력칸").getAttribute("value");
		mapping_name = FEB("xpath", "//*[@id=\"mapping_name\"]", "매핑명칭 입력칸").getAttribute("value");
	}
	
	private void delete() {
		FEB("xpath", "//*[@id=\"deleteBtn\"]", "삭제 버튼").click();
		cp.acceptAlert();
	}
	
	private boolean check() {
		boolean result1 = false;
		boolean result2 = false;
		
		try{
			result1 = !FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[1]", "매핑코드 목록 첫번째 항목 매핑 코드").getText().equals(show_seq);
		} catch(StaleElementReferenceException e) {
			result1 = !FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[1]", "매핑코드 목록 첫번째 항목 매핑 코드").getText().equals(show_seq);
		}
		
		try{
			result2 = !FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[2]/a", "매핑코드 목록 첫번째 항목 매핑 명칭").getText().equals(mapping_name);
		} catch(StaleElementReferenceException e) {
			result2 = !FEB("xpath", "//*[@id=\"listBody\"]/tr[1]/td[2]/a", "매핑코드 목록 첫번째 항목 매핑 명칭").getText().equals(mapping_name);
		}
		return result1&&result2;
	}
	
}

