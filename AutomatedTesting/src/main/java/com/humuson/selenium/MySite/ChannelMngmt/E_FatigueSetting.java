package com.humuson.selenium.MySite.ChannelMngmt;

import java.util.regex.Pattern;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� MySite
 * @�ߺз� ä�� ����
 * @�ó������� �Ƿε� ����
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
			
			FEB("xpath", "//*[@id=\"regist\"]/button", "��� ��ư").click();
			cp.acceptAlert();
			CustomWait(3);
			cp.acceptAlert();
			
		} else {
			CHECK(false);
		}
	}
	
	private void click() {
		FEB("id", "regFatigue", "�Ƿε� ���� ��").click();
	}

	private void fatigueSetting(int index) {
		String chn = ii.chns[index];
		FEB("id", chn+"DayCnt", chn+" �� �ִ� Ƚ�� �Է�ĭ").clear();
		FEB("id", chn+"DayCnt", chn+" �� �ִ� Ƚ�� �Է�ĭ").sendKeys(pr.getPropValue("site.fatigue."+chn+".day"));
		FEB("id", chn+"MonthCnt", chn+" �� �ִ� Ƚ�� �Է�ĭ").clear();
		FEB("id", chn+"MonthCnt", chn+" �� �ִ� Ƚ�� �Է�ĭ").sendKeys(pr.getPropValue("site.fatigue."+chn+".month"));
	}
}


