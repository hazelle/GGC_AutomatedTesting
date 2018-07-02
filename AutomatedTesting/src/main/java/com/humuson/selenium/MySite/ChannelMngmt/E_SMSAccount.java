package com.humuson.selenium.MySite.ChannelMngmt;

import java.util.regex.Pattern;

import org.openqa.selenium.By;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 MySite
 * @중분류 채널 관리
 * @시나리오명 SMS 계정등록
 * */
public class E_SMSAccount extends Scenario {
	public E_SMSAccount(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		if (Pattern.compile("/site/account").matcher(driver.getCurrentUrl()).find()) {
			click();
			register();
			modify();
			delete();
		} else {
			CHECK(false);
			FAIL("SMS 계정등록 페이지 이동");
		}
	}

	private void click() {
		FEB("id", "regSMS", "SMS 계정등록 탭").click();
	}

	private void register() {
		clear();
		FEB("id", "userName", "보내는 사람 이름 입력칸").sendKeys(pr.getPropValue("site.sender.name"));
		FEB("id", "userPhone", "보내는 전화번호 입력칸").sendKeys(pr.getPropValue("site.sender.phone"));
		FEB("id", "regist", "등록 버튼").click();
		int cnt = FEB("id", "resultBody", "업체 리스트").findElements(By.tagName("tr")).size();
		if (!FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[1]/input", "유저 리스트의 첫번째 보내는 사람 이름 입력칸").getAttribute("value").equals(pr.getPropValue("site.sender.name"))) {
			FAIL("유저 등록");
		}
	}

	private void modify() {
		CustomWait(10);
		clear();
		FEB("id", "userName", "보내는 사람 이름 입력칸").sendKeys("수정용");
		FEB("id", "userPhone", "보내는 전화번호 입력칸").sendKeys("01099999999");
		FEB("id", "regist", "등록 버튼").click();
		
		driver.navigate().refresh();
		
		int cnt = FEB("id", "resultBody", "유저 리스트").findElements(By.tagName("tr")).size();

		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[1]/input", "유저 리스트의 마지막 보내는 사람 이름 입력칸").clear();
		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[1]/input", "유저 리스트의 마지막 보내는 사람 이름 입력칸").sendKeys("삭제용");
		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[2]/input", "유저 리스트의 마지막 보내는 전화번호 입력칸").clear();
		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[2]/input", "유저 리스트의 마지막 보내는 전화번호 입력칸").sendKeys("heejae2@humuson.com");
		FEB("xpath", "//*[@id=\"resultBody\"]/tr[1]/td[3]/span", "유저 리스트의 마지막 수정 버튼").click();

		driver.navigate().refresh();

		if(!FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[1]/input", "유저 리스트의 마지막 보내는 사람 이름 입력칸")
				.getAttribute("value").equals("삭제용")) {
			FAIL("유저 수정 : 유저 리스트의 마지막 보내는 사람 이름 입력칸");
		}
		if(!FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[2]/input", "유저 리스트의 마지막 보내는 전화번호 입력칸")
				.getAttribute("value").equals("01044444444")) {
			FAIL("유저 수정 : 유저 리스트의 마지막 보내는 전화번호 입력칸");
		}
	}

	private void delete() {
		int cnt = FEB("id", "resultBody", "유저 리스트").findElements(By.tagName("tr")).size();

		if (FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[1]/input", "유저 리스트의 마지막 보내는 사람 이름 입력칸").getAttribute("value")
				.equals("삭제용")) {
			FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[4]/span", "유저 리스트의 마지막 삭제 버튼").click();
		}
		driver.navigate().refresh();
		if (!(cnt == FEB("id", "resultBody", "유저 리스트").findElements(By.tagName("tr")).size() + 1)) {
			FAIL("유저 삭제 : 삭제");
		}
	}

	private void clear() {
		FEB("id", "userName", "보내는 사람 이름 입력칸").clear();
		FEB("id", "userPhone", "보내는 전화번호 입력칸").clear();
	}
}
