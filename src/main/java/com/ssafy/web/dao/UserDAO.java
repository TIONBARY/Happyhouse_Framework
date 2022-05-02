package com.ssafy.web.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.ssafy.web.dto.UserDTO;

@Mapper
@Repository
public interface UserDAO {

	public UserDTO searchById(String id) throws Exception;
	public int createUser(UserDTO userDto) throws Exception;
	public UserDTO login(String id, String password) throws Exception;
	public int updateUser(UserDTO newUserDto) throws Exception;
	public int deleteUser(String id) throws Exception;
	
	
}
