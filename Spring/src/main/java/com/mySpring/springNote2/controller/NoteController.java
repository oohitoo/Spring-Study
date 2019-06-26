package com.mySpring.springNote2.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mySpring.springNote2.DAO.INoteDAO;

@Controller
public class NoteController {

	@Autowired
	private SqlSession sqlSession;

	@RequestMapping(value = "list")
	public String list(Model model) {
		INoteDAO dao = sqlSession.getMapper(INoteDAO.class);
		model.addAttribute("list", dao.listDAO());
		return "NoteList";
	}

	@RequestMapping(value = "/writeview")
	public String writeview(Model model) {
		return "noteForm";
	}

	@RequestMapping(value = "/wirte")
	public String noteForm(HttpServletRequest req, Model model) {
		INoteDAO dao = sqlSession.getMapper(INoteDAO.class);
		dao.writeDAO(req.getParameter("Notetitle"), req.getParameter("NoteContent"));
		return "redirect:list";
	}

	 @RequestMapping(value = "delete")
	 public String delete(HttpServletRequest req,Model model){
		INoteDAO dao = sqlSession.getMapper(INoteDAO.class);
		dao.deleteDAO(req.getParameter("id"));
		return "redirect:list";
	}
}
