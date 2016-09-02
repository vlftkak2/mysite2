package kr.ac.sungkyul.mysite.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.ac.sungkyul.mysite.service.BoardService;
import kr.ac.sungkyul.mysite.vo.BoardVo;
import kr.ac.sungkyul.mysite.vo.UserVo;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardservice; 
	
	@RequestMapping("/list")
	public String list(
			Model model,
			@RequestParam(value="p",required=true,defaultValue="1") String page,
			@RequestParam(value="kwd",required=false,defaultValue="") String keyword){
		
		Map<String, Object> map=boardservice.listBoard(page, keyword);
		
		model.addAttribute("map", map);
		return "/board/list";
	}
	
	@RequestMapping("/writeform")
	public String wirteform(
			HttpSession session){
		
		if(session==null){
			return "redirect:/main";
		}
		
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			return "redirect:/main";
		}
		
		return "/board/write";
	}
	
	@RequestMapping("/write")
	public String write(
			@ModelAttribute BoardVo vo,
			HttpSession session){
		
		if(session==null){
			return "redirect:/main";
		}
		
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			return "redirect:/main";
		}
		vo.setUserNo(authUser.getNo());
		boardservice.insert(vo);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/delete")
	public String delete(
			HttpSession session,
			@RequestParam("no") Long no,
			@ModelAttribute BoardVo vo){
		
		if(session==null){
			return "redirect:/main";
		}
		
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			return "redirect:/main";
		}
		vo.setNo(no);
		
		boardservice.delete(vo);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/viewform")
	public String viewfrom(
			HttpSession session,
			@RequestParam(value="no",required=true, defaultValue="1") Long no,
			Model model){
		
		if(session==null){
			return "redirect:/main";
		}
		
		BoardVo vo=boardservice.boardinfo(no);
		
		if(vo==null){
			return "redirect:/board/list";
		}
		model.addAttribute("BoardVo", vo);
		
		boardservice.viewcountup(no);
		
		return "/board/view";
	}
	
	@RequestMapping("/modifyform")
	public String modifyform(
			HttpSession session,
			@RequestParam("no") Long no,
			Model model){
		
		if(session==null){
			return "redirect:/main";
		}
		
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			return "redirect:/main";
		}
		
		BoardVo vo=boardservice.boardinfo(no);
		model.addAttribute("BoardVo", vo);
		
		return "/board/modify";
	}
	
	@RequestMapping("/modify")
	public String modify(
			HttpSession session,
			@RequestParam("no") Long no,
			@ModelAttribute BoardVo vo){
		
		if(session==null){
			return "redirect:/main";
		}
		
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			return "redirect:/main";
		}
		vo.setNo(no);
		
		boardservice.modify(vo);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/replyform")
	public String replyform(
			HttpSession session,
			@RequestParam("no") Long no,
			Model model){
		
		if(session==null){
			return "redirect:/main";
		}
		
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			return "redirect:/main";
		}
		
		
		BoardVo vo=boardservice.boardinfo(no);
		model.addAttribute("BoardVo", vo);
		
		System.out.println(vo);
		
		return "/board/reply";
	}
	
	@RequestMapping("/reply")
	public String reply(
			HttpSession session,
			@ModelAttribute BoardVo vo){
		
		if(session==null){
			return "redirect:/main";
		}
		
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			return "redirect:/main";
		}
		
		int depth=vo.getDepth()+1;
		int groupOrderno=vo.getGroupOrderNo()+1;
		int groupNo=vo.getGroupNo();
		

		vo.setDepth(depth);
		vo.setGroupOrderNo(groupOrderno);
		
		
		boardservice.updatereplyCount(groupNo, groupOrderno);
		
		boardservice.reply(vo);
		
		System.out.println(vo);
		
		
		
		return "redirect:/board/list";
	}
	
	@RequestMapping("/search")
	public String search(
			HttpSession session){
		
		if(session==null){
			return "redirect:/main";
		}
		
		UserVo authUser=(UserVo)session.getAttribute("authUser");
		if(authUser==null){
			return "redirect:/main";
		}
		
		
		
		return "redirect:/board/list";
	}
	
	
	
}
	
