package com.ssafy.web.dto;

import lombok.Data;

@Data
public class UserDTO {
	private int no;
	private String id;
	private String password;
	private String name;
	private String email;
	private String phone;
}
