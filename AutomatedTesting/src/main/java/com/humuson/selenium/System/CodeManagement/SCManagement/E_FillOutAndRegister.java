package com.humuson.selenium.System.CodeManagement.SCManagement;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 코드 관리
 * @소분류 시스템 코드 관리
 * @시나리오명 시스템 코드 정보 입력 폼에 데이터 입력 후 등록 버튼 클릭
 */
public class E_FillOutAndRegister extends Scenario {
	private String code_type = "";
	private String code_code = "";
	private String code_name = "";
	private String target_table = "";
	private String ref_column = "";
	private String start_value = "";
	private String end_value = "";
	private String etc = "";
	
	public E_FillOutAndRegister(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		setting();
		fillOut();
		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[2]", "등록 버튼").click();
		cp.acceptAlert();
	}
	
	private void fillOut() {
		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[1]", "신규 버튼").click();
		FEB("xpath", "//*[@id=\"code_type\"]", "분류코드 입력칸").sendKeys(code_type);
		FEB("xpath", "//*[@id=\"code_code\"]", "상세코드 입력칸").sendKeys(code_code);
		FEB("xpath", "//*[@id=\"code_name\"]", "코드명 입력칸").sendKeys(code_name);
		FEB("xpath", "//*[@id=\"code1\"]", "타겟테이블 입력칸").sendKeys(target_table);
		FEB("xpath", "//*[@id=\"code2\"]", "참조컬럼 입력칸").sendKeys(ref_column);
		FEB("xpath", "//*[@id=\"code3\"]", "시작값 입력칸").sendKeys(start_value);
		FEB("xpath", "//*[@id=\"code4\"]", "종료값 입력칸").sendKeys(end_value);
		FEB("xpath", "//*[@id=\"code5\"]", "비고 입력칸").sendKeys(etc);
	}
	
	private void setting() {
		this.code_type = bs.getPropValue("systemcode.code_type");
		this.code_code = bs.getPropValue("systemcode.code_code");
		this.code_name = bs.getPropValue("systemcode.code_name");
		this.target_table = bs.getPropValue("systemcode.target_table");
		this.ref_column = bs.getPropValue("systemcode.ref_column");
		this.start_value = bs.getPropValue("systemcode.start_value");
		this.end_value = bs.getPropValue("systemcode.end_value");
		this.etc = bs.getPropValue("systemcode.etc");
	}
}
