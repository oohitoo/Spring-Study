package com.mySpring.springNote3.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.mySpring.springNote3.DTO.NoteDTO;

import lombok.Setter;

public class NoteDAO {
	
	// servlet-context 에서 자동 생성하여 객체를 주입
	@Setter
	private JdbcTemplate template;

	//write
	public void write(final String writer, final String content) {
		// 익명 클래스로 선언
		template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String sql = "insert into tblNote(id, writer, content)values(tblNoteSeq.nextval,?,?)";
				PreparedStatement pstm = con.prepareStatement(sql);
				pstm.setString(1, writer);
				pstm.setString(2, content);			
				return pstm;
			}
		});
	}
	//list
	public ArrayList<NoteDTO> list(){
		String sql = "select * from tblNote order by id desc";
		return (ArrayList<NoteDTO>)template.query(sql, new BeanPropertyRowMapper<NoteDTO> (NoteDTO.class));
	}
	
	//delete
	public void delete(final int id) {
		String sql = "delete from tblNote where id=?";
		template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement psmt) throws SQLException {
				psmt.setInt(1, id);
			}
		});
	}
	
}
