package com.cafe24.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.GuestBookDao;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mysite.vo.UserVo;

@Service
public class GuestbookService {
	@Autowired
	private GuestBookDao guestbookDao;
	
	public List getList() {
		return (List<GuestbookVo>)guestbookDao.getList();
	}
	
	public boolean deleteContent( GuestbookVo vo ){
		return 1 == guestbookDao.delete( vo );
	}
	
	public boolean writeContent( GuestbookVo vo ) {
		int count = guestbookDao.insert(vo);
		return count == 1;
	}
}
