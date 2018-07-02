package com.humuson.selenium.System.CodeManagement.MappingManagement;

import org.openqa.selenium.JavascriptExecutor;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 코드 관리
 * @소분류 매핑 관리
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
		result &= FEB("xpath", "//*[@id=\"show_seq\"]", "매핑코드 입력칸").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"img_file_name\"]", "이미지 파일명 입력칸").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"mapping_name\"]", "매핑명칭 입력칸").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"member_column_name\"]", "컬럼명 입력칸").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"title_mapping\"]", "타이틀 매핑여부 radio").getAttribute("value").equals("");
		result &= FEB("xpath", "//*[@id=\"d_use_yn\"]", "SMS동보 사용유무 radio").getAttribute("value").equals("N");
		
		return result;
	}
	
	private void click() {
		FEB("xpath", "//*[@id=\"saveForm\"]/div[4]/div/a[1]", "신규 버튼").click();
	}
	
	private void fillOut() {
		FEB("xpath", "//*[@id=\"show_seq\"]", "매핑코드 입력칸").sendKeys(pr.getPropValue("mapping.show_seq"));
		FEB("xpath", "//*[@id=\"img_file_name\"]", "이미지 파일명 입력칸").sendKeys(pr.getPropValue("mapping.img_file_name"));
		FEB("xpath", "//*[@id=\"mapping_name\"]", "매핑명칭 입력칸").sendKeys(pr.getPropValue("mapping.mapping_name"));
		FEB("xpath", "//*[@id=\"member_column_name\"]", "컬럼명 입력칸").sendKeys(pr.getPropValue("mapping.member_column_name"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.getElementById('title_mapping').setAttribute('value', 'N')");
		js.executeScript("document.getElementById('d_use_yn').setAttribute('value', 'N')");
	}
}
