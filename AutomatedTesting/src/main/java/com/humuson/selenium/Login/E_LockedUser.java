package com.humuson.selenium.Login;

import com.humuson.support.Scenario;

/**
 * ADMIN
 * 대분류 : 로그인
 * 중분류 : 로그인
 * 시나리오 명 : 잠금처리된 계정일 경우
 * */
public class E_LockedUser extends Scenario {
	public E_LockedUser(int type, String[] category, String title) {
		this.title = title;
		this.TYPE = type;
		setCategory(category);
		if(type!=E) category[type] = title;
	}
}

