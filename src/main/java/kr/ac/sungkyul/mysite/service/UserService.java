package kr.ac.sungkyul.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.sungkyul.mysite.dao.UserDao;
import kr.ac.sungkyul.mysite.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userdao;
	
	public void join(UserVo vo){
	userdao.insert(vo);	
	}
	
	public UserVo login(String email, String password){
		
		UserVo authUser=userdao.get(email, password);
		return authUser;
	}
	
	public UserVo Authinfo(Long no){
		
		UserVo vo=userdao.get(no);
		return vo;
	}
	
	public void modify(UserVo vo){
		userdao.update(vo);
	}
}
