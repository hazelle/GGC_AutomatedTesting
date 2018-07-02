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

	/** action()만 실행시키면 시나리오의 모든 내용 진행 가능하도록 설계되어있음 */
	public void action() {
		CustomWait(3);
		GO();
		makeScenario();
		DO();
		END();
	}

	/** 시나리오의 main 부분을 실행함 */
	protected void DO() {	}

	protected void execute() {
		for (int i = 0; i < getChildScenario().size(); i++) {
			System.out.println("# 시나리오 명 : " + getChildScenario().get(i).getTitle());
			getChildScenario().get(i).action();
		}
	}
	
	protected void CHECK(boolean check) {
		if(check)
			OK();
		else
			RESULT += 1;
	}

	/** @return 해당 시나리오의 하위 시나리오를 ArrayList로 리턴함 */
	public ArrayList<Scenario> getChildScenario() {
		return childScenario;
	}

	/** 하위 시나리오를 생성하는 메소드 */
	protected void makeScenario() {	}

	/**
	 * 하위 시나리오를 childScenario에 추가
	 * 
	 * @param sc
	 *            하위 시나리오
	 */
	public void addChildScenario(Scenario sc) {
		childScenario.add(sc);
	}

	/** 로그 상에 시나리오의 시작을 알림 */
	public void GO() {
		simpleLog("");
		simpleLog(">>> " + getTitle() + " [시작]");
	}

	/** 로그 상에 시나리오의 끝을 알림 */
	public void END() {
		if(RESULT==0) {
			simpleLog("<<< " + getTitle() + " [끝] ... O");
			if(TYPE==E)	logExcel(category[0], category[1], category[2], title, "O", failDetail);
		} else {
			simpleLog("<<< " + getTitle() + " [끝] ... X");
			if(TYPE==E)	logExcel(category[0], category[1], category[2], title, "X", failDetail);
		}
	}

	/** 현재 타이틀의 성공을 로그에 O표시와 함께 남김 */
	public void OK() {
		simpleLog("O  " + getTitle());
	}

	/** 현재 타이틀의 실패를 로그에 X표시와 함께 남김 */
	public void FAIL() {
		RESULT += 1;
		simpleLog("X  " + getTitle());
	}

	/**
	 * 성공도 실패도 아닌 기타 내용의 로그를 남김/ 'type msg' <-형태로 로그에 기록됨
	 * 
	 * @param type
	 *            로그의 타입을 직접 입력. O나 X, 다른 것도 가능
	 * @param msg
	 *            로그의 내용
	 */
	public void ETC(String type, String msg) {
		simpleLog(type + "  " + msg);
	}

	/**
	 * DetaildLog가 true일 때만 실행됨. O표시와 함께 로그에 찍힘
	 * 
	 * @param detail
	 *            세부 내용 (현재 Class의 title 뒤에 찍힐 내용)
	 */
	public void OK(String detail) {
		if (StartTesting.DetailedLog)
			simpleLog("O  " + getTitle() + " | " + detail);
	}

	/**
	 * X표시와 함께 로그에 찍힘
	 * 
	 * @param detail
	 *            로그에 직접적으로 찍히는 내용
	 */
	public void FAIL(String detail) {
		// if(StartTesting.DetailedLog)
		RESULT += 1;
		simpleLog("X  " + getTitle() + " | " + detail + " 실패");
		if(failDetail.length()>0) {
			failDetail += "\n"+detail;
		} else {
			failDetail += detail;
		}
	}

	/**
	 * 현재 Url을 로그에 출력. X표시와 함께 로그에 찍힘
	 * 
	 * @param detail
	 *            세부 내용 (현재 Class의 title 뒤에 찍힐 내용)
	 * @param currentUrl
	 *            현재 페이지의 Url. driver.getCurrentUrl()로 쓰면 됨
	 */
	public void FAIL(String detail, String currentUrl) {
		// if(StartTesting.DetailedLog)
		RESULT += 1;
		simpleLog("X  " + getTitle() + " | " + detail + " 실패. 현재 페이지 " + currentUrl);
	}

	/**
	 * 현재 발생한 Exception의 내용을 로그에 출력. X표시와 함께 로그에 찍힘
	 * 
	 * @param detail
	 *            세부 내용 (현재 Class의 title 뒤에 찍힐 내용)
	 * @param e
	 *            현재 발생한 Exception
	 */
	public void FAIL(String detail, Exception e) {
		RESULT += 1;
		simpleLog("X  " + getTitle() + " | " + detail + " 실패");
		simpleLog(e.getMessage());
	}

	/**
	 * DetaildLog가 true일 때만 실행됨.
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
	 * driver.findElement(by)와 try/catch문을 한 메소드에 작성함
	 * 
	 * @param type
	 *            WebElement 검색에 쓸 값의 종류. id, xpath, css, name이 있음. default : id
	 * @param value
	 *            WebElement 검색에 쓸 값
	 * @param detail
	 *            검색한 WebElement를 부를 명칭. Exception발생 시 로그에 찍힘
	 * @return 검색된 WebElement. 검색에 실패 시 null값 리턴
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
			OK(detail +" 탐색");
			return w;
		} catch (StaleElementReferenceException e) {
			System.out.println("Stale 났음!!!!");
			return FEB(type, value, detail);
		} catch (WebDriverException e) {
			System.out.println("그냥 WebDriverException 났음!!!!");
			FAIL(detail + " WebElement 찾기 실패", e);
			return null;
		}
	}

	/**
	 * 현재 웹페이지 전체를 캡쳐해 jpg파일로 저장
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
			FAIL(detail + " 스크린샷", e);
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

	/** @return 현재 Class의 title */
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
//		if(title.equals("로그인")) {
			StartTesting.x.modify(xlsxContent);
			xlsxContent.clear();
		} else {	}
	}
	
}

