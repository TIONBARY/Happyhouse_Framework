package com.ssafy.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.web.dao.BoardDAO;
import com.ssafy.web.dto.BoardDTO;

@Service
public class BoardService {
	
	@Autowired
	BoardDAO boardDAO;
	
	public BoardDTO getPost(int no) throws Exception {
		return boardDAO.getPost(no);
	}
	
	public List<BoardDTO> getAllPost() throws Exception {
		return boardDAO.getAllPost();
	}
	
	public void createPost(BoardDTO BoardDTO) throws Exception {
		boardDAO.createPost(BoardDTO);
	}
	
	public void updatePost(BoardDTO BoardDTO) throws Exception {
		boardDAO.updatePost(BoardDTO);
	}
	
	public void deletePost(int no) throws Exception {
		boardDAO.deletePost(no);
	}
	
	public void makePageNavagation() {
		
	}
}
