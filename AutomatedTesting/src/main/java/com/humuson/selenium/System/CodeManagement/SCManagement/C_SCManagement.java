package com.humuson.selenium.System.CodeManagement.SCManagement;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 System 
 * @중분류 코드 관리 
 * @소분류 시스템 코드 관리 
 */
public class C_SCManagement extends Scenario {
	public C_SCManagement(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}

	protected void makeScenario() {
		addChildScenario(new E_ClickSCManagement(E, category, "시스템코드 관리 메뉴 클릭"));
		addChildScenario(new E_FillOutAndRegister(E, category, "시스템 코드 정보 입력 폼에 데이터 입력 후 등록 버튼 클릭"));
		addChildScenario(new E_CodeTypeNameSearch(E, category, "코드타입, 코드명 입력 후 검색 버튼 클릭"));
		addChildScenario(new E_ClickCodeAndModify(E, category, "코드명 클릭, 코드 수정"));
		addChildScenario(new E_DeleteCode(E, category, "시스템 코드 선택 후 삭제 버튼 클릭"));
		addChildScenario(new E_ResetForm(E, category, "신규 버튼 클릭"));
	}

	protected void DO() {
		execute();
	}
}
