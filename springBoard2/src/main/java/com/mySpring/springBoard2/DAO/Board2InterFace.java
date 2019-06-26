package com.mySpring.springBoard2.DAO;

import java.util.ArrayList;

import com.mySpring.springBoard2.DTO.Board2DTO;

public interface Board2InterFace {
	//리스트
	public ArrayList<Board2DTO> boardlist();
	//글작성
	public void writeBoard(String name, String title, String content);
	//삭제
	public void deleteBoard(String id);
	//글 읽기
	public Board2DTO readContent(String id);
	//내용 수정
	public void updateContent(String name, String title, String content,String id);
	//조회수
	public void hitcount(String id);
}
