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
	

	public List getList(Long pageNo) {
		pageNo = pageNo*10 -10;
		return (List<BoardVo>)boardDao.getList(pageNo);
	}
	public int getSize(Long pageNo) {
		pageNo = pageNo*10 -10;
		return boardDao.getSize(pageNo);
	}
	
	public boolean writeContent( BoardVo vo ) {
		int count;
		if(vo.getGroupNo() == null) {
			count = boardDao.insert(vo);			
		} else {
			 vo.setOrderNo(vo.getOrderNo() + 1);
			 vo.setDepth(vo.getDepth() +1 );
			count = boardDao.insertReply(vo);
			 vo.setOrderNo(vo.getOrderNo() -1);
		}
		List<BoardVo> li = boardDao.selectReplyList(vo);
		
		// 현재 변경된 게시물 번호 추가
		BoardVo nowChangePostNoVo = new BoardVo();
		nowChangePostNoVo.setNo(vo.getNo());
		li.add(nowChangePostNoVo);
		
		for(BoardVo t : li){
			vo.setParentNo(t.getNo());
			boardDao.insertReplyTable(vo);
		}
		return count == 1;
	}
	public BoardVo getContent(Long no) {
		return (BoardVo)boardDao.getContent(no);
	}
	
	public void delete(Long no) {
		List<BoardVo> li = boardDao.deleteReplyList(no);
		for(BoardVo t : li){			
			boardDao.delete(t.getNo());
		}
	}
	
}
