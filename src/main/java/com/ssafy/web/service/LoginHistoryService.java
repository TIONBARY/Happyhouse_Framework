package com.ssafy.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.dao.LoginHistoryDAO;
import com.ssafy.web.dto.LoginHistoryDTO;

@Service
public class LoginHistoryService {
	@Autowired LoginHistoryDAO loginHistoryDAO;
	
	public LoginHistoryDTO getHistory(String ip) throws Exception {
		LoginHistoryDTO loginHistoryDTO = loginHistoryDAO.getHistory(ip);
		if (loginHistoryDTO == null) {
			loginHistoryDTO = new LoginHistoryDTO();
			loginHistoryDTO.setIp(ip);
			loginHistoryDTO.setRetryCount(0);
			insertHistory(loginHistoryDTO);
		}
		return loginHistoryDTO;
	}
	public void insertHistory(LoginHistoryDTO loginHistoryDTO) throws Exception {
		loginHistoryDAO.insertHistory(loginHistoryDTO);
	}
	public void updateHistory(LoginHistoryDTO loginHistoryDTO) throws Exception {
		loginHistoryDAO.updateHistory(loginHistoryDTO);
	}
	
	public void cleanHistory(LoginHistoryDTO loginHistoryDTO) throws Exception {
		loginHistoryDTO.setRetryCount(0);
		updateHistory(loginHistoryDTO);
	}
}
