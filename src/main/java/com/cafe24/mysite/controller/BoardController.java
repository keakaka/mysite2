package com.cafe24.mysite.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.FileVo;
import com.cafe24.mysite.vo.Paging;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.Auth.Role;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	@Auth(role=Auth.Role.USER)
	@RequestMapping("/list")
	public String list(@ModelAttribute Paging pg, Model model) {
		List<BoardVo> list = boardService.getList(pg);
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@Auth(role=Auth.Role.USER)
	@RequestMapping(value="/write", method=RequestMethod.GET)
	public String write() {
		return "board/write";
	}
	
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(
	@ModelAttribute BoardVo boardVo,
	@RequestParam(value="file") MultipartFile multipartFile,
	Model model
	) {
		boardVo.setContent(boardVo.getContent().replace("\r\n", "<br>"));
		Boolean check = boardService.insertBoard(boardVo);
		Long boardNo = boardService.getLastIndex();
		if(check == true && multipartFile.getSize() != 0) {
			Boolean fileCheck = boardService.uploadFile(multipartFile, boardNo);
			if(!fileCheck) {
				return "error/500";
			}
		}
		
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value="/view", method=RequestMethod.GET)
	public String view(Long no, Model model) {
		BoardVo vo = boardService.getOne(no);
		FileVo fileVo = boardService.getFile(no);
		model.addAttribute("vo", vo);
		model.addAttribute("fileVo", fileVo);
		return "board/view";
	}
	
	@RequestMapping(value="/reply")
	public String view(Model model, @ModelAttribute BoardVo boardVo, HttpServletRequest request) {
		UserVo authUser = (UserVo) request.getSession().getAttribute("authUser");
		
		if(authUser == null || authUser.getNo() != boardVo.getWriterNo()) {
			return "error/404";
		}
		model.addAttribute("boardVo", boardVo);
		return "board/writeReply";
	}
	
	@RequestMapping(value="/delete")
	public String view(Model model, Long no) {
		boardService.delete(no);
		return "redirect:/board/list";
	}
	
	@RequestMapping("/update")
	public String update(@ModelAttribute BoardVo boardVo, Model model, HttpServletRequest request) {
		UserVo authUser = (UserVo) request.getSession().getAttribute("authUser");
		BoardVo vo = boardService.getOne(boardVo.getNo());
		if(authUser == null || authUser.getNo() != boardVo.getWriterNo()) {
			return "error/404";
		}
		
		vo.setContent(vo.getContent().replace("<br>", "\r\n"));
		model.addAttribute("vo", vo);
		
		return "board/update";
	}
	
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@ModelAttribute BoardVo boardVo, HttpServletRequest request) {
		UserVo authUser = (UserVo) request.getSession().getAttribute("authUser");
		if(authUser == null) {
			return "error/404";
		}
		boardVo.setContent(boardVo.getContent().replace("\r\n", "<br>"));
		Boolean check = boardService.updateBoard(boardVo);
		if(!check) {
			return "error/500";
		}
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/fileDown")
	public void fileDown(String downUrl, String oriName, HttpServletResponse response) {
		
		byte fileByte[];
		
		try {
			fileByte = FileUtils.readFileToByteArray(new File(downUrl));
			response.setContentType("application/octet-stream"); response.setContentLength(fileByte.length); 
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(oriName,"UTF-8")+"\";"); 
			response.setHeader("Content-Transfer-Encoding", "binary"); response.getOutputStream().write(fileByte); 
			response.getOutputStream().flush(); 
			response.getOutputStream().close();

		} catch (IOException e) {
			e.printStackTrace();
		} 
		

	}
}
