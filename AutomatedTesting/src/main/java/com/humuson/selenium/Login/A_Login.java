package com.humuson.selenium.Login;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * @대분류 로그인
 * */
public class A_Login extends Scenario {
	
	public A_Login(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
	
	protected void makeScenario() {
		addChildScenario(new E_InputIDPWAndLogin(E, category, "아이디, 패스워드 입력하여 로그인 성공"));
		addChildScenario(new E_CheckRememberMe(E, category, "remember me 체크"));
		addChildScenario(new E_Logout(E, category, "로그아웃 버튼을 눌러 로그아웃 성공"));
		addChildScenario(new E_NonexistentUser(E, category, "존재하지 않는 사용자일 경우"));
		addChildScenario(new E_WrongPassword(E, category, "비밀번호 틀린 경우"));
//		addChildScenario(new E_LockedUser("잠금처리된 계정일 경우"));
	}
	
	protected void DO() {
		if(cp.movePage("/login")) {
			execute();
		}
	}
}

