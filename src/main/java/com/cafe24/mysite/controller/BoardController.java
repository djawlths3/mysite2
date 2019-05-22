package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/list")
	public String list(Model model) {
		List li = boardService.getList();
		model.addAttribute("list",li);
		return "board/list";
	}
	
	@RequestMapping("/list/{pageNo}")
	public String listPage(Model model, @PathVariable( "pageNo" ) int pageNo) {
		List li = boardService.getList(pageNo);
		model.addAttribute("list",li);
		return "board/list";
	}
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable( "no" ) Long no, Model model, HttpSession session) {
		BoardVo vo = boardService.getContent(no);
		model.addAttribute("vo",vo);
		return "board/view";
	}
	
	@RequestMapping("/write")
	public String write() {
		return "board/write";
	}
	
	@RequestMapping("/reply")
	public String reply(@ModelAttribute BoardVo vo,Model model) {
		model.addAttribute("vo",vo);
		return "board/write";
	}
	
	@RequestMapping("/modify")
	public String modify() {
		return "board/modify";
	}
	
	@RequestMapping( value="/add", method=RequestMethod.POST )
	public String add( @ModelAttribute BoardVo vo, HttpSession session ) {
		// guestbookService.writeContent( vo );
		UserVo user = (UserVo) session.getAttribute("authUser");
		vo.setUserNo(user.getNo());	
		boolean b = boardService.writeContent(vo);
		return "redirect:/board/list";
	}
}
