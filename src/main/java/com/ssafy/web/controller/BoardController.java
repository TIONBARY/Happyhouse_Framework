package com.ssafy.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.web.dto.BoardDTO;
import com.ssafy.web.dto.UserDTO;
import com.ssafy.web.service.BoardService;

@Controller
@RequestMapping("board")
public class BoardController {

	@Autowired
	BoardService boardService;
	
	@GetMapping("getAllPost")
	public ModelAndView selectAll() throws Exception{
		List<BoardDTO> list = boardService.getAllPost();
		ModelAndView mav = new ModelAndView("board/postList");
		mav.addObject("postList", list);
		return mav;
	}
	
	@PostMapping("createPost")
	public ModelAndView createPost(@RequestParam String subject, @RequestParam String content, HttpSession session) throws Exception{
		UserDTO userDTO = (UserDTO)session.getAttribute("currentUser");
		if (userDTO == null) {
			return new ModelAndView("auth/login");
		}
		
		String userid = userDTO.getId();
		BoardDTO boardDTO = new BoardDTO();
		
		boardDTO.setUserid(userid);
		boardDTO.setSubject(subject);
		boardDTO.setContent(content);
		boardService.createPost(boardDTO);
		
		return selectAll();
	}
	
	@GetMapping("getPost/{no}")
	public ModelAndView getPost(@PathVariable("no") int no) throws Exception{
		ModelAndView mav = new ModelAndView("board/post");
		BoardDTO boardDTO = boardService.getPost(no);
		boardDTO.setViews(boardDTO.getViews() + 1);
		boardService.updatePost(boardDTO);
		mav.addObject("postDto", boardDTO);

		return mav;
	}
	
	@GetMapping("addPost")
	public ModelAndView addPost() throws Exception{
		return new ModelAndView("board/addPost");
	}
	
	@PostMapping("updatePost")
	public ModelAndView getPost(@RequestParam int no, @RequestParam String subject, @RequestParam String content) throws Exception{
		BoardDTO boardDTO = boardService.getPost(no);
		boardDTO.setSubject(subject);
		boardDTO.setContent(content);
		boardService.updatePost(boardDTO);
		
		return selectAll();
	}
	
	@GetMapping("deletePost")
	public ModelAndView deletePost(@RequestParam int no) throws Exception{
		boardService.deletePost(no);
		return selectAll();
	}
	
	@GetMapping("createPost")
	public ModelAndView mvCreatePost(HttpSession session) throws Exception{
		UserDTO userDTO = (UserDTO)session.getAttribute("currentUser");
		if (userDTO == null) {
			return new ModelAndView("redirect:/auth/login");
		}
		return new ModelAndView("redirect:/board/addPost");
	}
	
	@GetMapping("updatePost/{no}")
	public ModelAndView mvUpdatePost(@PathVariable("no") int no, HttpSession session) throws Exception{
		UserDTO userDTO = (UserDTO)session.getAttribute("currentUser");
		if (userDTO == null) {
			return new ModelAndView("redirect:/auth/login");
		}
		
		ModelAndView mav = new ModelAndView("board/updatePost");
		BoardDTO boardDTO = boardService.getPost(no);
		mav.addObject("postDto", boardDTO);

		return mav;
	}
}
