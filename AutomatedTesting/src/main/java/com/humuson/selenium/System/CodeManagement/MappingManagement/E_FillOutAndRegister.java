package com.humuson.selenium.System.CodeManagement.MappingManagement;

import org.openqa.selenium.JavascriptExecutor;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 코드 관리
 * @소분류 매핑 관리
 * @시나리오명 하단 폼에 데이터 입력 후 등록 버튼 클릭
 * */
public class E_FillOutAndRegister extends Scenario {
	private String show_seq = "";
	private String img_file_name = "";
	private String mapping_name = "";
	private String member_column_name = "";
	
	public E_FillOutAndRegister(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		setting();
		fillOut();
	}
	
	private void fillOut() {
		FEB("xpath", "//*[@id=\"show_seq\"]", "매핑코드 입력칸").sendKeys(show_seq);
		FEB("xpath", "//*[@id=\"img_file_name\"]", "이미지 파일명 입력칸").sendKeys(img_file_name);
		FEB("xpath", "//*[@id=\"mapping_name\"]", "매핑명칭 입력칸").sendKeys(mapping_name);
		FEB("xpath", "//*[@id=\"member_column_name\"]", "컬럼명 입력칸").sendKeys(member_column_name);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('title_mapping').setAttribute('value', 'N')");
		js.executeScript("document.getElementById('d_use_yn').setAttribute('value', 'N')");
		
		FEB("xpath", "//*[@id=\"saveBtn\"]", "등록 버튼").click();
		cp.acceptAlert();
	}
	
	private void setting() {
		this.show_seq = pr.getPropValue("mapping.show_seq");
		this.img_file_name = pr.getPropValue("mapping.img_file_name");
		this.mapping_name = pr.getPropValue("mapping.mapping_name");
		this.member_column_name = pr.getPropValue("mapping.member_column_name");
	}
}
