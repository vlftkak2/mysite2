package kr.ac.sungkyul.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import kr.ac.sungkyul.mysite.dao.guestbookDao;
import kr.ac.sungkyul.mysite.vo.guestbookVo;

@Service
public class GuestService {
	
	@Autowired
	private guestbookDao guestdao;
	
	public List<guestbookVo> list(){
		
		List<guestbookVo> list=guestdao.getList();
		return list;
	}
	
	public void insert(guestbookVo vo){
		guestdao.insert(vo);
	}
	
	public void delete(guestbookVo vo){
		guestdao.delete(vo);
	}

}
