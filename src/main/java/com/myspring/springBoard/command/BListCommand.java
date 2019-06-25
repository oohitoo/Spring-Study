package com.myspring.springBoard.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.myspring.springBoard.dao.BDao;
import com.myspring.springBoard.dto.BDto;

public class BListCommand implements BCommand{

	@Override
	public void excute(Model model) {
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		model.addAttribute("list", dtos);
	}
	
}
