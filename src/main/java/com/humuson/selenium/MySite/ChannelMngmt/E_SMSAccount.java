package com.humuson.selenium.MySite.ChannelMngmt;

import java.util.regex.Pattern;

import org.openqa.selenium.By;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� MySite
 * @�ߺз� ä�� ����
 * @�ó������� SMS �������
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
			FAIL("SMS ������� ������ �̵�");
		}
	}

	private void click() {
		FEB("id", "regSMS", "SMS ������� ��").click();
	}

	private void register() {
		clear();
		FEB("id", "userName", "������ ��� �̸� �Է�ĭ").sendKeys(pr.getPropValue("site.sender.name"));
		FEB("id", "userPhone", "������ ��ȭ��ȣ �Է�ĭ").sendKeys(pr.getPropValue("site.sender.phone"));
		FEB("id", "regist", "��� ��ư").click();
		int cnt = FEB("id", "resultBody", "��ü ����Ʈ").findElements(By.tagName("tr")).size();
		if (!FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[1]/input", "���� ����Ʈ�� ù��° ������ ��� �̸� �Է�ĭ").getAttribute("value").equals(pr.getPropValue("site.sender.name"))) {
			FAIL("���� ���");
		}
	}

	private void modify() {
		CustomWait(10);
		clear();
		FEB("id", "userName", "������ ��� �̸� �Է�ĭ").sendKeys("������");
		FEB("id", "userPhone", "������ ��ȭ��ȣ �Է�ĭ").sendKeys("01099999999");
		FEB("id", "regist", "��� ��ư").click();
		
		driver.navigate().refresh();
		
		int cnt = FEB("id", "resultBody", "���� ����Ʈ").findElements(By.tagName("tr")).size();

		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[1]/input", "���� ����Ʈ�� ������ ������ ��� �̸� �Է�ĭ").clear();
		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[1]/input", "���� ����Ʈ�� ������ ������ ��� �̸� �Է�ĭ").sendKeys("������");
		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[2]/input", "���� ����Ʈ�� ������ ������ ��ȭ��ȣ �Է�ĭ").clear();
		FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[2]/input", "���� ����Ʈ�� ������ ������ ��ȭ��ȣ �Է�ĭ").sendKeys("heejae2@humuson.com");
		FEB("xpath", "//*[@id=\"resultBody\"]/tr[1]/td[3]/span", "���� ����Ʈ�� ������ ���� ��ư").click();

		driver.navigate().refresh();

		if(!FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[1]/input", "���� ����Ʈ�� ������ ������ ��� �̸� �Է�ĭ")
				.getAttribute("value").equals("������")) {
			FAIL("���� ���� : ���� ����Ʈ�� ������ ������ ��� �̸� �Է�ĭ");
		}
		if(!FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[2]/input", "���� ����Ʈ�� ������ ������ ��ȭ��ȣ �Է�ĭ")
				.getAttribute("value").equals("01044444444")) {
			FAIL("���� ���� : ���� ����Ʈ�� ������ ������ ��ȭ��ȣ �Է�ĭ");
		}
	}

	private void delete() {
		int cnt = FEB("id", "resultBody", "���� ����Ʈ").findElements(By.tagName("tr")).size();

		if (FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[1]/input", "���� ����Ʈ�� ������ ������ ��� �̸� �Է�ĭ").getAttribute("value")
				.equals("������")) {
			FEB("xpath", "//*[@id=\"resultBody\"]/tr["+cnt+"]/td[4]/span", "���� ����Ʈ�� ������ ���� ��ư").click();
		}
		driver.navigate().refresh();
		if (!(cnt == FEB("id", "resultBody", "���� ����Ʈ").findElements(By.tagName("tr")).size() + 1)) {
			FAIL("���� ���� : ����");
		}
	}

	private void clear() {
		FEB("id", "userName", "������ ��� �̸� �Է�ĭ").clear();
		FEB("id", "userPhone", "������ ��ȭ��ȣ �Է�ĭ").clear();
	}
}
