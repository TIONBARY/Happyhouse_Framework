package com.ssafy.web.dto;

import lombok.Data;

@Data
public class HouseDealDTO {
	private int aptCode;
	private String dong;
	private String aptName;
	private String dealAmount;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String area;
	private String areaNumber;
	private int builtYear;
	private int floor;
	private String type;
	private String rentMoney;
}
