package com.kc.pingpang.platform.controller.admin.login.api;


import com.kc.pingpang.platform.freamwork.http.api.api.ServiceResponse;

public class GetLoginInfoResponse extends ServiceResponse {
	private static final long serialVersionUID = 1L;

	private LoginAccountVO account;

	public LoginAccountVO getAccount() {
		return account;
	}

	public void setAccount(LoginAccountVO account) {
		this.account = account;
	}	
}
