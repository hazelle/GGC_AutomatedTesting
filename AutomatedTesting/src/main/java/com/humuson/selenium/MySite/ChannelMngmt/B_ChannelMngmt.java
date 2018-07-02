package com.humuson.selenium.MySite.ChannelMngmt;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 MySite
 * @중분류 채널 관리
 * */
public class B_ChannelMngmt extends Scenario {
	public B_ChannelMngmt(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		addChildScenario(new E_ClickChnMngmt(E, category, "채널 관리 버튼 클릭"));
		if(ii.getChannel().get("email"))	addChildScenario(new E_EmailAccount(E, category, "E-mail 계정등록"));
		if(ii.getChannel().get("sms"))		addChildScenario(new E_SMSAccount(E, category, "SMS 계정등록"));
		if(ii.getChannel().get("push"))		addChildScenario(new E_PushAccount(E, category, "Push 계정등록"));
		if(ii.getChannel().get("kakao"))	addChildScenario(new E_KakaoAccount(E, category, "Kakao 계정등록"));
		addChildScenario(new E_FatigueSetting(E, category, "피로도 설정"));
	}
	
	protected void DO() {
		execute();
	}
}
