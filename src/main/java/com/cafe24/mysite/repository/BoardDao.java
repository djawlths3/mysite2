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

import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.GuestbookVo;


@Repository
public class BoardDao {
	
	@Autowired
	private SqlSession sqlSession;

	@Autowired
	private DataSource datasource;
	
	
	public int delete( GuestbookVo vo ) {
		int count = sqlSession.delete( "guestbook.delete", vo );
		return count;
	}
	
	public int insert( BoardVo vo ) {
		int check = sqlSession.insert( "board.insert", vo );
		if(check > 0) {
			sqlSession.update("board.updateGroupNo",vo.getNo());					
		}
		return check;
	}
	
	public int insertReply( BoardVo vo ) {
		sqlSession.update("board.updateOrderNo",vo);
		int check = sqlSession.insert( "board.insert", vo );
		return check;
	}
	
	public List<BoardVo> getList(){
		List<BoardVo> result = sqlSession.selectList("board.list");
		return result;
	}
	public List<BoardVo> getList(int pageNo){
		List<BoardVo> result = sqlSession.selectList("board.list", pageNo);
		return result;
	}
	
	public BoardVo getContent(Long no){
		BoardVo result = sqlSession.selectOne("board.selectDetail", no);
		return result;
	}
}
