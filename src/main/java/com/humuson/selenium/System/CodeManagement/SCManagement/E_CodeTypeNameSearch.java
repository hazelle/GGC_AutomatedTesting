package com.humuson.selenium.System.CodeManagement.SCManagement;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� System
 * @�ߺз� �ڵ� ����
 * @�Һз� �ý��� �ڵ� ����
 * @�ó������� �ڵ�Ÿ��, �ڵ�� �Է� �� �˻� ��ư Ŭ��
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
		ETC("#","�̹��� ���� Ȯ�� �ʿ�  #");
	}

	private void search() {
		search_codeType(bs.getPropValue("systemcode.code_type"));
		FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[3]/div/span/button", "�˻� ��ư").click();
		saveScreenShot(category, "�ڵ�Ÿ������ �˻�", ".jpg");

		search_codeName(bs.getPropValue("systemcode.code_name2"));
		FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[3]/div/span/button", "�˻� ��ư").click();
		saveScreenShot(category, "�ڵ������ �˻�", ".jpg");

		search_codeType(bs.getPropValue("systemcode.code_type"));
		search_codeName(bs.getPropValue("systemcode.code_name3"));
		FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[3]/div/span/button", "�˻� ��ư").click();
		saveScreenShot(category, "�ڵ�Ÿ��,�ڵ������ �˻�", ".jpg");
	}

	public void search_codeType(String codetype) {
		CustomWait(3);
		Select dropbox = new Select(driver.findElement(By.xpath("//*[@id=\"SearchForm\"]/div/div[1]/div/select")));
		dropbox.selectByValue(codetype);
	}

	private void search_codeName(String codename) {
		FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[2]/div/input", "�ڵ�� �Է�ĭ").clear();
		FEB("xpath", "//*[@id=\"SearchForm\"]/div/div[2]/div/input", "�ڵ�� �Է�ĭ").sendKeys(codename);
	}

	private void setting() {
		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[1]", "�ű� ��ư").click();
		FEB("xpath", "//*[@id=\"code_type\"]", "�з��ڵ� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.code_type2"));
		FEB("xpath", "//*[@id=\"code_code\"]", "���ڵ� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.code_code2"));
		FEB("xpath", "//*[@id=\"code_name\"]", "�ڵ�� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.code_name2"));
		FEB("xpath", "//*[@id=\"code1\"]", "Ÿ�����̺� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.target_table2"));
		FEB("xpath", "//*[@id=\"code2\"]", "�����÷� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.ref_column2"));
		FEB("xpath", "//*[@id=\"code3\"]", "���۰� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.start_value2"));
		FEB("xpath", "//*[@id=\"code4\"]", "���ᰪ �Է�ĭ").sendKeys(bs.getPropValue("systemcode.end_value2"));
		FEB("xpath", "//*[@id=\"code5\"]", "��� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.etc2"));
		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[2]", "��� ��ư").click();
		CustomWait(5);
		cp.acceptAlert(0);

		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[1]", "�ű� ��ư").click();
		FEB("xpath", "//*[@id=\"code_type\"]", "�з��ڵ� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.code_type3"));
		FEB("xpath", "//*[@id=\"code_code\"]", "���ڵ� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.code_code3"));
		FEB("xpath", "//*[@id=\"code_name\"]", "�ڵ�� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.code_name3"));
		FEB("xpath", "//*[@id=\"code1\"]", "Ÿ�����̺� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.target_table3"));
		FEB("xpath", "//*[@id=\"code2\"]", "�����÷� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.ref_column3"));
		FEB("xpath", "//*[@id=\"code3\"]", "���۰� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.start_value3"));
		FEB("xpath", "//*[@id=\"code4\"]", "���ᰪ �Է�ĭ").sendKeys(bs.getPropValue("systemcode.end_value3"));
		FEB("xpath", "//*[@id=\"code5\"]", "��� �Է�ĭ").sendKeys(bs.getPropValue("systemcode.etc3"));
		FEB("xpath", "//*[@id=\"sysCodeEditForm\"]/div[6]/div/button[2]", "��� ��ư").click();
		CustomWait(50);
		cp.acceptAlert();
	}
}
