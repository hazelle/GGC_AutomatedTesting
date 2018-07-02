package com.humuson.selenium.System.Setting.MonitoringServer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 환경설정
 * @소분류 모니터링 서버
 * @시나리오명 모니터링 서버 리스트 등록
 */
public class E_RegistrMntrSvrList extends Scenario {
	private int cnt = 0;
	
	public E_RegistrMntrSvrList(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		click();
		fillOut();
		
		CHECK(check());
	}
	
	private void click() {
//		FEB("xpath", "//*[@id=\"page-wrapper\"]/div[1]/div/button", "등록 버튼").click();
//		if(FEB("xpath", "//*[@id=\"modalServer\"]", "서버 정보 모달창").getAttribute("display").equals("display: none;")) {
//			FEB("xpath", "//*[@id=\"page-wrapper\"]/div[1]/div/button", "등록 버튼").click();
//		}
		do {
			FEB("xpath", "//*[@id=\"page-wrapper\"]/div[1]/div/button", "등록 버튼").click();
		} while(FEB("xpath", "//*[@id=\"modalServer\"]", "서버 리스트 정보 모달창").getAttribute("style").equals("display: none;"));
	}
	
	private void fillOut() {
		List<WebElement> servers = FEB("xpath", "//*[@id=\"ListDiv\"]/div/div/table/tbody", "서버 리스트 tbody").findElements(By.tagName("tr"));
		cnt = servers.size();
		
		wait.until(ExpectedConditions.visibilityOf(FEB("id", "server_name", "서버명 입력칸"))); 
		
		FEB("id", "server_name", "서버명 입력칸").clear();
		FEB("id", "server_name", "서버명 입력칸").sendKeys(bs.getPropValue("monitoring.server.name"));
		
		FEB("id", "max_hdd", "HDD 전체 용량 입력칸").clear();
		FEB("id", "max_hdd", "HDD 전체 용량 입력칸").sendKeys(bs.getPropValue("monitoring.server.max.hdd"));
		
		FEB("id", "max_db", "DB 전체 용량 입력칸").clear();
		FEB("id", "max_db", "DB 전체 용량 입력칸").sendKeys(bs.getPropValue("monitoring.server.max.db"));
		
		FEB("id", "warn_hdd", "경고 알람 HDD 임계치 입력칸").clear();
		FEB("id", "warn_hdd", "경고 알람 HDD 임계치 입력칸").sendKeys(bs.getPropValue("monitoring.server.warn.hdd"));
		
		FEB("id", "warn_db", "경고 알람 DB 임계치 입력칸").clear();
		FEB("id", "warn_db", "경고 알람 DB 임계치 입력칸").sendKeys(bs.getPropValue("monitoring.server.warn.db"));
		
		FEB("id", "hdd_path", "HDD 경로 입력칸").clear();
		FEB("id", "hdd_path", "HDD 경로 입력칸").sendKeys(bs.getPropValue("monitoring.server.hdd.path"));
		
		FEB("id", "serverRegistBtn", "저장 버튼").click();
		cp.acceptAlert();
	}
	
	private boolean check() {
		driver.navigate().refresh();
		List<WebElement> servers_ = FEB("xpath", "//*[@id=\"ListDiv\"]/div/div/table/tbody", "서버 리스트 tbody").findElements(By.tagName("tr"));
		
		if(servers_.size()==cnt) {
			FAIL("모니터링 서버 추가");
			return false;
		} else {
			return true;
		}
	}
}
