package com.cos.service;

import com.cos.domain.UserVO;

public interface UserService {
	public void insert(UserVO userVO) throws Exception;
	public UserVO select(String userID) throws Exception;
	public int login(UserVO user) throws Exception;
}
