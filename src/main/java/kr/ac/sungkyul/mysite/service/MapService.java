package kr.ac.sungkyul.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.sungkyul.mysite.dao.MapDao;
import kr.ac.sungkyul.mysite.vo.MapVo;

@Service
public class MapService {
	
	@Autowired
	private MapDao mapdao;
	
	public List<MapVo> list(){
		
		List<MapVo> list=mapdao.getList();
		System.out.println(list);
		return list;
	}

}
