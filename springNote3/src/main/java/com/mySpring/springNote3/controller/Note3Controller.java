package com.mySpring.springNote3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mySpring.springNote3.DAO.NoteDAO;

import lombok.Setter;

@Controller
public class Note3Controller {
	
	@Setter
	@Autowired
	private NoteDAO dao;
	
	@RequestMapping(value = "/form")
	public String form(Model model) {
		return "form";
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		model.addAttribute("noteList", dao.list());
		return "list";
	}
	
	@RequestMapping(value = "/writerForm")
	public String writerform(Model model) {
		return "writer";
	}
	
	@RequestMapping(value = "/write")
	public String writer(String writer, String content) {
		dao.write(writer, content);
		return "redirect:list";
	}
	
	@RequestMapping(value = "/delete")
	public String delete(int id) {
		dao.delete(id);
		return "redirect:list";
	}
}
