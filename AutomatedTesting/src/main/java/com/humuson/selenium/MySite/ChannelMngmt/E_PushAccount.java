package com.humuson.selenium.MySite.ChannelMngmt;

import java.util.List;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * 
 * @대분류 MySite
 * @중분류 채널 관리
 * @시나리오명 Kakao 계정등록
 */
public class E_PushAccount extends Scenario {
	public E_PushAccount(int type, String[] category, String title) {
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
		}
	}

	private void click() {
		FEB("id", "regPush", "Push 계정등록 탭").click();
	}

	private void register() {
		FEB("id", "appGrpRegist", "APP 등록 버튼").click();

		if (!Pattern.compile("/site/account/push/appRegist").matcher(driver.getCurrentUrl()).find()) {
			FAIL("APP 등록 페이지 진입");
			return;
		}

		FEB("xpath", "//*[@id=\"appImageFile\"]", "이미지 Upload 버튼").sendKeys(pr.getPropValue("site.app.image"));

		FEB("id", "appGrpName", "App 이름 입력칸").clear();
		FEB("id", "appGrpName", "App 이름 입력칸").sendKeys(pr.getPropValue("site.app.name"));

		menuCheck("//*[@id=\"platform_A_ICON\"]", "//*[@id=\"platform_A_ICON\"]", "Android 슬라이드 메뉴");
		menuCheck("//*[@id=\"platform_I_ICON\"]", "//*[@id=\"platform_I_ICON\"]", "iOS 슬라이드 메뉴");

		if (Boolean.valueOf(pr.getPropValue("site.app.android")))
			android();
		if (Boolean.valueOf(pr.getPropValue("site.app.ios")))
			ios();

		Select start = new Select(FEB("xpath", "//*[@id=\"autoBlockStart\"]", "발송 차단 시간 첫번째 dropbox"));
		start.selectByValue(pr.getPropValue("site.app.block.start").length() == 1
				? "0" + pr.getPropValue("site.app.block.start") : pr.getPropValue("site.app.block.start"));
		Select end = new Select(FEB("xpath", "//*[@id=\"autoBlockEnd\"]", "발송 차단 시간 두번째 dropbox"));
		end.selectByValue(pr.getPropValue("site.app.block.end").length() == 1
				? "0" + pr.getPropValue("site.app.block.end") : pr.getPropValue("site.app.block.end"));

		FEB("xpath", "//*[@id=\"btnSave\"]", "완료 버튼").click();

		if (!check_register()) {
			FAIL("App 등록");
		} else {
			OK("App 등록");
			saveScreenShot(category, "App 등록", ".jpg");
		}
	}

	private void modify() {
		FEB("id", "appGrpRegist", "APP 등록 버튼").click();
		if (!Pattern.compile("/site/account/push/appRegist").matcher(driver.getCurrentUrl()).find()) {
			FAIL("APP 등록 페이지 진입");
			return;
		}
		FEB("xpath", "//*[@id=\"appImageFile\"]", "이미지 Upload 버튼").sendKeys(pr.getPropValue("site.app.image"));
		FEB("id", "appGrpName", "App 이름 입력칸").clear();
		FEB("id", "appGrpName", "App 이름 입력칸").sendKeys("수정용");
		menuCheck("//*[@id=\"platform_A_ICON\"]", "//*[@id=\"platform_A_ICON\"]", "Android 슬라이드 메뉴");
		menuCheck("//*[@id=\"platform_I_ICON\"]", "//*[@id=\"platform_I_ICON\"]", "iOS 슬라이드 메뉴");
		if (!FEB("xpath", "//*[@id=\"platform_A_ICON\"]", "Android 슬라이드 메뉴").getAttribute("flag").equals("open")) {
			FEB("xpath", "//*[@id=\"platform_A_ICON\"]", "Android 슬라이드 메뉴 열기").click();
		}
		for (int i = 1; i <= 3; i++) {
			FEB("xpath", "//*[@id=\"apiKey" + i + "\"]", "API Key" + i + " 입력칸").clear();
			FEB("xpath", "//*[@id=\"apiKey" + i + "\"]", "API Key" + i + " 입력칸").sendKeys("123456");
		}
		FEB("xpath", "//*[@id=\"gcmNum\"]", "GCM 프로젝트 넘버").clear();
		FEB("xpath", "//*[@id=\"gcmNum\"]", "GCM 프로젝트 넘버").sendKeys("12");
		Select dropbox_private = new Select(FEB("xpath", "//*[@id=\"privateYN\"]", "Private 사용여부 dropbox"));
		dropbox_private.selectByValue("N");
		dropbox_private.selectByValue("Y");
		if (FEB("xpath", "//*[@id=\"selectProtocol\"]", "Private 프로토콜 선택 div").getAttribute("display") == null
				|| FEB("xpath", "//*[@id=\"selectProtocol\"]", "Private 프로토콜 선택 div").getAttribute("display")
						.equals("block")) {
			Select dropbox_private_protocol = new Select(
					FEB("xpath", "//*[@id=\"privateProtocol\"]", "Private 프로토콜 dropbox"));
			dropbox_private_protocol
					.selectByValue(pr.getPropValue("site.app.private.protocol").equals("TCP") ? "T" : "S");
		} else if (FEB("xpath", "//*[@id=\"selectProtocol\"]", "Private 프로토콜 선택 div").getAttribute("display")
				.equals("none"))
			FAIL("Private 프로토콜 선택 div 화면출력");
		Select start = new Select(FEB("xpath", "//*[@id=\"autoBlockStart\"]", "발송 차단 시간 첫번째 dropbox"));
		start.selectByValue(pr.getPropValue("site.app.block.start").length() == 1
				? "0" + pr.getPropValue("site.app.block.start") : pr.getPropValue("site.app.block.start"));
		Select end = new Select(FEB("xpath", "//*[@id=\"autoBlockEnd\"]", "발송 차단 시간 두번째 dropbox"));
		end.selectByValue(pr.getPropValue("site.app.block.end").length() == 1
				? "0" + pr.getPropValue("site.app.block.end") : pr.getPropValue("site.app.block.end"));
		FEB("xpath", "//*[@id=\"btnSave\"]", "완료 버튼").click();
		List<WebElement> forms = FEB("xpath", "//*[@id=\"resultBody\"]", "App 리스트").findElements(By.tagName("form"));
		WebElement w = FEB("xpath", "//*[@id=\"resultBody\"]", "App 리스트");
		int check = 0;
		for (int i = 1; i <= forms.size(); i++) {
			CustomWait(2);
			if (w.findElement(By.xpath("//form["+i+"]/div[2]/table/tbody/tr/td[2]/table/tbody/tr[1]/td[1]")).getText()
					.equals("수정용")) {
				w.findElement(By.xpath("//form["+i+"]/div[2]/table/tbody/tr/td[2]/button")).click();
				check += 1;
				break;
			}
		}
		if (check == 0)
			FAIL("수정할 앱 선택");

		// 수정 시작
		FEB("id", "appGrpName", "App 이름 입력칸").clear();
		FEB("id", "appGrpName", "App 이름 입력칸").sendKeys("삭제용");

		FEB("xpath", "//*[@id=\"btnUpdate\"]", "수정 버튼").click();

		if (!check_modify())
			FAIL("App 수정");
		else
			OK("App 수정");
	}

	private void delete() {
		CustomWait(30);
		List<WebElement> forms = FEB("xpath", "//*[@id=\"resultBody\"]", "App 리스트").findElements(By.className("form_main"));
		int cnt = forms.size();
		WebElement w = FEB("xpath", "//*[@id=\"resultBody\"]", "App 리스트");
		
		for (int i = 1; i <= cnt; i++) {
			if (w.findElement(By.xpath("//form["+i+"]/div[2]/table/tbody/tr/td[2]/table/tbody/tr[1]/td[1]")).getText().equals("삭제용")) {
				w.findElement(By.xpath("//form["+i+"]/div[1]")).findElement(By.name("account_box_check")).click();
			}
		}
		List<WebElement> buttons = driver.findElements(By.xpath("//*[@id=\"appGrpRegist\"]"));
		for (int j = 0; j < buttons.size(); j++) {
			if (buttons.get(j).getText().equals("APP 삭제")) {
				buttons.get(j).click();
				cp.acceptAlert();
				cp.acceptAlert();
			}
		}
		forms.clear();
		forms = FEB("xpath", "//*[@id=\"resultBody\"]", "App 리스트").findElements(By.tagName("form"));
		if (cnt == forms.size()) {
			FAIL("App 삭제");
		} else {
			OK("App 삭제");
		}
	}

	private void android() {
		if (!FEB("xpath", "//*[@id=\"platform_A_ICON\"]", "Android 슬라이드 메뉴").getAttribute("flag").equals("open")) {
			FEB("xpath", "//*[@id=\"platform_A_ICON\"]", "Android 슬라이드 메뉴 열기").click();
		}

		for (int i = 1; i <= 3; i++) {
			FEB("xpath", "//*[@id=\"apiKey" + i + "\"]", "API Key" + i + " 입력칸").clear();
			FEB("xpath", "//*[@id=\"apiKey" + i + "\"]", "API Key" + i + " 입력칸")
					.sendKeys(pr.getPropValue("site.app.apikey" + i));
		}
		FEB("xpath", "//*[@id=\"gcmNum\"]", "GCM 프로젝트 넘버").clear();
		FEB("xpath", "//*[@id=\"gcmNum\"]", "GCM 프로젝트 넘버").sendKeys(pr.getPropValue("site.app.gcmnum"));

		Select dropbox_private = new Select(FEB("xpath", "//*[@id=\"privateYN\"]", "Private 사용여부 dropbox"));
		dropbox_private.selectByValue("N");

		if (pr.getPropValue("site.app.private.use").equals("true")) {
			dropbox_private.selectByValue("Y");
			if (FEB("xpath", "//*[@id=\"selectProtocol\"]", "Private 프로토콜 선택 div").getAttribute("display") == null
					|| FEB("xpath", "//*[@id=\"selectProtocol\"]", "Private 프로토콜 선택 div").getAttribute("display")
							.equals("block")) {
				Select dropbox_private_protocol = new Select(
						FEB("xpath", "//*[@id=\"privateProtocol\"]", "Private 프로토콜 dropbox"));
				dropbox_private_protocol
						.selectByValue(pr.getPropValue("site.app.private.protocol").equals("TCP") ? "T" : "S");
			} else if (FEB("xpath", "//*[@id=\"selectProtocol\"]", "Private 프로토콜 선택 div").getAttribute("display")
					.equals("none"))
				FAIL("Private 프로토콜 선택 div 화면출력");

		} else {
			dropbox_private.selectByValue("N");
			if (!FEB("xpath", "//*[@id=\"selectProtocol\"]", "Private 프로토콜 선택 div").getAttribute("style")
					.equals("display: none;"))
				FAIL("Private 프로토콜 선택 div 화면숨김");
		}
	}

	private void ios() {
		if (!FEB("xpath", "//*[@id=\"platform_I_ICON\"]", "iOS 슬라이드 메뉴").getAttribute("flag").equals("open")) {
			FEB("xpath", "//*[@id=\"platform_I_ICON\"]", "iOS 슬라이드 메뉴 열기").click();
		}

		FEB("xpath", "//*[@id=\"upfile\"]", "P12 file 업로드").sendKeys(pr.getPropValue("site.app.p12file"));
		FEB("xpath", "//*[@id=\"apnsPW\"]", "APNS PW 입력칸").sendKeys(pr.getPropValue("site.app.apnspw"));
		FEB("xpath", "//*[@id=\"pushSound\"]", "Push Sound 입력칸").clear();
		FEB("xpath", "//*[@id=\"pushSound\"]", "Push Sound 입력칸").sendKeys(
				pr.getPropValue("site.app.pushsound").equals("") ? "default" : pr.getPropValue("site.app.pushsound"));
		Select dropbox_badge_count = new Select(FEB("xpath", "//*[@id=\"badgeFlag\"]", "Badge Count dropbox"));
		dropbox_badge_count.selectByValue(pr.getPropValue("site.app.badgecount").equals("true") ? "Y" : "N");

	}

	private boolean menuCheck(String menu, String click, String name) {
		boolean result = true;
		if (FEB("xpath", menu, name).getAttribute("flag") == "close") {
			CustomWait(5);
			FEB("xpath", click, name + " 열기").click();
			CustomWait(5);
			if (FEB("xpath", menu, name).getAttribute("flag").equals("close")) {
				FAIL(name + " 열기");
				result &= false;
			}
		}

		if (FEB("xpath", menu, name).getAttribute("flag").equals("open")) {
			CustomWait(5);
			FEB("xpath", click, name + " 닫기").click();
			CustomWait(5);
			if (FEB("xpath", menu, name).getAttribute("flag").equals("open")) {
				FAIL(name + " 닫기");
				result &= false;
			}
		}
		return result;
	}

	private boolean check_register() {
		List<WebElement> forms = FEB("xpath", "//*[@id=\"resultBody\"]", "App 리스트").findElements(By.tagName("form"));
		WebElement w = FEB("xpath", "//*[@id=\"resultBody\"]", "App 리스트");
		
		for (int i = 1; i <= forms.size(); i++) {
			CustomWait(2);
			if (w.findElement(By.xpath("//form["+i+"]/div[2]/table/tbody/tr/td[2]/table/tbody/tr[1]/td[1]")).getText()
					.equals(pr.getPropValue("site.app.name"))) {
				return true;
			}
		}
		return false;
	}

	private boolean check_modify() {
		List<WebElement> forms = FEB("xpath", "//*[@id=\"resultBody\"]", "App 리스트").findElements(By.tagName("form"));
		WebElement w = FEB("xpath", "//*[@id=\"resultBody\"]", "App 리스트");
		
		for (int i = 1; i <= forms.size(); i++) {
			CustomWait(2);
			if (w.findElement(By.xpath("//form["+i+"]/div[2]/table/tbody/tr/td[2]/table/tbody/tr[1]/td[1]")).getText()
					.equals("삭제용")) {
				return true;
			}
		}
		return false;
	}
}
