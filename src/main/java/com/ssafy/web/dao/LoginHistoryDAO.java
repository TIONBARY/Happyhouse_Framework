package com.ssafy.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssafy.web.dto.BoardDTO;
import com.ssafy.web.dto.LoginHistoryDTO;

@Mapper
@Repository
public interface LoginHistoryDAO {

	public LoginHistoryDTO getHistory(String ip);
	public void insertHistory(LoginHistoryDTO loginHistoryDTO);
	public void updateHistory(LoginHistoryDTO loginHistoryDTO);

}
