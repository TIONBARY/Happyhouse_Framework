package com.ssafy.web.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.web.dto.BoardDTO;
import com.ssafy.web.dto.HouseDealDTO;
import com.ssafy.web.service.HouseDealService;

@RestController
@RequestMapping("search")
public class HouseController {

	@Autowired
	HouseDealService houseDealService;
	
	@GetMapping("")
	public ModelAndView mvSearchPage() throws Exception {
		return new ModelAndView("search");
	}
	
	@PostMapping("searchByDong")
	public String search(@RequestParam String code, @RequestParam String period, @RequestParam String dong) throws Exception{
		JSONObject json = new JSONObject();

		
		ArrayList<HouseDealDTO> houseDealList = new ArrayList<>();
		try {
			ArrayList<HouseDealDTO> list = houseDealService.getHouseDealList(code, period);
			for(HouseDealDTO h: list) {
				if(dong.equals(h.getDong())) {
					houseDealList.add(h);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			json.put("msg", e.getMessage());
		}
		json.put("houseDealList", houseDealList);
		return json.toString();
	}
}
