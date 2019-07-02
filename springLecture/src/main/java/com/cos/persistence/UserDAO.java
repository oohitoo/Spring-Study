package com.cos.persistence;

import java.util.List;

import com.cos.domain.UserVO;

public interface UserDAO {
	public void insert(UserVO userVO) throws Exception;
	public UserVO select(String userID) throws Exception;	
	public int login(UserVO user) throws Exception;
}
