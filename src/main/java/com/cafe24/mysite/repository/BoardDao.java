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
	
	public List<BoardVo> getList(Long pageNo){
		List<BoardVo> result = sqlSession.selectList("board.list",pageNo);
		return result;
	}
	
	public int getSize(Long pageNo){
		int result = sqlSession.selectOne("board.size" );
		return result;
	}
	
	public BoardVo getContent(Long no){
		BoardVo result = sqlSession.selectOne("board.selectDetail", no);
		return result;
	}
	
	public List<BoardVo> selectReplyList(BoardVo vo){
		List<BoardVo> result = sqlSession.selectList("board.selectReplyList", vo);
		return result;
	}
	
	public List<BoardVo> deleteReplyList(Long no){
		List<BoardVo> result = sqlSession.selectList("board.deleteReplyList", no);
		return result;
	}
	
	public int delete( Long no ) {
		int count = sqlSession.delete( "board.delete", no );
		return count;
	}
	
	
	public int insertReplyTable( BoardVo vo ) {
		int check = sqlSession.insert( "board.insertReplyTable", vo );
		return check;
	}
}
