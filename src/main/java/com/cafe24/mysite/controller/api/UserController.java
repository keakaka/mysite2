package com.cafe24.mysite.controller.api;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cafe24.mysite.dto.JSONResult;
import com.cafe24.mysite.service.UserService;

@Controller("userAPIController")
@RequestMapping("/user/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	@RequestMapping("/checkemail")
//	@ResponseBody
//	public Map<String, Object> checkEmail(@RequestParam(value="email", required=true, defaultValue="") String email) {
//		
//		Boolean exist = userService.existEmail(email);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("result", "success");
//		map.put("data", exist);
//		
//		return map;
//	}
	@RequestMapping("/checkemail")
	@ResponseBody
	public JSONResult checkEmail(@RequestParam(value="email", required=true, defaultValue="") String email) {
		
		Boolean exist = userService.existEmail(email);
		return JSONResult.success(exist);
	}
	
}
