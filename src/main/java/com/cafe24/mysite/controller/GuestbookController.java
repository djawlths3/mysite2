package com.cafe24.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.GuestbookService;
import com.cafe24.mysite.vo.GuestbookVo;

@RequestMapping("guestbook")
@Controller
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String list(Model model) {
		List li = guestbookService.getList();
		model.addAttribute("list",li);
		return "guestbook/list";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.GET)
	public String deleteform(@PathVariable( "no" ) Long no, Model model ) {
		model.addAttribute( "no", no );
		return "guestbook/deleteform";
	}
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(@ModelAttribute GuestbookVo vo, Model model) {
		System.out.println( vo );
		guestbookService.deleteContent( vo );
		List li = guestbookService.getList();
		model.addAttribute("list",li);
		return "guestbook/list";
	}
	
	@RequestMapping( value="/add", method=RequestMethod.POST )
	public String add( @ModelAttribute GuestbookVo vo ) {
		guestbookService.writeContent( vo );
		return "redirect:/guestbook";
	}
}
