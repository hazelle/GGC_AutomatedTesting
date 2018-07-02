package com.humuson.selenium.MySite.ChannelMngmt;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� MySite
 * @�ߺз� ä�� ����
 * */
public class B_ChannelMngmt extends Scenario {
	public B_ChannelMngmt(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		addChildScenario(new E_ClickChnMngmt(E, category, "ä�� ���� ��ư Ŭ��"));
		if(ii.getChannel().get("email"))	addChildScenario(new E_EmailAccount(E, category, "E-mail �������"));
		if(ii.getChannel().get("sms"))		addChildScenario(new E_SMSAccount(E, category, "SMS �������"));
		if(ii.getChannel().get("push"))		addChildScenario(new E_PushAccount(E, category, "Push �������"));
		if(ii.getChannel().get("kakao"))	addChildScenario(new E_KakaoAccount(E, category, "Kakao �������"));
		addChildScenario(new E_FatigueSetting(E, category, "�Ƿε� ����"));
	}
	
	protected void DO() {
		execute();
	}
}
