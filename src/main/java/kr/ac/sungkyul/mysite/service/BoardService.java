package kr.ac.sungkyul.mysite.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.sungkyul.mysite.dao.BoardDao;
import kr.ac.sungkyul.mysite.vo.BoardVo;

@Service
public class BoardService {
	
	private static final int LIST_PAGESIZE = 10; // 리스팅 되는 게시물 수
	private static final int LIST_BLOCKSIZE = 5; // 페이지 리스트에 표시되는 페이지 수
	
	@Autowired
	private BoardDao boarddao;
	
	public Map<String, Object> listBoard(String spage, String keyword){
		
		int page=Integer.parseInt(spage);
		
		int totalCount = boarddao.getTotalCount();
		int pageCount = (int) Math.ceil((double) totalCount / LIST_PAGESIZE);
		int blockCount = (int) Math.ceil((double) pageCount / LIST_BLOCKSIZE);
		int currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);
		
		// 4. page값 검증
		if (page < 1) {
			page = 1;
			currentBlock = 1;
		} else if (page > pageCount) {
			page = pageCount;
			currentBlock = (int) Math.ceil((double) page / LIST_BLOCKSIZE);
		}

		// 5. 페이지를 그리기 위한 값 계산
		int startPage = (currentBlock - 1) * LIST_BLOCKSIZE + 1;
		int endPage = (startPage - 1) + LIST_BLOCKSIZE;
		int prevPage = (currentBlock > 1) ? (currentBlock - 1) * LIST_BLOCKSIZE : 0;
		int nextPage = (currentBlock < blockCount) ? currentBlock * LIST_BLOCKSIZE + 1 : 0;
		
		List<BoardVo> list=boarddao.getList(page, LIST_PAGESIZE, keyword);
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("sizeList", LIST_PAGESIZE);
		map.put("firstPage", startPage);
		map.put("lastPage", endPage);
		map.put("prevPage", prevPage);
		map.put("nextPage", nextPage);
		map.put("currentPage", page);
		map.put("pageCount", pageCount);
		map.put("list", list);
		map.put("totalCount", totalCount);
		map.put("keyword", keyword);
		
		return map;
	}
	
	public void insert(BoardVo vo){
		boarddao.insert(vo);
	}
	
	public void delete(BoardVo vo){
		
		boarddao.delete(vo);
	}
	
	public BoardVo boardinfo(Long no){
		
		BoardVo vo=boarddao.get2(no);
		return vo;
	}
	
	public void viewcountup(Long no){
		
		boarddao.updateViewCount(no);
	}
	
	public void modify(BoardVo vo){
		boarddao.update(vo);
	}
	
	public void reply(BoardVo vo){
		boarddao.insert(vo);
	}
	
	public void updatereplyCount(int groupno, int ordernumber){
		
		boarddao.updatereplyCount(groupno, ordernumber);
	}
	
	
}
