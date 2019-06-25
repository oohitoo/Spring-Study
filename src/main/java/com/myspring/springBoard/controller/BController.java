package com.myspring.springBoard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myspring.springBoard.command.BCommand;
import com.myspring.springBoard.command.BContentCommand;
import com.myspring.springBoard.command.BListCommand;
import com.myspring.springBoard.command.BWriteCommand;

@Controller
public class BController {
	BCommand command;
	
	@RequestMapping(value = "/writeView")
	public String writeView(Model model) {
		return "writeView"; //src/main/WEB-INF/views/writeView.jsp ȣ��
	}
	
	@RequestMapping(value = "/list")
	public String list(Model model) {
		System.out.println("1");
		command = new BListCommand();
		command.excute(model); //���� �Ҵ�
		return "list";
	}
	
	@RequestMapping(value = "/write")
	public String write(HttpServletRequest req, Model model) {
		model.addAttribute("request", req);
		command = new BWriteCommand();
		command.excute(model);
		return "reirect:list";
	}
	
	//contentView �޼ҵ�(�׼�)�� ���� -> "contentView"
	@RequestMapping(value = "/contentView")
	public String contentView(HttpServletRequest req, Model model) {
		model.addAttribute("request", req);
		command = new BContentCommand();
		command.excute(model);
		return "contentView";
		
	}
}
