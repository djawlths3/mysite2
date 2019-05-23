package com.cafe24.mysite.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.util.Paging;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;

	@RequestMapping("/list")
	public String listPage(Model model, @RequestParam(value="p", required=false, defaultValue="1") Long pageNo) {
		Map<String, Object> map = new HashMap<String, Object>();
		List li = boardService.getList(pageNo);
		Long pageSize = (long) boardService.getSize(pageNo);
		map.put("size", pageSize);
		map.put("now", pageNo);
		map.put("pageList", new Paging(pageNo,pageSize).getPageList());
		
		model.addAttribute("list",li);
		model.addAttribute("paging",map);
		return "board/list";
	}
	
	@RequestMapping("/view/{no}")
	public String view(@PathVariable( "no" ) Long no, Model model, HttpSession session) {
		BoardVo vo = boardService.getContent(no);
		model.addAttribute("vo",vo);
		return "board/view";
	}
	
	
	@RequestMapping("/write")
	public String write(HttpSession session) {
		if(session.getAttribute("authUser") == null) {
			return "user/login";
		}
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
		UserVo user = (UserVo) session.getAttribute("authUser");
		vo.setUserNo(user.getNo());	
		boardService.writeContent(vo);
		return "redirect:/board/list";
	}
	
	@RequestMapping( value="/delete/{no}", method=RequestMethod.GET )
	public String delete( @PathVariable( "no" ) Long no, @ModelAttribute BoardVo vo, HttpSession session ) {
		// UserVo userVo = (UserVo) session.getAttribute("authUser");
		boardService.delete(no);
		return "redirect:/board/list";
	}
}
