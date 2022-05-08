package com.ssafy.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.ssafy.web.dto.UserDTO;

public class SessionInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		UserDTO userDto = (UserDTO) session.getAttribute("currentUser");

		if(userDto != null) {
			return true;
		} else {
			ModelAndView mav = new ModelAndView("redirect:/auth/login");
			throw new ModelAndViewDefiningException(mav);
		}
		
	}
}
