package com.ssafy.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.dao.UserDAO;
import com.ssafy.web.dto.UserDTO;

@Service
public class UserService {
	@Autowired
	UserDAO userDAO;
	
	public UserDTO searchById(String id) throws Exception {
		return userDAO.searchById(id);
	}
	
	public int createUser(UserDTO userDTO) throws Exception {
		String id = userDTO.getId();
		UserDTO searchedUser = searchById(id);
		if (searchedUser != null) {
			return 0;
		}else {
			return userDAO.createUser(userDTO);
		}
		
		
	}

	public UserDTO login(String id, String pwd) throws Exception {
		return userDAO.login(id, pwd);
	}

	public int updateUser(UserDTO userDTO) throws Exception {
		return userDAO.updateUser(userDTO);
	}

	public int deleteUser(String id) throws Exception {
		return userDAO.deleteUser(id);
	}
}
