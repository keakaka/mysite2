package com.cafe24.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.repository.UserDao;
import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute UserVo userVo) {
		
		userService.join(userVo);
		
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(
	@ModelAttribute UserVo userVo,
	HttpSession session,
	Model model
	) {
		UserVo authUser = userService.getUser(userVo);
		
		if(authUser == null) {
			model.addAttribute("result", "fail");
			return "user/login";
		}
		// session 처리
		session.setAttribute("authUser", authUser);
		
		return "redirect:/";
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(HttpSession session, Model model) {
		UserVo vo = (UserVo) session.getAttribute("authUser");
		
		UserVo userVo = userService.getUser(vo.getNo());
		model.addAttribute("userVo", userVo);
		return "user/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(UserVo userVo, Model model) {
		
		Boolean check = userService.updateUser(userVo);
		
		if(check == false) {
			model.addAttribute("result", "회원정보 수정 실패");
			return "common/success";
		}
		model.addAttribute("result", "회원정보 수정 완료");
		return "common/success";
	}
	
}
