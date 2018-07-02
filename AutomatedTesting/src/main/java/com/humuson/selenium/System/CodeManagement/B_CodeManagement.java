package com.humuson.selenium.System.CodeManagement;

import com.humuson.selenium.System.CodeManagement.MappingManagement.C_MappingManagement;
import com.humuson.selenium.System.CodeManagement.SCManagement.C_SCManagement;
import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System
 * @중분류 코드 관리
 */
public class B_CodeManagement extends Scenario {
	public B_CodeManagement(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		
		addChildScenario(new C_SCManagement(C, category, "시스템 코드 관리"));
		addChildScenario(new C_MappingManagement(C, category, "매핑 관리"));
	}
	
	protected void DO() {
		execute();
	}
}

