package kr.ac.sungkyul.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.ac.sungkyul.mysite.service.MapService;
import kr.ac.sungkyul.mysite.vo.MapVo;

@Controller
@RequestMapping("/map")
public class MapController {
	
	@Autowired
	MapService mapservice;
	
	@RequestMapping("/list")
	public String maplist(
			Model model){
		
		List<MapVo> list=mapservice.list();
		
		model.addAttribute("list", list);
		
		System.out.println(list);
		return "/map/maplist";
	}
	
	

}
