package com.humuson.selenium.MySite.ChannelMngmt;

import java.util.regex.Pattern;

import org.openqa.selenium.By;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� MySite
 * @�ߺз� ä�� ����
 * @�ó������� E-mail �������
 */
public class E_EmailAccount extends Scenario {
	public E_EmailAccount(int type, String[] category, String title) {
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
			FAIL("E-mail ������� ������ �̵�");
		}
	}

	private void click() {
		FEB("id", "regEmail", "E-mail ������� ��").click();
	}

	private void register() {
		clear();
		FEB("id", "userName", "������ ��� �̸� �Է�ĭ").sendKeys(pr.getPropValue("site.sender.name"));
		FEB("id", "userEmail", "������ �̸��� �Է�ĭ").sendKeys(pr.getPropValue("site.sender.email"));
		FEB("id", "return", "���� ���� �Է�ĭ").sendKeys(pr.getPropValue("site.return.email"));
		FEB("id", "btnRegisterUser", "��� ��ư").click();
		if (!FEB("xpath", "//*[@id=\"resultBody\"]/tr[1]/td[1]/input", "���� ����Ʈ�� ù��° ������ ��� �̸� �Է�ĭ").getAttribute("value").equals(pr.getPropValue("site.sender.name"))) {
			FAIL("���� ���");
		}
	}

	private void modify() {
		CustomWait(10);
		clear();
		FEB("id", "userName", "������ ��� �̸� �Է�ĭ").sendKeys("������");
		FEB("id", "userEmail", "������ �̸��� �Է�ĭ").sendKeys("heejae2@humuson.com");
		FEB("id", "return", "���� ���� �Է�ĭ").sendKeys("heejae2@humuson.com");
		FEB("id", "btnRegisterUser", "��� ��ư").click();
		
		driver.navigate().refresh();

		FEB("xpath", "//*[@id=\"resultBody\"]/tr[1]/td[1]/input", "���� ����Ʈ�� ù��° ������ ��� �̸� �Է�ĭ").clear();
		FEB("xpath", "//*[@id=\"resultBody\"]/tr[1]/td[1]/input", "���� ����Ʈ�� ù��° ������ ��� �̸� �Է�ĭ").sendKeys("������");
		FEB("xpath", "//*[@id=\"resultBody\"]/tr[1]/td[2]/input", "���� ����Ʈ�� ù��° ������ �̸��� �Է�ĭ").clear();
		FEB("xpath", "//*[@id=\"resultBody\"]/tr[1]/td[2]/input", "���� ����Ʈ�� ù��° ������ �̸��� �Է�ĭ")
				.sendKeys("heejae2@humuson.com");
		FEB("xpath", "//*[@id=\"resultBody\"]/tr[1]/td[3]/input", "���� ����Ʈ�� ù��° ���� �̸��� �Է�ĭ").clear();
		FEB("xpath", "//*[@id=\"resultBody\"]/tr[1]/td[3]/input", "���� ����Ʈ�� ù��° ���� �̸��� �Է�ĭ")
				.sendKeys("heejae2@humuson.com");
		FEB("xpath", "//*[@id=\"resultBody\"]/tr[1]/td[6]/button", "���� ����Ʈ�� ù��° ���� ��ư").click();

		driver.navigate().refresh();
		
		if(FEB("xpath", "//*[@id=\"resultBody\"]/tr[1]/td[1]/input", "���� ����Ʈ�� ù��° ������ ��� �̸� �Է�ĭ")
				.getAttribute("value").equals("������") 
				&& FEB("xpath", "//*[@id=\"resultBody\"]/tr[1]/td[2]/input", "���� ����Ʈ�� ù��° ������ �̸��� �Է�ĭ")
				.getAttribute("value").equals("heejae2@humuson.com")
				&& FEB("xpath", "//*[@id=\"resultBody\"]/tr[1]/td[3]/input", "���� ����Ʈ�� ù��° ���� �̸��� �Է�ĭ")
				.getAttribute("value").equals("heejae2@humuson.com")) {
		} else {
			FAIL("���� ����");
		}
	}

	private void delete() {
		int cnt = FEB("id", "resultBody", "���� ����Ʈ").findElements(By.tagName("tr")).size();

		if (FEB("xpath", "//*[@id=\"resultBody\"]/tr[1]/td[1]/input", "���� ����Ʈ�� ù��° ������ ��� �̸� �Է�ĭ").getAttribute("value")
				.equals("������")) {
			FEB("xpath", "//*[@id=\"resultBody\"]/tr[1]/td[7]/button", "���� ����Ʈ�� ù��° ���� ��ư").click();
		}
		driver.navigate().refresh();
		if (cnt == FEB("id", "resultBody", "���� ����Ʈ").findElements(By.tagName("tr")).size()) {
			FAIL("���� ����");
		}
	}

	private void clear() {
		FEB("id", "userName", "������ ��� �̸� �Է�ĭ").clear();
		FEB("id", "userEmail", "������ �̸��� �Է�ĭ").clear();
		FEB("id", "return", "���� ���� �Է�ĭ").clear();
	}
}
