package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.GuestbookVo;


@Repository
public class GuestBookDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private DataSource datasource;
	
	
	public int delete( GuestbookVo vo ) {
		int count = sqlSession.delete( "guestbook.delete", vo );
		return count;
	}
	
	public int insert( GuestbookVo vo ) {
		return sqlSession.insert( "guestbook.insert", vo );
	}
	
	public List<GuestbookVo> getList(){
		List<GuestbookVo> result = sqlSession.selectList("getList");
		return result;
	}
	

}
