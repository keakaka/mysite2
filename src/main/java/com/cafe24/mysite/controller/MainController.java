package com.cafe24.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.vo.UserVo;

@Controller
public class MainController {
	
	@RequestMapping({"/", "/main"})
	public String main() {
		
		return "main/index";
	}
	
	@RequestMapping("/hello")
	@ResponseBody
	public String hello() {
		return "<h1>안녕하세요 !</h1><br><h1>Hello!!</h1>";
	}
	
	@RequestMapping("/hello2")
	@ResponseBody
	public UserVo hello2() {
		UserVo vo = new UserVo();
		vo.setNo(10L);
		vo.setName("박필");
		vo.setEmail("k@k@");
		return vo;
	}
}
