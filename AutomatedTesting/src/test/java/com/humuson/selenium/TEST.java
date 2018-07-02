package com.humuson.selenium;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.edge.*;
 
public class TEST {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();

 
    @Test
    public void testCreateUser() throws Exception {
    	System.setProperty("webdriver.chrome.driver", "C:/app/Selenium/ChromWin.exe");
    	
        driver = new ChromeDriver();
        baseUrl = "http://119.207.76.92/TMS";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl + "/login");
        
        WebElement loginID = driver.findElement(By.id("j_username"));
        loginID.sendKeys("tms");

        WebElement loginPassword = driver.findElement(By.id("j_password"));
        loginPassword.sendKeys("humuson123!");

        loginPassword.submit();
        
        driver.switchTo().alert().dismiss();
        
        driver.get(baseUrl + "/site/regist");
        
        driver.findElement(By.id("emailFlag")).click();
        driver.findElement(By.id("pushFlag")).click();
        driver.findElement(By.id("smsFlag")).click();
        
        driver.findElement(By.id("siteImageFile")).click();
        // C:/Users/YSW/Pictures/2748_1561_735.jpg
        Thread.sleep(10000);
        
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
        
    }
}