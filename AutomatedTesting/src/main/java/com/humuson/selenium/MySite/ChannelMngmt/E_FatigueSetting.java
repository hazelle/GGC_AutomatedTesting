package com.humuson.selenium.MySite.ChannelMngmt;

import java.util.regex.Pattern;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 MySite
 * @중분류 채널 관리
 * @시나리오명 피로도 설정
 * */
public class E_FatigueSetting extends Scenario {
	//public String[] chns = {"email", "push", "sms", "kakao"};

	public E_FatigueSetting(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void DO() {
		if (Pattern.compile("/site/account").matcher(driver.getCurrentUrl()).find()) {
			click();

			if(ii.getChannel().get("email"))	fatigueSetting(0);
			if(ii.getChannel().get("sms"))		fatigueSetting(2);
			if(ii.getChannel().get("push"))		fatigueSetting(1);
			if(ii.getChannel().get("kakao"))	fatigueSetting(3);
			
			FEB("xpath", "//*[@id=\"regist\"]/button", "등록 버튼").click();
			cp.acceptAlert();
			CustomWait(3);
			cp.acceptAlert();
			
		} else {
			CHECK(false);
		}
	}
	
	private void click() {
		FEB("id", "regFatigue", "피로도 설정 탭").click();
	}

	private void fatigueSetting(int index) {
		String chn = ii.chns[index];
		FEB("id", chn+"DayCnt", chn+" 일 최대 횟수 입력칸").clear();
		FEB("id", chn+"DayCnt", chn+" 일 최대 횟수 입력칸").sendKeys(pr.getPropValue("site.fatigue."+chn+".day"));
		FEB("id", chn+"MonthCnt", chn+" 월 최대 횟수 입력칸").clear();
		FEB("id", chn+"MonthCnt", chn+" 월 최대 횟수 입력칸").sendKeys(pr.getPropValue("site.fatigue."+chn+".month"));
	}
}


