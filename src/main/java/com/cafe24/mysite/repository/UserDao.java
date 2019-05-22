package com.cafe24.mysite.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.exception.UserDaoException;
import com.cafe24.mysite.vo.GuestbookVo;
import com.cafe24.mysite.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	@Autowired
	private DataSource datasource;

	public UserVo get(Long no){
		return sqlSession.selectOne("user.getByNo", no);
	}
	
	public UserVo get(String email){
		return sqlSession.selectOne("user.getByEmail", email);
	}

	public UserVo get(String email, String password) throws UserDaoException{
		UserVo result = null;
		Map map = new HashMap();
		map.put("email", email);
		map.put("password", password);
		result = sqlSession.selectOne("user.getByEmailAndPassword",map);
		return result;
	}

	public Boolean insert(UserVo vo) {
		int cnt = sqlSession.insert("user.insert",vo);
		return 1==cnt;
	}

	public int update( UserVo userVo ) {
		return sqlSession.update( "user.update", userVo );
	}


}
