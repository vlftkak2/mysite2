package kr.ac.sungkyul.mysite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product")
public class GalleryController {
	
	@RequestMapping("/list")
	@ResponseBody
	public String gallerylist(){
		
		return "gallerylist:gallery";
	}

}
