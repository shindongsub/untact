package com.sbs.untact.dto;

import java.util.Map;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultData {
	private String resultCode;
	private String msg;
	private Map<String, Object> body;
	
	public ResultData(String resultCode, String msg, Object... args) {
		this.resultCode = resultCode;
		this.msg = msg;
		this.body = Util.mapOf(args);
	}
	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}
	public boolean isfail() {
		return isSuccess() == false;
	}
}
