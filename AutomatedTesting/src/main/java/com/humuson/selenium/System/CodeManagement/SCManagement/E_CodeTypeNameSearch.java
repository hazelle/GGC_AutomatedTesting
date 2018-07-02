package com.humuson.selenium.System.CodeManagement.SCManagement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 코드 관리
 * @소분류 시스템 코드 관리
 * @시나리오명 코드타입, 코드명 입력 후 검색 버튼 클릭
 */
public class E_CodeTypeNameSearch extends Scenario {

	public E_CodeTypeNameSearch(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}

	protected void DO() {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		setting();
		search();
		ETC("#","이미지 파일 확인 필요  #");
	}

	private void search() {
		search_codeType(bs.getPropValue("systemcode.code_type"));
		FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[3]/div/span/button", "검색 버튼").click();
		saveScreenShot(category, "코드타입으로 검색", ".jpg");

		search_codeName(bs.getPropValue("systemcode.code_name2"));
		FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[3]/div/span/button", "검색 버튼").click();
		saveScreenShot(category, "코드명으로 검색", ".jpg");

		search_codeType(bs.getPropValue("systemcode.code_type"));
		search_codeName(bs.getPropValue("systemcode.code_name3"));
		FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[3]/div/span/button", "검색 버튼").click();
		saveScreenShot(category, "코드타입,코드명으로 검색", ".jpg");
	}

	public void search_codeType(String codetype) {
		CustomWait(3);
		Select dropbox = new Select(driver.findElement(By.xpath("//*[@id=\"SearchForm\"]/div/div[1]/div/select")));
		dropbox.selectByValue(codetype);
	}

	private void search_codeName(String codename) {
		FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[2]/div/input", "코드명 입력칸").clear();
		FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[2]/div/input", "코드명 입력칸").sendKeys(codename);
	}

	private void setting() {
		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[1]", "신규 버튼").click();
		FEB("xpath", "//*[@id=\"code_type\"]", "분류코드 입력칸").sendKeys(bs.getPropValue("systemcode.code_type2"));
		FEB("xpath", "//*[@id=\"code_code\"]", "상세코드 입력칸").sendKeys(bs.getPropValue("systemcode.code_code2"));
		FEB("xpath", "//*[@id=\"code_name\"]", "코드명 입력칸").sendKeys(bs.getPropValue("systemcode.code_name2"));
		FEB("xpath", "//*[@id=\"code1\"]", "타겟테이블 입력칸").sendKeys(bs.getPropValue("systemcode.target_table2"));
		FEB("xpath", "//*[@id=\"code2\"]", "참조컬럼 입력칸").sendKeys(bs.getPropValue("systemcode.ref_column2"));
		FEB("xpath", "//*[@id=\"code3\"]", "시작값 입력칸").sendKeys(bs.getPropValue("systemcode.start_value2"));
		FEB("xpath", "//*[@id=\"code4\"]", "종료값 입력칸").sendKeys(bs.getPropValue("systemcode.end_value2"));
		FEB("xpath", "//*[@id=\"code5\"]", "비고 입력칸").sendKeys(bs.getPropValue("systemcode.etc2"));
		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[2]", "등록 버튼").click();
		CustomWait(5);
		cp.acceptAlert(0);

		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[1]", "신규 버튼").click();
		FEB("xpath", "//*[@id=\"code_type\"]", "분류코드 입력칸").sendKeys(bs.getPropValue("systemcode.code_type3"));
		FEB("xpath", "//*[@id=\"code_code\"]", "상세코드 입력칸").sendKeys(bs.getPropValue("systemcode.code_code3"));
		FEB("xpath", "//*[@id=\"code_name\"]", "코드명 입력칸").sendKeys(bs.getPropValue("systemcode.code_name3"));
		FEB("xpath", "//*[@id=\"code1\"]", "타겟테이블 입력칸").sendKeys(bs.getPropValue("systemcode.target_table3"));
		FEB("xpath", "//*[@id=\"code2\"]", "참조컬럼 입력칸").sendKeys(bs.getPropValue("systemcode.ref_column3"));
		FEB("xpath", "//*[@id=\"code3\"]", "시작값 입력칸").sendKeys(bs.getPropValue("systemcode.start_value3"));
		FEB("xpath", "//*[@id=\"code4\"]", "종료값 입력칸").sendKeys(bs.getPropValue("systemcode.end_value3"));
		FEB("xpath", "//*[@id=\"code5\"]", "비고 입력칸").sendKeys(bs.getPropValue("systemcode.etc3"));
		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[2]", "등록 버튼").click();
		CustomWait(50);
		cp.acceptAlert();
	}
}
