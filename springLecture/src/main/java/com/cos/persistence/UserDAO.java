package com.cos.persistence;

import java.util.List;

import com.cos.domain.UserVO;

public interface UserDAO {
	public void insert(UserVO userVO) throws Exception;
	public List<UserVO> select(String userID) throws Exception;	
	public int login(String userID, String userPW) throws Exception;
}
