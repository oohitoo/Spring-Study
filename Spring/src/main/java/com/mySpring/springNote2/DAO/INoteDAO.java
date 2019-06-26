package com.mySpring.springNote2.DAO;

import java.util.ArrayList;

import com.mySpring.springNote2.DTO.NoteDTO;

public interface INoteDAO {
	public ArrayList<NoteDTO> listDAO();
	public void writeDAO(String writer, String content);
	public void deleteDAO(String id);
	
}
