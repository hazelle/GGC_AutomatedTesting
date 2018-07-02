package com.humuson.selenium;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.humuson.selenium.Login.A_Login;
import com.humuson.selenium.MySite.A_MySite;
import com.humuson.selenium.System.A_System;
import com.humuson.support.ControlPage;
import com.humuson.support.InputInfo;
import com.humuson.support.CustomLogging;
import com.humuson.support.Excel;
import com.humuson.support.PropRead;
import com.humuson.support.Scenario;
//import com.humuson.support.Window;

/**
 * @version TMS_QC_v2.0
 * @author heejae
 * */
public class StartTesting extends CustomLogging {

	public static WebDriver driver;
	public static InputInfo ii;
	public static PropRead pr;
	public static PropRead bs;
	public static WebDriverWait wait;
	public static ControlPage cp;
	public static CustomLogging cl;
	
	public static Excel x;
	
	public static boolean DetailedLog = true;
	static String[] category = {"", "", ""};
	
	private static String propfile = "\\..\\..\\..\\resources.properties";
	private static String basicfile = "\\..\\..\\..\\basic.properties";
	
	public static void main(String[] args) throws IOException {
		setting();
//		new Window();
		
		cl.headerLog();
		
		start();
	}
	
	public static void start() {
		forTest();	/* �׽�Ʈ�� ���� �κ��̹Ƿ� ���� �Ϸ� �Ŀ� ������ �� */
		
		x = new Excel();
		
		new A_Login(Scenario.A, category, "�α���").action();		
		System.out.println("A_Login ����");
		new A_System(Scenario.A, category, "System").action();
		System.out.println("A_System ����"); 
		new A_MySite(Scenario.A, category, "MySite");
		System.out.println("A_MySite ����");
		
		lastLogExcel();
		System.out.println("\n��!");
	}
	
	public static void setting() throws IOException {
		pr = new PropRead(propfile);
		bs = new PropRead(basicfile);
		ii = new InputInfo();
		driver = ii.getDriver();
		
		cp = new ControlPage();
		cl = new CustomLogging();
		
		wait = new WebDriverWait(driver, 20);
	}

	private static void lastLogExcel() {
		if(Scenario.xlsxContent.size()>0) {
			x.modify(Scenario.xlsxContent);
		}
	}

	/**
	 * �׽�Ʈ�� ���� �κ��̹Ƿ� ���� �Ϸ� �Ŀ� ������ ��
	 * */
	private static void forTest() {
		pr.setPropValue("site.name", "TEST");
		pr.setPropValue("site.name2", "TES2");
	}
}








