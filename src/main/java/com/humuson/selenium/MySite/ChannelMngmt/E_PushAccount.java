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
 * @��з� MySite
 * @�ߺз� ä�� ����
 * @�ó������� Kakao �������
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
		FEB("id", "regPush", "Push ������� ��").click();
	}

	private void register() {
		FEB("id", "appGrpRegist", "APP ��� ��ư").click();

		if (!Pattern.compile("/site/account/push/appRegist").matcher(driver.getCurrentUrl()).find()) {
			FAIL("APP ��� ������ ����");
			return;
		}

		FEB("xpath", "//*[@id=\"appImageFile\"]", "�̹��� Upload ��ư").sendKeys(pr.getPropValue("site.app.image"));

		FEB("id", "appGrpName", "App �̸� �Է�ĭ").clear();
		FEB("id", "appGrpName", "App �̸� �Է�ĭ").sendKeys(pr.getPropValue("site.app.name"));

		menuCheck("//*[@id=\"platform_A_ICON\"]", "//*[@id=\"platform_A_ICON\"]", "Android �����̵� �޴�");
		menuCheck("//*[@id=\"platform_I_ICON\"]", "//*[@id=\"platform_I_ICON\"]", "iOS �����̵� �޴�");

		if (Boolean.valueOf(pr.getPropValue("site.app.android")))
			android();
		if (Boolean.valueOf(pr.getPropValue("site.app.ios")))
			ios();

		Select start = new Select(FEB("xpath", "//*[@id=\"autoBlockStart\"]", "�߼� ���� �ð� ù��° dropbox"));
		start.selectByValue(pr.getPropValue("site.app.block.start").length() == 1
				? "0" + pr.getPropValue("site.app.block.start") : pr.getPropValue("site.app.block.start"));
		Select end = new Select(FEB("xpath", "//*[@id=\"autoBlockEnd\"]", "�߼� ���� �ð� �ι�° dropbox"));
		end.selectByValue(pr.getPropValue("site.app.block.end").length() == 1
				? "0" + pr.getPropValue("site.app.block.end") : pr.getPropValue("site.app.block.end"));

		FEB("xpath", "//*[@id=\"btnSave\"]", "�Ϸ� ��ư").click();

		if (!check_register()) {
			FAIL("App ���");
		} else {
			OK("App ���");
			saveScreenShot(category, "App ���", ".jpg");
		}
	}

	private void modify() {
		FEB("id", "appGrpRegist", "APP ��� ��ư").click();
		if (!Pattern.compile("/site/account/push/appRegist").matcher(driver.getCurrentUrl()).find()) {
			FAIL("APP ��� ������ ����");
			return;
		}
		FEB("xpath", "//*[@id=\"appImageFile\"]", "�̹��� Upload ��ư").sendKeys(pr.getPropValue("site.app.image"));
		FEB("id", "appGrpName", "App �̸� �Է�ĭ").clear();
		FEB("id", "appGrpName", "App �̸� �Է�ĭ").sendKeys("������");
		menuCheck("//*[@id=\"platform_A_ICON\"]", "//*[@id=\"platform_A_ICON\"]", "Android �����̵� �޴�");
		menuCheck("//*[@id=\"platform_I_ICON\"]", "//*[@id=\"platform_I_ICON\"]", "iOS �����̵� �޴�");
		if (!FEB("xpath", "//*[@id=\"platform_A_ICON\"]", "Android �����̵� �޴�").getAttribute("flag").equals("open")) {
			FEB("xpath", "//*[@id=\"platform_A_ICON\"]", "Android �����̵� �޴� ����").click();
		}
		for (int i = 1; i <= 3; i++) {
			FEB("xpath", "//*[@id=\"apiKey" + i + "\"]", "API Key" + i + " �Է�ĭ").clear();
			FEB("xpath", "//*[@id=\"apiKey" + i + "\"]", "API Key" + i + " �Է�ĭ").sendKeys("123456");
		}
		FEB("xpath", "//*[@id=\"gcmNum\"]", "GCM ������Ʈ �ѹ�").clear();
		FEB("xpath", "//*[@id=\"gcmNum\"]", "GCM ������Ʈ �ѹ�").sendKeys("12");
		Select dropbox_private = new Select(FEB("xpath", "//*[@id=\"privateYN\"]", "Private ��뿩�� dropbox"));
		dropbox_private.selectByValue("N");
		dropbox_private.selectByValue("Y");
		if (FEB("xpath", "//*[@id=\"selectProtocol\"]", "Private �������� ���� div").getAttribute("display") == null
				|| FEB("xpath", "//*[@id=\"selectProtocol\"]", "Private �������� ���� div").getAttribute("display")
						.equals("block")) {
			Select dropbox_private_protocol = new Select(
					FEB("xpath", "//*[@id=\"privateProtocol\"]", "Private �������� dropbox"));
			dropbox_private_protocol
					.selectByValue(pr.getPropValue("site.app.private.protocol").equals("TCP") ? "T" : "S");
		} else if (FEB("xpath", "//*[@id=\"selectProtocol\"]", "Private �������� ���� div").getAttribute("display")
				.equals("none"))
			FAIL("Private �������� ���� div ȭ�����");
		Select start = new Select(FEB("xpath", "//*[@id=\"autoBlockStart\"]", "�߼� ���� �ð� ù��° dropbox"));
		start.selectByValue(pr.getPropValue("site.app.block.start").length() == 1
				? "0" + pr.getPropValue("site.app.block.start") : pr.getPropValue("site.app.block.start"));
		Select end = new Select(FEB("xpath", "//*[@id=\"autoBlockEnd\"]", "�߼� ���� �ð� �ι�° dropbox"));
		end.selectByValue(pr.getPropValue("site.app.block.end").length() == 1
				? "0" + pr.getPropValue("site.app.block.end") : pr.getPropValue("site.app.block.end"));
		FEB("xpath", "//*[@id=\"btnSave\"]", "�Ϸ� ��ư").click();
		List<WebElement> forms = FEB("xpath", "//*[@id=\"resultBody\"]", "App ����Ʈ").findElements(By.tagName("form"));
		WebElement w = FEB("xpath", "//*[@id=\"resultBody\"]", "App ����Ʈ");
		int check = 0;
		for (int i = 1; i <= forms.size(); i++) {
			CustomWait(2);
			if (w.findElement(By.xpath("//form["+i+"]/div[2]/table/tbody/tr/td[2]/table/tbody/tr[1]/td[1]")).getText()
					.equals("������")) {
				w.findElement(By.xpath("//form["+i+"]/div[2]/table/tbody/tr/td[2]/button")).click();
				check += 1;
				break;
			}
		}
		if (check == 0)
			FAIL("������ �� ����");

		// ���� ����
		FEB("id", "appGrpName", "App �̸� �Է�ĭ").clear();
		FEB("id", "appGrpName", "App �̸� �Է�ĭ").sendKeys("������");

		FEB("xpath", "//*[@id=\"btnUpdate\"]", "���� ��ư").click();

		if (!check_modify())
			FAIL("App ����");
		else
			OK("App ����");
	}

	private void delete() {
		CustomWait(30);
		List<WebElement> forms = FEB("xpath", "//*[@id=\"resultBody\"]", "App ����Ʈ").findElements(By.className("form_main"));
		int cnt = forms.size();
		WebElement w = FEB("xpath", "//*[@id=\"resultBody\"]", "App ����Ʈ");
		
		for (int i = 1; i <= cnt; i++) {
			if (w.findElement(By.xpath("//form["+i+"]/div[2]/table/tbody/tr/td[2]/table/tbody/tr[1]/td[1]")).getText().equals("������")) {
				w.findElement(By.xpath("//form["+i+"]/div[1]")).findElement(By.name("account_box_check")).click();
			}
		}
		List<WebElement> buttons = driver.findElements(By.xpath("//*[@id=\"appGrpRegist\"]"));
		for (int j = 0; j < buttons.size(); j++) {
			if (buttons.get(j).getText().equals("APP ����")) {
				buttons.get(j).click();
				cp.acceptAlert();
				cp.acceptAlert();
			}
		}
		forms.clear();
		forms = FEB("xpath", "//*[@id=\"resultBody\"]", "App ����Ʈ").findElements(By.tagName("form"));
		if (cnt == forms.size()) {
			FAIL("App ����");
		} else {
			OK("App ����");
		}
	}

	private void android() {
		if (!FEB("xpath", "//*[@id=\"platform_A_ICON\"]", "Android �����̵� �޴�").getAttribute("flag").equals("open")) {
			FEB("xpath", "//*[@id=\"platform_A_ICON\"]", "Android �����̵� �޴� ����").click();
		}

		for (int i = 1; i <= 3; i++) {
			FEB("xpath", "//*[@id=\"apiKey" + i + "\"]", "API Key" + i + " �Է�ĭ").clear();
			FEB("xpath", "//*[@id=\"apiKey" + i + "\"]", "API Key" + i + " �Է�ĭ")
					.sendKeys(pr.getPropValue("site.app.apikey" + i));
		}
		FEB("xpath", "//*[@id=\"gcmNum\"]", "GCM ������Ʈ �ѹ�").clear();
		FEB("xpath", "//*[@id=\"gcmNum\"]", "GCM ������Ʈ �ѹ�").sendKeys(pr.getPropValue("site.app.gcmnum"));

		Select dropbox_private = new Select(FEB("xpath", "//*[@id=\"privateYN\"]", "Private ��뿩�� dropbox"));
		dropbox_private.selectByValue("N");

		if (pr.getPropValue("site.app.private.use").equals("true")) {
			dropbox_private.selectByValue("Y");
			if (FEB("xpath", "//*[@id=\"selectProtocol\"]", "Private �������� ���� div").getAttribute("display") == null
					|| FEB("xpath", "//*[@id=\"selectProtocol\"]", "Private �������� ���� div").getAttribute("display")
							.equals("block")) {
				Select dropbox_private_protocol = new Select(
						FEB("xpath", "//*[@id=\"privateProtocol\"]", "Private �������� dropbox"));
				dropbox_private_protocol
						.selectByValue(pr.getPropValue("site.app.private.protocol").equals("TCP") ? "T" : "S");
			} else if (FEB("xpath", "//*[@id=\"selectProtocol\"]", "Private �������� ���� div").getAttribute("display")
					.equals("none"))
				FAIL("Private �������� ���� div ȭ�����");

		} else {
			dropbox_private.selectByValue("N");
			if (!FEB("xpath", "//*[@id=\"selectProtocol\"]", "Private �������� ���� div").getAttribute("style")
					.equals("display: none;"))
				FAIL("Private �������� ���� div ȭ�����");
		}
	}

	private void ios() {
		if (!FEB("xpath", "//*[@id=\"platform_I_ICON\"]", "iOS �����̵� �޴�").getAttribute("flag").equals("open")) {
			FEB("xpath", "//*[@id=\"platform_I_ICON\"]", "iOS �����̵� �޴� ����").click();
		}

		FEB("xpath", "//*[@id=\"upfile\"]", "P12 file ���ε�").sendKeys(pr.getPropValue("site.app.p12file"));
		FEB("xpath", "//*[@id=\"apnsPW\"]", "APNS PW �Է�ĭ").sendKeys(pr.getPropValue("site.app.apnspw"));
		FEB("xpath", "//*[@id=\"pushSound\"]", "Push Sound �Է�ĭ").clear();
		FEB("xpath", "//*[@id=\"pushSound\"]", "Push Sound �Է�ĭ").sendKeys(
				pr.getPropValue("site.app.pushsound").equals("") ? "default" : pr.getPropValue("site.app.pushsound"));
		Select dropbox_badge_count = new Select(FEB("xpath", "//*[@id=\"badgeFlag\"]", "Badge Count dropbox"));
		dropbox_badge_count.selectByValue(pr.getPropValue("site.app.badgecount").equals("true") ? "Y" : "N");

	}

	private boolean menuCheck(String menu, String click, String name) {
		boolean result = true;
		if (FEB("xpath", menu, name).getAttribute("flag") == "close") {
			CustomWait(5);
			FEB("xpath", click, name + " ����").click();
			CustomWait(5);
			if (FEB("xpath", menu, name).getAttribute("flag").equals("close")) {
				FAIL(name + " ����");
				result &= false;
			}
		}

		if (FEB("xpath", menu, name).getAttribute("flag").equals("open")) {
			CustomWait(5);
			FEB("xpath", click, name + " �ݱ�").click();
			CustomWait(5);
			if (FEB("xpath", menu, name).getAttribute("flag").equals("open")) {
				FAIL(name + " �ݱ�");
				result &= false;
			}
		}
		return result;
	}

	private boolean check_register() {
		List<WebElement> forms = FEB("xpath", "//*[@id=\"resultBody\"]", "App ����Ʈ").findElements(By.tagName("form"));
		WebElement w = FEB("xpath", "//*[@id=\"resultBody\"]", "App ����Ʈ");
		
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
		List<WebElement> forms = FEB("xpath", "//*[@id=\"resultBody\"]", "App ����Ʈ").findElements(By.tagName("form"));
		WebElement w = FEB("xpath", "//*[@id=\"resultBody\"]", "App ����Ʈ");
		
		for (int i = 1; i <= forms.size(); i++) {
			CustomWait(2);
			if (w.findElement(By.xpath("//form["+i+"]/div[2]/table/tbody/tr/td[2]/table/tbody/tr[1]/td[1]")).getText()
					.equals("������")) {
				return true;
			}
		}
		return false;
	}
}
