package com.kc.pingpang.platform.controller.admin.login.api;

import org.apache.commons.lang.StringUtils;

public class LoginRequest {

	private String loginName;
	private String password;
	
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean validate() {
		return StringUtils.isNotBlank(loginName) && StringUtils.isNotBlank(password);
	}
}
