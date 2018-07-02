package com.humuson.selenium.System.CodeManagement.SCManagement;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 코드 관리
 * @소분류 시스템 코드 관리
 * @시나리오명 신규 버튼 클릭
 * */
public class E_ResetForm extends Scenario {
	public E_ResetForm(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		fillOut();
		click();
		CHECK(check());
	}
	
	private boolean check() {
		boolean result = true;
		result &= FEB("xpath", "//*[@id=\"code_type\"]", "분류코드 입력칸").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"code_code\"]", "상세코드 입력칸").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"code_name\"]", "코드명 입력칸").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"code1\"]", "타겟테이블 입력칸").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"code2\"]", "참조컬럼 입력칸").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"code3\"]", "시작값 입력칸").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"code4\"]", "종료값 입력칸").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"code5\"]", "비고 입력칸").getAttribute("value").equals("");
		
		return result;
	}
	
	private void click() {
		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[1]", "신규 버튼").click();
	}
	
	private void fillOut() {
		FEB("xpath", "//*[@id=\"code_type\"]", "분류코드 입력칸").sendKeys(bs.getPropValue("systemcode.code_type2"));
		FEB("xpath", "//*[@id=\"code_code\"]", "상세코드 입력칸").sendKeys(bs.getPropValue("systemcode.code_code2"));
		FEB("xpath", "//*[@id=\"code_name\"]", "코드명 입력칸").sendKeys(bs.getPropValue("systemcode.code_name2"));
		FEB("xpath", "//*[@id=\"code1\"]", "타겟테이블 입력칸").sendKeys(bs.getPropValue("systemcode.target_table2"));
		FEB("xpath", "//*[@id=\"code2\"]", "참조컬럼 입력칸").sendKeys(bs.getPropValue("systemcode.ref_column2"));
		FEB("xpath", "//*[@id=\"code3\"]", "시작값 입력칸").sendKeys(bs.getPropValue("systemcode.start_value2"));
		FEB("xpath", "//*[@id=\"code4\"]", "종료값 입력칸").sendKeys(bs.getPropValue("systemcode.end_value2"));
		FEB("xpath", "//*[@id=\"code5\"]", "비고 입력칸").sendKeys(bs.getPropValue("systemcode.etc2"));
	}
}



