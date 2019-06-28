package com.mySpring.tran.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.mySpring.tran.DTO.TranDTO;

import lombok.Setter;

@Setter
public class TranDAO {
	
	private JdbcTemplate template;
	private PlatformTransactionManager transactionManager;
	
	public ArrayList<TranDTO> list(){
		String sql = "select * from tblTicket";
		return (ArrayList<TranDTO>)template.query(sql, new BeanPropertyRowMapper<TranDTO>(TranDTO.class));
	}
	
	// 티켓 구매
	public boolean buyTicket(final TranDTO dto) {
		boolean result = false;
		// 트랜잭션 추가
		TransactionDefinition definition = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(definition);
		
		try {
			//카드 입력
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String sql = "insert into tblCard values(?,?)";
					PreparedStatement psmt = con.prepareStatement(sql);
					psmt.setString(1, dto.getCounsumerId());
					psmt.setInt(2, dto.getAmount());
					return psmt;
				}
			});
			//ticket 입력
			template.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					String sql = "insert into tblTicket values(?,?)";
					PreparedStatement psmt = con.prepareStatement(sql);
					psmt.setString(1, dto.getCounsumerId());
					psmt.setInt(2, dto.getAmount());
					return psmt;
				}
			});
			transactionManager.commit(status);
			result = true;
		}
		catch(Exception e){		
			transactionManager.rollback(status);
			result = false;
		}
		return result;
	}
}
