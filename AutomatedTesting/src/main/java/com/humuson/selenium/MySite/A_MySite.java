package com.humuson.selenium.MySite;

import com.humuson.selenium.MySite.ChannelMngmt.B_ChannelMngmt;
import com.humuson.selenium.MySite.SiteMngmt.B_SiteMngmt;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 MySite
 * */
public class A_MySite extends Scenario {
	public A_MySite(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		addChildScenario(new B_SiteMngmt(B, category, "사이트 관리"));
		addChildScenario(new B_ChannelMngmt(B, category, "채널 관리"));
	}
	
	protected void DO() {
		execute();
	}
}
