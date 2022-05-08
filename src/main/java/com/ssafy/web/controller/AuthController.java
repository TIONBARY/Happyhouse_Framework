package com.ssafy.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ssafy.web.dto.LoginHistoryDTO;
import com.ssafy.web.dto.UserDTO;
import com.ssafy.web.service.LoginHistoryService;
import com.ssafy.web.service.UserService;

@Controller
@RequestMapping("auth")
public class AuthController {

	@Autowired
	UserService userService;
	
	@Autowired
	LoginHistoryService loginHistoryService;
	
	@GetMapping("register")
	public ModelAndView register() throws Exception {
		return new ModelAndView("auth/register");
	}
	
	@PostMapping("register")
	public ModelAndView register(@ModelAttribute UserDTO userDto) throws Exception{
		int cnt = 0;
		
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
	public ModelAndView login() throws Exception {
		return new ModelAndView("auth/login");
	}
	
	@PostMapping("login")
	public ModelAndView login(@RequestParam String id,
			@RequestParam String password,
			HttpSession session,
			RedirectAttributes ra,
			HttpServletRequest request) throws Exception{
		
		String ip = request.getRemoteAddr();
		LoginHistoryDTO loginHistoryDTO = loginHistoryService.getHistory(ip);
		int loginCount = loginHistoryDTO.getRetryCount();
		if (loginCount >= 14) {
			ra.addFlashAttribute("ok", false);
			ra.addFlashAttribute("msg", "로그인 시도 회수 제한을 초과하여 접근이 제한되었습니다 관리자에 문의하세요.");
			ModelAndView mav = new ModelAndView("redirect:/auth/login");
			return mav;
		}
		else {
			loginHistoryDTO.setRetryCount(loginCount + 1);
		}
		
		
		UserDTO userDto = userService.login(id, password);
		
		if (userDto != null) {
			loginHistoryService.cleanHistory(loginHistoryDTO);
			ra.addFlashAttribute("ok", true);
			ra.addFlashAttribute("msg", "로그인 성공");
			ModelAndView mav = new ModelAndView("redirect:/");
			session.setAttribute("currentUser", userDto);
			return mav;
		}else {
			loginHistoryService.updateHistory(loginHistoryDTO);
			ra.addFlashAttribute("ok", false);
			ra.addFlashAttribute("msg", "잘못된 아이디 혹은 비밀번호입니다. 현재 시도횟수는 15회 중 " + (loginCount + 1) + "회입니다.");
			ModelAndView mav = new ModelAndView("redirect:/auth/login");
			return mav;
		}
	}
	
	
	@GetMapping("logout")
	public ModelAndView logout(HttpSession session) throws Exception {
		session.invalidate();
		return new ModelAndView("redirect:/");
	}
	
	@GetMapping("mypage")
	public ModelAndView getMyPage(HttpSession session) throws Exception {

		return new ModelAndView("auth/mypage");
	}

	
	@PostMapping("update")
	public ModelAndView update(@ModelAttribute UserDTO newUserDto,
			HttpSession session) throws Exception {
		
		int cnt = userService.updateUser(newUserDto);
		
		ModelAndView mav = new ModelAndView("auth/mypage");
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
	
	@GetMapping("delete")
	public ModelAndView delete(HttpSession session) throws Exception {
		UserDTO userDto = (UserDTO) session.getAttribute("currentUser");
		
		String id = userDto.getId();
		int cnt = userService.deleteUser(id);
		if(cnt == 1) {
			logout(session);
			return new ModelAndView("redirect:/");
		} else {
			throw new Exception("회원 탈퇴 실패");
		}
		
	}
	
	
	
	
}
