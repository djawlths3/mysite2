package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.repository.GuestBookDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mysite.vo.UserVo;

@Service
public class BoardService {
	@Autowired
	private BoardDao boardDao;
	
	public List getList() {
		return (List<BoardVo>)boardDao.getList();
	}
	public List getList(int pageNo) {
		pageNo = pageNo*10 -10;
		return (List<BoardVo>)boardDao.getList(pageNo);
	}

	
	public boolean writeContent( BoardVo vo ) {
		int count;
		if(vo.getGroupNo() == null) {
			count = boardDao.insert(vo);			
		} else {
			vo.setOrderNo(vo.getOrderNo() + 1);
			vo.setDepth(vo.getDepth() +1 );
			count = boardDao.insertReply(vo);
		}
		return count == 1;
	}
	public BoardVo getContent(Long no) {
		return (BoardVo)boardDao.getContent(no);
	}
	
}
