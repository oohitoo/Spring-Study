package com.mySpring.SpringComm.DAO;

import com.mySpring.SpringComm.DTO.usersDTO;

public interface LoginDAO {
	public usersDTO login(String userid, String userpwd);
	public void createDAO(usersDTO dto);
}
