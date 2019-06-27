package com.mySpring.springBoard2.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.mySpring.springBoard2.DAO.Board2InterFace;

import jdk.internal.jline.internal.Log;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

@Controller
public class Board2Controller {
	
	@Autowired
	private SqlSession sqlsession;
	
	@RequestMapping(value = "/boardList")
	public String boardList(Model model) {
		Board2InterFace dao = sqlsession.getMapper(Board2InterFace.class);
		model.addAttribute("boardlist", dao.boardlist());
		return "boardList";
	}
	
	@RequestMapping(value = "/writeView")
	public String writeView(Model model) {
		return "writeView";
	}
	
	@RequestMapping(value = "/write")
	public String write(HttpServletRequest req,Model model) {
		Board2InterFace dao = sqlsession.getMapper(Board2InterFace.class);
		dao.writeBoard(req.getParameter("boardname"), req.getParameter("boardtitle"), req.getParameter("boardcontent"));
		return "redirect:boardList";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(HttpServletRequest req,Model model) {
		Board2InterFace dao = sqlsession.getMapper(Board2InterFace.class);
		dao.deleteBoard(req.getParameter("id"));
		return "redirect:boardList";
	}
	
	@RequestMapping(value = "/read")
	public String read(HttpServletRequest req,Model model) {
		Board2InterFace dao = sqlsession.getMapper(Board2InterFace.class);
		dao.hitcount(req.getParameter("id"));
		model.addAttribute("readContent", dao.readContent(req.getParameter("id")));
		return "read";
	}
	
	@RequestMapping(value = "/update")
	public String update(HttpServletRequest req,Model model) {
		Board2InterFace dao = sqlsession.getMapper(Board2InterFace.class);
		dao.updateContent(req.getParameter("boardname"), req.getParameter("boardtitle"), req.getParameter("boardcontent"),req.getParameter("boardid"));
		return "redirect:boardList";
	}
	
	@RequestMapping(value = "/fileload")
	public String fileList(Model model) {
		return "fileload";
	}
}
