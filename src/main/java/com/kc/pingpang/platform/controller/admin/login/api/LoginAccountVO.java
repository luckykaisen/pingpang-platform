package com.kc.pingpang.platform.controller.admin.login.api;


import com.kc.pingpang.platform.data.model.AdminAccount;

public class LoginAccountVO {

	private Integer id;
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static LoginAccountVO toAccountVO(AdminAccount account) {
		
		LoginAccountVO vo = new LoginAccountVO();
		
		vo.setId(account.getId());
		vo.setName(account.getLoginName());

		return vo;
	}
}
