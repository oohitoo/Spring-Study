package com.cos.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.cos.domain.UserVO;
import com.cos.persistence.UserDAO;

@Service
public class UserServiceImpl implements UserService{
	
	@Inject
	private UserDAO dao;

	@Override
	public void insert(UserVO userVO) throws Exception {
		dao.insert(userVO);		
	}

	@Override
	public List<UserVO> select(String userID) throws Exception {
		return dao.select(userID);
	}

	@Override
	public boolean login(String userID, String userPW) throws Exception {
		if( dao.login(userID, userPW) == 1 ) 
			return true;
		return false;
	}	
}
