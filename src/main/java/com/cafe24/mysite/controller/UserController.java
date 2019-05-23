package com.cafe24.mysite.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cafe24.mysite.service.UserService;
import com.cafe24.mysite.vo.UserVo;
import com.cafe24.security.Auth;
import com.cafe24.security.AuthUser;

@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(@ModelAttribute UserVo userVo) {
		return "user/join";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(@ModelAttribute @Valid UserVo userVo,BindingResult result, Model model ) {
		if(result.hasErrors()) {
			if( result.hasErrors() ) {
				List<ObjectError> list = result.getAllErrors();
				for(ObjectError error : list) {
					System.out.println(error);
				}
				model.addAllAttributes(result.getModel());	
				return "user/join";
			}
		}
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

//	@RequestMapping(value="/login", method=RequestMethod.POST)
//	public String login(
//		@RequestParam(value="email", required=true, defaultValue="") String email, 
//		@RequestParam(value="password", required=true, defaultValue="") String password,
//		HttpSession session, Model model) {
//		
//		UserVo authUser = userService.getUser( new UserVo(email, password) );
//		if(authUser == null) {
//			model.addAttribute("result", "fail");
//			return "user/login";
//		}
//		
//		// session 처리
//		session.setAttribute("authUser", authUser);
//		
//		return "redirect:/";
//	}
//
//	@RequestMapping("/logout")
//	public String logout(HttpSession session) {
//		session.removeAttribute("authUser");
//		session.invalidate();
//		
//		return "redirect:/";
//	}
	
	
//	@RequestMapping( value="/update", method=RequestMethod.GET )
//	public String update(HttpSession session, Model model ){
//		UserVo authUser = (UserVo)session.getAttribute("authUser");
//		if(authUser == null) {
//			return "redirect:/";
//		}
//		
//		UserVo userVo = userService.getUser( authUser.getNo() );
//		model.addAttribute( "userVo", userVo );
//		return "user/update";
//	}
	@Auth
	@RequestMapping( value="/update", method=RequestMethod.GET )
	public String update(@AuthUser UserVo authUser, Model model ){
		UserVo userVo = userService.getUser( authUser.getNo() );
		model.addAttribute( "userVo", userVo );
		return "user/update";
	}
	
	@RequestMapping( value="/update", method=RequestMethod.POST )
	public String update( HttpSession session, @ModelAttribute UserVo userVo ){
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			return "redirect:/";
		}
		userService.updateUser( userVo );
		// session의 authUser 변경
		authUser.setName(userVo.getName());
		authUser.setGender(userVo.getGender());
		authUser.setEmail(userVo.getEmail());
		
		return "redirect:/user/update?result=success";
	}
}
