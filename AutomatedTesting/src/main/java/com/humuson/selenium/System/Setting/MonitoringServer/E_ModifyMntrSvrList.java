package com.humuson.selenium.System.Setting.MonitoringServer;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * 
 * @대분류 System
 * @중분류 환경설정
 * @소분류 모니터링 서버
 * @시나리오명 모니터링 서버 리스트 수정
 */
public class E_ModifyMntrSvrList extends Scenario {
	private String add = "2";
	
	public E_ModifyMntrSvrList(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if (type != E)
			category[type] = title;
	}

	protected void DO() {
		driver.navigate().refresh();
		register();
		click();
		if(check_contents()) modify();
		CHECK(check_contents(add));
		
	}
	
	private void register() {
		driver.navigate().refresh();
		do {
			FEB("xpath", "//*[@id=\"page-wrapper\"]/div[1]/div/button", "등록 버튼").click();
		} while(FEB("xpath", "//*[@id=\"modalServer\"]", "서버 리스트 정보 모달창").getAttribute("style").equals("display: none;"));
		wait.until(ExpectedConditions.visibilityOf(FEB("id", "server_name", "서버명 입력칸"))); 
		FEB("id", "server_name", "서버명 입력칸").clear();
		FEB("id", "server_name", "서버명 입력칸").sendKeys("수정용");
		
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
		
		FEB("xpath", "//*[@id=\"serverRegistBtn\"]", "등록 버튼").click();
		cp.acceptAlert();
	}

	private void click() {
		driver.navigate().refresh();
		List<WebElement> servers = FEB("xpath", "//*[@id=\"ListDiv\"]/div/div/table/tbody", "서버 리스트").findElements(By.tagName("tr"));
		for (int i = 1; i < servers.size() - 8; i++) {
			if (i % 2 == 1
					&& driver.findElement(By.xpath("//*[@id=\"ListDiv\"]/div/div/table/tbody/tr[" + i + "]/td[2]/a"))
							.getText().trim().equals("수정용")) {
				driver.findElement(By.xpath("//*[@id=\"ListDiv\"]/div/div/table/tbody/tr[" + i + "]/td[4]/a[1]")).click();
			}
		}
	}

	private boolean check_contents() {
		boolean result = true;
		
		result &= FEB("id", "server_name", "서버명 입력칸").getAttribute("value")
				.equals("수정용");
		result &= FEB("id", "max_hdd", "HDD 전체 용량 입력칸").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.max.hdd"));
		result &= FEB("id", "max_db", "DB 전체 용량 입력칸").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.max.db"));
		result &= FEB("id", "warn_hdd", "경고 알람 HDD 임계치 입력칸").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.warn.hdd"));
		result &= FEB("id", "warn_db", "경고 알람 DB 임계치 입력칸").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.warn.db"));
		result &= FEB("id", "hdd_path", "HDD 경로 입력칸").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.hdd.path"));
		
		return result;
	}
	private void modify() {
		FEB("id", "server_name", "서버명 입력칸").clear();
		FEB("id", "server_name", "서버명 입력칸").sendKeys("삭제용");
		
		FEB("id", "max_hdd", "HDD 전체 용량 입력칸").clear();
		FEB("id", "max_hdd", "HDD 전체 용량 입력칸").sendKeys(bs.getPropValue("monitoring.server.max.hdd")+add);
		
		FEB("id", "max_db", "DB 전체 용량 입력칸").clear();
		FEB("id", "max_db", "DB 전체 용량 입력칸").sendKeys(bs.getPropValue("monitoring.server.max.db")+add);
		
		FEB("id", "warn_hdd", "경고 알람 HDD 임계치 입력칸").clear();
		FEB("id", "warn_hdd", "경고 알람 HDD 임계치 입력칸").sendKeys(bs.getPropValue("monitoring.server.warn.hdd")+add);
		
		FEB("id", "warn_db", "경고 알람 DB 임계치 입력칸").clear();
		FEB("id", "warn_db", "경고 알람 DB 임계치 입력칸").sendKeys(bs.getPropValue("monitoring.server.warn.db")+add);
		
		FEB("id", "hdd_path", "HDD 경로 입력칸").clear();
		FEB("id", "hdd_path", "HDD 경로 입력칸").sendKeys(bs.getPropValue("monitoring.server.hdd.path")+add);
		
		FEB("xpath", "//*[@id=\"serverEditBtn\"]", "등록 버튼").click();
		cp.acceptAlert();
	}
	
	private boolean check_contents(String add) {
		boolean result = true;
		
		boolean result1 = FEB("id", "server_name", "서버명 입력칸").getAttribute("value")
				.equals("삭제용");
		if(!result1) FAIL("서버명 입력칸 수정");
		boolean result2 = FEB("id", "max_hdd", "HDD 전체 용량 입력칸").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.max.hdd")+add);
		if(!result2) FAIL("HDD 전체 용량 수정");
		boolean result3 = FEB("id", "max_db", "DB 전체 용량 입력칸").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.max.db")+add);
		if(!result3) FAIL("DB 전체 용량 수정");
		boolean result4 = FEB("id", "warn_hdd", "경고 알람 HDD 임계치 입력칸").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.warn.hdd")+add);
		if(!result4) FAIL("경고 알람 HDD 임계치 수정");
		boolean result5 = FEB("id", "warn_db", "경고 알람 DB 임계치 입력칸").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.warn.db")+add);
		if(!result5) FAIL("경고 알람 DB 임계치 수정");
		boolean result6 = FEB("id", "hdd_path", "HDD 경로 입력칸").getAttribute("value")
				.equals(bs.getPropValue("monitoring.server.hdd.path")+add);
		if(!result6) FAIL("HDD 경로 수정");
		return result;
	}
	
}


