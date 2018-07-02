package com.humuson.selenium.MySite.ChannelMngmt;

import java.util.regex.Pattern;

import org.openqa.selenium.By;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� MySite
 * @�ߺз� ä�� ����
 * @�ó������� Kakao �������
 */
public class E_KakaoAccount extends Scenario {
	String delete_name = "������";
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
		FEB("id", "regKakao", "Kakao ������� ��").click();
	}

	private void register() {
		clear();
		FEB("id", "company_name", "��ü�� �̸� �Է�ĭ").sendKeys(pr.getPropValue("site.sender.name"));
		FEB("id", "sender_key", "�߽������� Ű �Է�ĭ").sendKeys(pr.getPropValue("site.sender.senderkey"));
		FEB("id", "regist", "��� ��ư").click();
		int cnt = FEB("id", "resultBody", "��ü ����Ʈ").findElements(By.tagName("tr")).size();
		if (!FEB("xpath", "//*[@id=\"resultBody\"]/tr[" + cnt + "]/td[1]/input", "��ü ����Ʈ�� ��ü�� �̸� �Է�ĭ")
				.getAttribute("value").equals(pr.getPropValue("site.sender.name"))) {
			FAIL("��ü ���");
		}
	}

	private void modify() {
		CustomWait(10);
		clear();
		FEB("id", "company_name", "��ü�� �̸� �Է�ĭ").sendKeys("������");
		FEB("id", "sender_key", "�߽������� Ű �Է�ĭ").sendKeys("9999");
		FEB("id", "regist", "��� ��ư").click();
		
		driver.navigate().refresh();
		
		int cnt = FEB("id", "resultBody", "��ü ����Ʈ").findElements(By.tagName("tr")).size();

		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[1]/input", "��ü ����Ʈ�� ������ ��ü�� �Է�ĭ").clear();
		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[1]/input", "��ü ����Ʈ�� ������ ��ü�� �Է�ĭ").sendKeys(delete_name);
		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[2]/input", "��ü ����Ʈ�� ������ �߽������� Ű �Է�ĭ").clear();
		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[2]/input", "��ü ����Ʈ�� ������ �߽������� Ű �Է�ĭ")
				.sendKeys(delete_key);
		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[3]/span", "��ü ����Ʈ�� ù��° ���� ��ư").click();

		driver.navigate().refresh();
		
		if (FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[1]/input", "��ü ����Ʈ�� ������ ��ü�� �Է�ĭ").getAttribute("value").equals("������")
				&&	FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[2]/input", "��ü ����Ʈ�� ������ �߽������� Ű �Է�ĭ").getAttribute("value").equals("7777")) {
		} else {
			FAIL("��ü ����");
		}
	}

	private void delete() {
		int cnt = FEB("id", "resultBody", "��ü ����Ʈ").findElements(By.tagName("tr")).size();

		if (FEB("xpath", "//*[@id=\"resultBody\"]/tr[" + cnt + "]/td[1]/input", "��ü ����Ʈ�� ������ ��ü�� �Է�ĭ")
				.getAttribute("value").equals("������"))
			FEB("xpath", "//*[@id=\"resultBody\"]/tr[" + cnt + "]/td[4]/span", "��ü ����Ʈ�� ������ ���� ��ư").click();

		driver.navigate().refresh();
		if (cnt == FEB("id", "resultBody", "���� ����Ʈ").findElements(By.tagName("tr")).size()) {
			FAIL("��ü ����");
		}
	}

	private void clear() {
		FEB("id", "company_name", "��ü�� �Է�ĭ").clear();
		FEB("id", "sender_key", "�߽������� Ű �Է�ĭ").clear();
	}
}
