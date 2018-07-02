package com.humuson.selenium.MySite.ChannelMngmt;

import java.util.regex.Pattern;

import org.openqa.selenium.By;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 MySite
 * @중분류 채널 관리
 * @시나리오명 Kakao 계정등록
 */
public class E_KakaoAccount extends Scenario {
	String delete_name = "삭제용";
	String delete_key = "7777";
	
	public E_KakaoAccount(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if (type != E)
			category[type] = title;
	}

	protected void DO() {
		if (Pattern.compile("/site/account").matcher(driver.getCurrentUrl()).find()) {
			click();

			register();
			modify();
			delete();
		} else {
			CHECK(false);
		}
	}

	private void click() {
		FEB("id", "regKakao", "Kakao 계정등록 탭").click();
	}

	private void register() {
		clear();
		FEB("id", "company_name", "업체명 이름 입력칸").sendKeys(pr.getPropValue("site.sender.name"));
		FEB("id", "sender_key", "발신프로필 키 입력칸").sendKeys(pr.getPropValue("site.sender.senderkey"));
		FEB("id", "regist", "등록 버튼").click();
		int cnt = FEB("id", "resultBody", "업체 리스트").findElements(By.tagName("tr")).size();
		if (!FEB("xpath", "//*[@id=\"resultBody\"]/tr[" + cnt + "]/td[1]/input", "업체 리스트의 업체명 이름 입력칸")
				.getAttribute("value").equals(pr.getPropValue("site.sender.name"))) {
			FAIL("업체 등록");
		}
	}

	private void modify() {
		CustomWait(10);
		clear();
		FEB("id", "company_name", "업체명 이름 입력칸").sendKeys("수정용");
		FEB("id", "sender_key", "발신프로필 키 입력칸").sendKeys("9999");
		FEB("id", "regist", "등록 버튼").click();
		
		driver.navigate().refresh();
		
		int cnt = FEB("id", "resultBody", "업체 리스트").findElements(By.tagName("tr")).size();

		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[1]/input", "업체 리스트의 마지막 업체명 입력칸").clear();
		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[1]/input", "업체 리스트의 마지막 업체명 입력칸").sendKeys(delete_name);
		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[2]/input", "업체 리스트의 마지막 발신프로필 키 입력칸").clear();
		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[2]/input", "업체 리스트의 마지막 발신프로필 키 입력칸")
				.sendKeys(delete_key);
		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[3]/span", "업체 리스트의 첫번째 수정 버튼").click();

		driver.navigate().refresh();
		
		if (FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[1]/input", "업체 리스트의 마지막 업체명 입력칸").getAttribute("value").equals("삭제용")
				&&	FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[2]/input", "업체 리스트의 마지막 발신프로필 키 입력칸").getAttribute("value").equals("7777")) {
		} else {
			FAIL("업체 수정");
		}
	}

	private void delete() {
		int cnt = FEB("id", "resultBody", "업체 리스트").findElements(By.tagName("tr")).size();

		if (FEB("xpath", "//*[@id=\"resultBody\"]/tr[" + cnt + "]/td[1]/input", "업체 리스트의 마지막 업체명 입력칸")
				.getAttribute("value").equals("삭제용"))
			FEB("xpath", "//*[@id=\"resultBody\"]/tr[" + cnt + "]/td[4]/span", "업체 리스트의 마지막 삭제 버튼").click();

		driver.navigate().refresh();
		if (cnt == FEB("id", "resultBody", "유저 리스트").findElements(By.tagName("tr")).size()) {
			FAIL("업체 삭제");
		}
	}

	private void clear() {
		FEB("id", "company_name", "업체명 입력칸").clear();
		FEB("id", "sender_key", "발신프로필 키 입력칸").clear();
	}
}
