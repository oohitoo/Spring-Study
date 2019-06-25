package com.myspring.springBoard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.myspring.springBoard.command.BCommand;
import com.myspring.springBoard.command.BContentCommand;
import com.myspring.springBoard.command.BDeleteCommand;
import com.myspring.springBoard.command.BListCommand;
import com.myspring.springBoard.command.BModifyCommand;
import com.myspring.springBoard.command.BWriteCommand;

@Controller
public class BController {
	BCommand command;
	
	@RequestMapping(value = "/writeView")
	public String writeView(Model model) {
		return "writeView"; //src/main/WEB-INF/views/writeView.jsp 호출
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		command = new BListCommand();
		command.excute(model); //동적할당
		return "list";
	}
	
	@RequestMapping(value = "/write")
	public String write(HttpServletRequest req, Model model) {
		model.addAttribute("request", req);
		command = new BWriteCommand();
		command.excute(model);
		return "redirect:list";
	}
	
	//contentView �޼ҵ�(�׼�)�� ���� -> "contentView"
	@RequestMapping(value = "/contentView")
	public String contentView(HttpServletRequest req, Model model) {
		model.addAttribute("request", req);
		command = new BContentCommand();
		command.excute(model);
		return "contentView";
	}
	
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modify(HttpServletRequest req,Model model) {
		model.addAttribute("request", req);		
		command = new BModifyCommand();
		command.excute(model);
		return "redirect:list";
	}
	
	//delete
	@RequestMapping(value = "delete")
	public String delete(HttpServletRequest req, Model model) {
		model.addAttribute("request", req);
		command = new BDeleteCommand();
		command.excute(model);
		return "redirect:list";
	}
}
