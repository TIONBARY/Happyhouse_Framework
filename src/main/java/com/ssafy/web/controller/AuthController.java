package com.ssafy.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.web.dto.UserDTO;
import com.ssafy.web.service.UserService;

@Controller
@RequestMapping("auth")
public class AuthController {

	@Autowired
	UserService userService;
	
	@GetMapping("register")
	public ModelAndView getRegisterPage() throws Exception {
		return new ModelAndView("auth/register");
	}
	
	@PostMapping("register")
	public ModelAndView register(@RequestParam String id,
			@RequestParam String pwd,
			@RequestParam String name,
			@RequestParam String email,
			@RequestParam String phone) throws Exception{
		int cnt = 0;
		UserDTO userDto = new UserDTO();

		userDto.setId(id);
		userDto.setPassword(pwd);
		userDto.setName(name);
		userDto.setEmail(email);
		userDto.setPhone(phone);
		cnt = userService.createUser(userDto);
		
		
		if (cnt == 1) {
			ModelAndView mav = new ModelAndView("auth/login");
			mav.addObject("ok", true);
			mav.addObject("msg", "회원 가입 성공");
			return mav;
		} else {
			ModelAndView mav = new ModelAndView("auth/register");
			mav.addObject("ok", false);
			mav.addObject("msg", "이미 존재하는 아이디입니다.");
			return mav;
		}

	}
	
	@GetMapping("login")
	public ModelAndView getLoginPage() throws Exception {
		return new ModelAndView("auth/login");
	}
	
	@PostMapping("login")
	public ModelAndView login(@RequestParam String id,
			@RequestParam String pwd,
			HttpSession session) throws Exception{
		
		UserDTO userDto = userService.login(id, pwd);
		
		if (userDto != null) {
			ModelAndView mav = new ModelAndView("index");
			session.setAttribute("currentUser", userDto);
			mav.addObject("ok", true);
			mav.addObject("msg", "로그인 성공");
			return mav;
		}else {
			ModelAndView mav = new ModelAndView("auth/login");
			mav.addObject("ok", false);
			mav.addObject("msg", "잘못된 아이디 혹은 비밀번호입니다.");
			return mav;
		}
	}
	
	
	@GetMapping("logout")
	public ModelAndView logout(HttpSession session) throws Exception {
		session.invalidate();
		return new ModelAndView("index");
	}
	
	@GetMapping("mypage")
	public ModelAndView getMyPage() throws Exception {
		return new ModelAndView("auth/mypage");
	}
	
	@GetMapping("mvMypage")
	public ModelAndView getMyPage(HttpSession session) throws Exception {
		UserDTO userDto = (UserDTO) session.getAttribute("currentUser");
		
		if(userDto != null) {
			return new ModelAndView("redirect:/auth/mypage");
		} else {
			return new ModelAndView("redirect:/auth/login");
		}
	}
	
	@PostMapping("update")
	public ModelAndView update(@RequestParam String id,
			@RequestParam String pwd,
			@RequestParam String name,
			@RequestParam String email,
			@RequestParam String phone,
			HttpSession session) throws Exception {
		UserDTO userDto = (UserDTO) session.getAttribute("currentUser");
		
		if(userDto != null) {
			UserDTO newUserDto = new UserDTO();
			newUserDto.setId(id);
			newUserDto.setPassword(pwd);
			newUserDto.setName(name);
			newUserDto.setEmail(email);
			newUserDto.setPhone(phone);
			
			int cnt = userService.updateUser(newUserDto);
			
			ModelAndView mav = new ModelAndView("mypage");
			if (cnt == 1) {
				session.setAttribute("currentUser", newUserDto);
				mav.addObject("ok", true);
				mav.addObject("msg", "회원 정보 수정 완료");
			} else {
				mav.addObject("ok", false);
				mav.addObject("msg", "회원 정보 수정 완료 실패");
			}
			
			return mav;
		}
		
		return mvLogin(session);
	}
	
	@GetMapping("delete")
	public ModelAndView delete(HttpSession session) throws Exception {
		UserDTO userDto = (UserDTO) session.getAttribute("currentUser");
		
		if(userDto != null) {
			String id = userDto.getId();
			int cnt = userService.deleteUser(id);
			if(cnt == 1) {
				logout(session);
				return new ModelAndView("index");
			} else {
				throw new Exception("회원 탈퇴 실패");
			}
		} else {
			return new ModelAndView("auth/login");
		}
	}
	
	@GetMapping("mvRegister")
	public ModelAndView mvRegister(HttpSession session) throws Exception {
		return new ModelAndView("redirect:/auth/register");
	}
	
	@GetMapping("mvLogin")
	public ModelAndView mvLogin(HttpSession session) throws Exception {
		return new ModelAndView("redirect:/auth/login");
	}
	
	
}
