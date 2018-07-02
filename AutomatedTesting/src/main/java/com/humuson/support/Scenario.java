package com.humuson.support;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.humuson.selenium.StartTesting;

public class Scenario extends CustomLogging{
	public static int A = 0;
	public static int B = 1;
	public static int C = 2;
	public static int E = 9;
	
	protected static WebDriver driver = StartTesting.driver;
	protected static WebDriverWait wait = StartTesting.wait;
	protected static JavascriptExecutor js = (JavascriptExecutor) driver;
	
	protected static PropRead pr = StartTesting.pr;
	protected static PropRead bs = StartTesting.bs;
	protected static InputInfo ii = StartTesting.ii;
	protected static ControlPage cp = StartTesting.cp;
	
	protected String[] category = {"", "", ""};
	protected int TYPE = A;
	protected String title = "";
	protected ArrayList<Scenario> childScenario = new ArrayList<Scenario>();
	protected int RESULT = 0;
	
	public static ArrayList<String[]> xlsxContent = new ArrayList<String[]>();
	protected String failDetail = "";
	protected int period = 5;
	protected int flag=0;

	/** action()�� �����Ű�� �ó������� ��� ���� ���� �����ϵ��� ����Ǿ����� */
	public void action() {
		CustomWait(3);
		GO();
		makeScenario();
		DO();
		END();
	}

	/** �ó������� main �κ��� ������ */
	protected void DO() {	}

	protected void execute() {
		for (int i = 0; i < getChildScenario().size(); i++) {
			System.out.println("# �ó����� �� : " + getChildScenario().get(i).getTitle());
			getChildScenario().get(i).action();
		}
	}
	
	protected void CHECK(boolean check) {
		if(check)
			OK();
		else
			RESULT += 1;
	}

	/** @return �ش� �ó������� ���� �ó������� ArrayList�� ������ */
	public ArrayList<Scenario> getChildScenario() {
		return childScenario;
	}

	/** ���� �ó������� �����ϴ� �޼ҵ� */
	protected void makeScenario() {	}

	/**
	 * ���� �ó������� childScenario�� �߰�
	 * 
	 * @param sc
	 *            ���� �ó�����
	 */
	public void addChildScenario(Scenario sc) {
		childScenario.add(sc);
	}

	/** �α� �� �ó������� ������ �˸� */
	public void GO() {
		simpleLog("");
		simpleLog(">>> " + getTitle() + " [����]");
	}

	/** �α� �� �ó������� ���� �˸� */
	public void END() {
		if(RESULT==0) {
			simpleLog("<<< " + getTitle() + " [��] ... O");
			if(TYPE==E)	logExcel(category[0], category[1], category[2], title, "O", failDetail);
		} else {
			simpleLog("<<< " + getTitle() + " [��] ... X");
			if(TYPE==E)	logExcel(category[0], category[1], category[2], title, "X", failDetail);
		}
	}

	/** ���� Ÿ��Ʋ�� ������ �α׿� Oǥ�ÿ� �Բ� ���� */
	public void OK() {
		simpleLog("O  " + getTitle());
	}

	/** ���� Ÿ��Ʋ�� ���и� �α׿� Xǥ�ÿ� �Բ� ���� */
	public void FAIL() {
		RESULT += 1;
		simpleLog("X  " + getTitle());
	}

	/**
	 * ������ ���е� �ƴ� ��Ÿ ������ �α׸� ����/ 'type msg' <-���·� �α׿� ��ϵ�
	 * 
	 * @param type
	 *            �α��� Ÿ���� ���� �Է�. O�� X, �ٸ� �͵� ����
	 * @param msg
	 *            �α��� ����
	 */
	public void ETC(String type, String msg) {
		simpleLog(type + "  " + msg);
	}

	/**
	 * DetaildLog�� true�� ���� �����. Oǥ�ÿ� �Բ� �α׿� ����
	 * 
	 * @param detail
	 *            ���� ���� (���� Class�� title �ڿ� ���� ����)
	 */
	public void OK(String detail) {
		if (StartTesting.DetailedLog)
			simpleLog("O  " + getTitle() + " | " + detail);
	}

	/**
	 * Xǥ�ÿ� �Բ� �α׿� ����
	 * 
	 * @param detail
	 *            �α׿� ���������� ������ ����
	 */
	public void FAIL(String detail) {
		// if(StartTesting.DetailedLog)
		RESULT += 1;
		simpleLog("X  " + getTitle() + " | " + detail + " ����");
		if(failDetail.length()>0) {
			failDetail += "\n"+detail;
		} else {
			failDetail += detail;
		}
	}

	/**
	 * ���� Url�� �α׿� ���. Xǥ�ÿ� �Բ� �α׿� ����
	 * 
	 * @param detail
	 *            ���� ���� (���� Class�� title �ڿ� ���� ����)
	 * @param currentUrl
	 *            ���� �������� Url. driver.getCurrentUrl()�� ���� ��
	 */
	public void FAIL(String detail, String currentUrl) {
		// if(StartTesting.DetailedLog)
		RESULT += 1;
		simpleLog("X  " + getTitle() + " | " + detail + " ����. ���� ������ " + currentUrl);
	}

	/**
	 * ���� �߻��� Exception�� ������ �α׿� ���. Xǥ�ÿ� �Բ� �α׿� ����
	 * 
	 * @param detail
	 *            ���� ���� (���� Class�� title �ڿ� ���� ����)
	 * @param e
	 *            ���� �߻��� Exception
	 */
	public void FAIL(String detail, Exception e) {
		RESULT += 1;
		simpleLog("X  " + getTitle() + " | " + detail + " ����");
		simpleLog(e.getMessage());
	}

	/**
	 * DetaildLog�� true�� ���� �����.
	 * 
	 * @param type
	 * @param msg
	 * @param detailedLog
	 */
	public void ETC(String type, String msg, boolean detailedLog) {
		if (detailedLog)
			simpleLog(type + "  " + msg);
	}

	/**
	 * driver.findElement(by)�� try/catch���� �� �޼ҵ忡 �ۼ���
	 * 
	 * @param type
	 *            WebElement �˻��� �� ���� ����. id, xpath, css, name�� ����. default : id
	 * @param value
	 *            WebElement �˻��� �� ��
	 * @param detail
	 *            �˻��� WebElement�� �θ� ��Ī. Exception�߻� �� �α׿� ����
	 * @return �˻��� WebElement. �˻��� ���� �� null�� ����
	 */
	public WebElement FEB(String type, String value, String detail) {
		CustomWait(4);
		WebElement w;
		try {
			switch (type) {
			case "id":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
				w = driver.findElement(By.id(value));
				break;
			case "xpath":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(value)));
				w = driver.findElement(By.xpath(value));
				break;
			case "class":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.className(value)));
				w = driver.findElement(By.className(value));
				break;
			case "css":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(value)));
				w = driver.findElement(By.cssSelector(value));
				break;
			case "name":
				wait.until(ExpectedConditions.presenceOfElementLocated(By.name(value)));
				w = driver.findElement(By.name(value));
				break;
			default:
				wait.until(ExpectedConditions.presenceOfElementLocated(By.id(value)));
				w = driver.findElement(By.id(value));
			}
			OK(detail +" Ž��");
			return w;
		} catch (StaleElementReferenceException e) {
			System.out.println("Stale ����!!!!");
			return FEB(type, value, detail);
		} catch (WebDriverException e) {
			System.out.println("�׳� WebDriverException ����!!!!");
			FAIL(detail + " WebElement ã�� ����", e);
			return null;
		}
	}

	/**
	 * ���� �������� ��ü�� ĸ���� jpg���Ϸ� ����
	 */
	public void saveScreenShot(String[] category, String detail, String extension) {
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			String filename = "";
			for(String c : category) {
				if (!c.equals(""))	filename += c+"_";
			}
			filename += " "+detail;
			FileUtils.copyFile(scrFile, new File(filename + extension));
		} catch (IOException e) {
			FAIL(detail + " ��ũ����", e);
		}
	}
	
	public void setAttribute(String type, String value, String attr, String value2) {
		switch (type) {
		case "id":
			js.executeScript("document.getElementById('"+value+"').setAttribute('"+attr+"', '"+value2+"')");
			break;
//		case "xpath":
//			js.executeScript("document.getElementById('"+value+"').setAttribute('"+attr+"', '"+value2+"')");
//			break;
//		case "css":
//			js.executeScript("document.getElementById('"+value+"').setAttribute('"+attr+"', '"+value2+"')");
//			break;
//		case "name":
//			js.executeScript("document.getElementById('"+value+"').setAttribute('"+attr+"', '"+value2+"')");
//			break;
//		default:
//			
		}
//		js.executeScript("document.getElementById('//id of element').setAttribute('attr', '10')");
	}

	/** @return ���� Class�� title */
	public String getTitle() {
		return title;
	}
	
	public String[] getCategory() {
		return category;
	}
	
	public void setCategory(String[] category) {
		this.category = category;
	}

	public void CustomWait() {
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(pr.getPropValue("wait.seconds")), TimeUnit.SECONDS);
	}
	
	public void CustomWait(int plus) {
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(pr.getPropValue("wait.seconds"))+plus, TimeUnit.SECONDS);
	}
	
	public void logExcel(String cate0, String cate1, String cate2, String scenarioname, String result, String failDetail) {
		CustomWait(3);
		String[] val = {cate0, cate1, cate2, scenarioname, result, failDetail};
		xlsxContent.add(val);
		if(xlsxContent.size()>=period) {
//		if(title.equals("�α���")) {
			StartTesting.x.modify(xlsxContent);
			xlsxContent.clear();
		} else {	}
	}
	
}

