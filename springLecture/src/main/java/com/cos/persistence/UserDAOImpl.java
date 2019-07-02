package com.cos.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.cos.domain.UserVO;

@Repository
public class UserDAOImpl implements UserDAO{

	@Inject
	private SqlSession sqlSession;
	//xml에서 있는 nameSpace	
	private static final String nameSpace = "com.cos.domain.user";
	
	@Override
	public void insert(UserVO userVO) throws Exception {
		sqlSession.insert(nameSpace+".insert", userVO);
	}

	@Override
	public UserVO select(String userID) throws Exception {
		UserVO user = sqlSession.selectOne(nameSpace+".select", userID);
		return user;
	}

	@Override
	public int login(UserVO user) throws Exception {
		return sqlSession.selectOne(nameSpace+".login",user);
	}
	
}
