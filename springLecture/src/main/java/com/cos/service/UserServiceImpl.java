package com.cos.service;

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
	public UserVO select(String userID) throws Exception {
		return dao.select(userID);
	}

	@Override
	public int login(UserVO user) throws Exception {
		return dao.login(user);
	}	
}
