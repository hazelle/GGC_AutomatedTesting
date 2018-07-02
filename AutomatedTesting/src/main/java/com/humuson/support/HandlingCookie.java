package com.humuson.support;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class HandlingCookie extends CustomLogging {
	
	public HandlingCookie() {	}

	public static void showCookies(WebDriver driver) {
		//To return all the cookies of the current domain
		Set<Cookie> cookiesForCurrentURL = driver.manage().getCookies();
		for (Cookie cookie : cookiesForCurrentURL) {
		    logger.trace("[Cookie] name: "+cookie.getName()+"\n"
		    				+"                    value: "+cookie.getValue());
		}
	}
		
}
