package com.mySpring.SpringComm.DAO;

import java.util.ArrayList;

import com.mySpring.SpringComm.DTO.CommentDTO;

public interface CommentDAO {
	// Insert
	public void commentsInsert(CommentDTO dto);
	// VO��ȯ
	/* public ArrayList<CommentDTO> selectComment(); */
	public ArrayList<CommentDTO> selectComment(int start, int end);

	// Page ó��
	// ��ü ���� ��ȯ
	public int totalRecord();
}
