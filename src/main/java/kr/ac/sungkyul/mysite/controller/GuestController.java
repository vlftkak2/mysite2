package kr.ac.sungkyul.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.sungkyul.mysite.service.GuestService;
import kr.ac.sungkyul.mysite.vo.guestbookVo;

@Controller
@RequestMapping("/guest")
public class GuestController {
	
	@Autowired 
	GuestService guestservice;
	
	@RequestMapping("/list")
	public String list(Model model){
		
		List<guestbookVo> list=guestservice.list();
		model.addAttribute("list",list);
		return "/guestbook/list";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(
		@ModelAttribute guestbookVo vo){
		
		guestservice.insert(vo);
		return "redirect:/guest/list";
	}
	

	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteform(
		@RequestParam(value="no", required=false, defaultValue="-1") Long no,
		Model model){
		
		model.addAttribute("no", no);
		return "/guestbook/deleteform";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(
		@ModelAttribute guestbookVo vo){
		
	    guestservice.delete(vo);
		return "redirect:/guest/list";
	}

}
