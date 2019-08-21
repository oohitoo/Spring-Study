package com.mySpring.SpringComm.DAO;

import java.util.ArrayList;

import com.mySpring.SpringComm.DTO.CommentDTO;

public interface CommentDAO {
	// Insert
	public void commentsInsert(CommentDTO dto);
	// VO반환
	/* public ArrayList<CommentDTO> selectComment(); */
	public ArrayList<CommentDTO> selectComment(int start, int end);

	// Page 처리
	// 전체 개수 반환
	public int totalRecord();
}
