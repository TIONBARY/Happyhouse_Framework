package com.ssafy.web.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ssafy.web.dto.BoardDTO;

@Mapper
@Repository
public interface BoardDAO {

	public BoardDTO getPost(int no);
	public List<BoardDTO> getAllPost();
	public void createPost(BoardDTO boardDTO);
	public void updatePost(BoardDTO boardDTO);
	public void deletePost(int no);

}
