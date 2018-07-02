package com.humuson.selenium.MySite;

import com.humuson.selenium.MySite.ChannelMngmt.B_ChannelMngmt;
import com.humuson.selenium.MySite.SiteMngmt.B_SiteMngmt;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * @��з� MySite
 * */
public class A_MySite extends Scenario {
	public A_MySite(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		addChildScenario(new B_SiteMngmt(B, category, "����Ʈ ����"));
		addChildScenario(new B_ChannelMngmt(B, category, "ä�� ����"));
	}
	
	protected void DO() {
		execute();
	}
}
