package com.cos.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public List<UserVO> select(String userID) throws Exception {
		return sqlSession.selectList(nameSpace+".select", userID);
	}

	@Override
	public int login(String userID, String userPW) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userID", userID);
		map.put("userPW", userPW);
		return sqlSession.selectOne(nameSpace+".login",map);
	}
	
}
