package com.mySpring.tran.Controller;

import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mySpring.tran.DAO.TranDAO;
import com.mySpring.tran.DTO.TranDTO;

import lombok.Setter;

@Controller
public class trancontroller {
	
	@Setter
	@Autowired
	private TranDAO dao;	

	@RequestMapping(value = "/buy_ticket")
	public String buy_ticket() {
		return "buy_ticket";
	}
	
	@RequestMapping(value = "/buy_ticket_card", method = RequestMethod.POST)
	public String buy_ticket_card(TranDTO dto, Model model) {
		Boolean flag = dao.buyTicket(dto);
		model.addAttribute("tick_info", dto);
		model.addAttribute("flag", flag);
		return "buy_ticket_end";
	}
}
