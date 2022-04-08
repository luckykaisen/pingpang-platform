package com.kc.pingpang.platform.freamwork.http.api.api;

import java.io.Serializable;

public class ServiceResponse implements Serializable {
	private static final long serialVersionUID = 9025319384467047144L;

	public static final String CODE_SUCCESS = "100000";
	
	public static final String CODE_REQUIRE_LOGIN = "999997";
	public static final String CODE_NO_PERMISSION = "999998";	
	
	public static final String CODE_FAILED = "999999";
	
	public static ServiceResponse getSucceedResponse() {
		ServiceResponse response = new ServiceResponse();
		
		response.fillWithSucceedCode();
		
		return response;
	}
	
	public static ServiceResponse getFailedResponse() {
		ServiceResponse response = new ServiceResponse();
		
		response.fillWithFailedCode();
		
		return response;
	}
	
	private String resultCode;
	private String resultMessage;
	// 为每一个错误产生一个临时近似唯一的ID，这个ID会记录到日志里面，方便错误定位
	private String errorId;

	public ServiceResponse() {
		resultCode = CODE_SUCCESS;
		resultMessage = "操作成功";
	}
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getErrorId() {
		return errorId;
	}

	public void setErrorId(String errorId) {
		this.errorId = errorId;
	}

	public void fillWithSucceedCode() {
		setResultCode(CODE_SUCCESS);
		setResultMessage("操作成功");
	}
	
	public void fillWithNoPermissionCode() {
		setResultCode(CODE_NO_PERMISSION);
		setResultMessage("没有权限");
	}
	
	public void fillWithRequireLoginCode() {
		setResultCode(CODE_REQUIRE_LOGIN);
		setResultMessage("需要登录");
	}
	
	public void fillWithFailedCode() {
		setResultCode(CODE_FAILED);
		setResultMessage("操作失败");
	}
	
	@Override
	public String toString() {
		return "ServiceResponse [resultCode=" + resultCode + ", resultMessage=" + resultMessage + ", errorId=" + errorId
				+ "]";
	}
}
