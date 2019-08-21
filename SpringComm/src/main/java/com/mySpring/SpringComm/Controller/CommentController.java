package com.mySpring.SpringComm.Controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mySpring.SpringComm.DAO.CommentDAO;
import com.mySpring.SpringComm.DTO.CommentDTO;


@Controller
public class CommentController {
	
	@Autowired
	private SqlSession sqlsession;
	
	@RequestMapping(value = "Comment")
	public String comment(Model model, HttpServletRequest req) {
		System.out.println(req.getParameter("nowPage"));
		CommentDAO dao = sqlsession.getMapper(CommentDAO.class);
		model.addAttribute("totalRecord", dao.totalRecord());
		
		if(req.getParameter("nowPage") != null) {
			model.addAttribute("page",pageing(dao.totalRecord(), req.getParameter("nowPage")));
			int numPerPage = pageing(dao.totalRecord(), req.getParameter("nowPage")).get("numPerPage");
			System.out.println(pageing(dao.totalRecord(), req.getParameter("nowPage")));
			if(req.getParameter("start") != null) {
				model.addAttribute("listComment", dao.selectComment(Integer.parseInt(req.getParameter("start")), numPerPage));
			}else {
				model.addAttribute("listComment", dao.selectComment(0, numPerPage));
			}
			
		}else {			
			model.addAttribute("page",pageing(dao.totalRecord(), "1"));
		}		
		return "GuestBook/Comment";
	}
	
	@RequestMapping(value = "/commentInsert", method = RequestMethod.POST)
	public String commentInsert(HttpServletRequest req, CommentDTO dto,Model model, RedirectAttributes rttr) {
				
		CommentDAO dao = sqlsession.getMapper(CommentDAO.class);		
		dto.setUsername(req.getParameter("commentuser"));
		dto.setUsercomment(req.getParameter("content"));
		// 0 비밀글
		// 1 일반글
		if(req.getParameter("secret") == null) {
			dto.setSercret(0);
		}else {
			dto.setSercret(1);
		}		
		dao.commentsInsert(dto);
		return "redirect:Comment";
	}
	
	public HashMap<String, Integer> pageing(int total, String nowPages){
		HashMap<String, Integer> pages = new HashMap<String, Integer>();
				
		int totalRecord = total; 
		int numPerPage = 10;
		int pagePerBlock = 5;
		int totalBlock = 0;
		int totalPage = 0;
		int nowPage = Integer.parseInt(nowPages);
		int nowBlock = 1;

		int start = 0; 
		int end = numPerPage; 
		
		
		if(nowPages != null){
			nowPage = Integer.parseInt(nowPages);
		}
		start = (nowPage * numPerPage) - numPerPage; // (1 * 10) - 10 = 0 || (2 * 10) - 10 = 10
				
	
		totalPage = (int)Math.ceil((double)totalRecord / numPerPage);
	
		totalBlock = (int)Math.ceil((double)totalPage / pagePerBlock);
		
		nowBlock = (int)Math.ceil((double)nowPage / pagePerBlock);
		
		pages.put("start", start);
		pages.put("numPerPage", numPerPage);
		pages.put("nowPage", nowPage);
		pages.put("nowBlock", nowBlock);
		pages.put("totalPage", totalPage);	
		pages.put("totalBlock", totalBlock);
		pages.put("pagePerBlock", pagePerBlock);
		
		return pages;		
	}
}
