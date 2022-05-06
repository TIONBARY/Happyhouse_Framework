package com.ssafy.web.dto;

import lombok.Data;

@Data
public class LoginHistoryDTO {
	private String ip;
	private int retryCount;
}
