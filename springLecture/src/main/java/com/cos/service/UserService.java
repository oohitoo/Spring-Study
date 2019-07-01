package com.cos.service;

import java.util.List;

import com.cos.domain.UserVO;

public interface UserService {
	public void insert(UserVO userVO) throws Exception;
	public List<UserVO> select(String userID) throws Exception;
	public boolean login(String userID, String userPW) throws Exception;
}
