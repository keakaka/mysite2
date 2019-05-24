package com.cafe24.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List<GuestbookVo> list = guestbookService.list();
		model.addAttribute("list", list);
		return "guestbook/list";
	}
	
	@RequestMapping("/insert")
	public String list(GuestbookVo guestbookVo, Model model) {
		Boolean check = guestbookService.insert(guestbookVo);
		if(check == false) {
			model.addAttribute("result", "방명록 쓰기 실패");
			return "common/success";
		}
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/deleteform", method=RequestMethod.GET)
	public String deleteform(@ModelAttribute GuestbookVo vo) {
		System.out.println(vo+"@@@@@");
		return "guestbook/deleteform";
	}
	
//	@RequestMapping(value="/deleteform", method=RequestMethod.GET)
//	public String deleteform(@ModelAttribute(value="no") GuestbookVo vo) {
//		return "guestbook/deleteform";
//	}

	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(GuestbookVo guestbookVo, Model model) {
		
		System.out.println(guestbookVo);
		Boolean check = guestbookService.delete(guestbookVo);
		if(check == false) {
			model.addAttribute("result", "방명록 삭제 실패");
			return "common/success";
		}
		
		return "redirect:/guestbook/list";
	}
}
