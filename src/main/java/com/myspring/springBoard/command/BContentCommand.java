package com.myspring.springBoard.command;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.myspring.springBoard.dao.BDao;
import com.myspring.springBoard.dto.BDto;


public class BContentCommand implements BCommand{

	@Override //MVC1 -> JSP, Beans, Msg
	//MVC2 -> model
	public void excute(Model model) {
		Map<String , Object> map = model.asMap();
		HttpServletRequest req = (HttpServletRequest)map.get("request");
		try {
			req.setCharacterEncoding("UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		String bId = req.getParameter("bId");
		BDao dao = new BDao();
		BDto dto = dao.contentView(bId);
		model.addAttribute("contentView", dto);
	}

}
