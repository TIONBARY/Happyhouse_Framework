package com.ssafy.web.dto;

import lombok.Data;

@Data
public class BoardDTO {
	private int no;
	private String userid;
	private String subject;
	private String content;
	private int views;
	private String regtime;
}
