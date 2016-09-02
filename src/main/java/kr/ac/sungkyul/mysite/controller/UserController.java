package kr.ac.sungkyul.mysite.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.sungkyul.mysite.service.UserService;
import kr.ac.sungkyul.mysite.vo.BoardVo;
import kr.ac.sungkyul.mysite.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	UserService userservice;
	
	@RequestMapping("/joinform")
	public String joinform(){
		return "user/joinform";
	}
	
	@RequestMapping("/join")
	public String join(
		@ModelAttribute UserVo vo){
		userservice.join(vo);
		return "redirect:/user/joinsuccess";
	}
	
	@RequestMapping("/joinsuccess")
	public String joinSuccess(){
		return "user/joinsuccess";
	}
	
	@RequestMapping("/loginform")
	public String loginForm(){
		return "user/loginform";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(
		HttpSession session,
		@RequestParam(value="email", required=false, defaultValue="") String email,
		@RequestParam(value="password", required=false, defaultValue="") String password){
		
		UserVo authUser=userservice.login(email, password);
		if(authUser==null){
			return "redirect:/user/loginform?r=fail";
		}
		//인증 성공 경우
		session.setAttribute("authUser", authUser);
		return "redirect:/main";
	}
	
	@RequestMapping("/logout")
	public String logout(
			HttpSession session){
	if(session!=null){
		
		session.removeAttribute("authUser");
		session.invalidate(); //기존에 쓰고있던 세션 객체를 다른 객체로 바꿔라.
		return "redirect:/main";
		}
	
	return "redirect:/main";
	}
	
	@RequestMapping("/modifyform")
	public String modifyForm(
			HttpSession session,
			Model model){
		
		if(session==null){
			return "redirect:/main";
		}
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			return "redirect:/main";
		}
		Long no=authUser.getNo();
		UserVo vo=userservice.Authinfo(no);
		model.addAttribute("userVo", vo);
		return "/user/modifyform";
	}
	
	@RequestMapping("/modify")
	public String modify(
			HttpSession session,
			@ModelAttribute UserVo vo){
		if(session==null){
			return "redirect:/main";
		}
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			return "redirect:/main";
		}
		Long no=authUser.getNo();
		vo.setNo(no);
		userservice.modify(vo);
		authUser.setName(vo.getName());
		return "redirect:/board/list";
	}
}
