package com.myspring.springBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.myspring.springBoard.dao.BDao;

public class BDeleteCommand implements BCommand{

	@Override
	public void excute(Model model) {
		Map<String, Object> map = model.asMap(); 
		HttpServletRequest req = (HttpServletRequest)map.get("request");
		String bId = req.getParameter("bId");
		System.out.println(bId);
		BDao dao = new BDao();		
		dao.delete(bId);
	}

}
