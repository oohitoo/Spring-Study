package com.cos.controller;

import java.io.PrintWriter;
import java.util.Locale;

import javax.inject.Inject;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cos.domain.UserVO;
import com.cos.service.CategoryService;
import com.cos.service.UserService;

@Controller
public class UserController {

	@Inject
	private CategoryService ctService;
	@Inject
	private UserService userService;


	@RequestMapping(value = "/userLoginForm", method = RequestMethod.GET)
	public String userLoginForm(Model model) throws Exception{
		model.addAttribute("ctg1", ctService.selectGubun1(1));
		model.addAttribute("ctg2", ctService.selectGubun1(2));
		model.addAttribute("ctg3", ctService.selectGubun1(3));
		model.addAttribute("ctg4", ctService.selectGubun1(4));
		return "user/userLoginForm";
	}

	@RequestMapping(value = "/userJoinForm", method = RequestMethod.GET)
	public String userJoinForm(Model model, Locale locale) throws Exception{
		model.addAttribute("ctg1", ctService.selectGubun1(1));
		model.addAttribute("ctg2", ctService.selectGubun1(2));
		model.addAttribute("ctg3", ctService.selectGubun1(3));
		model.addAttribute("ctg4", ctService.selectGubun1(4));
		return "user/userJoinForm";
	}

	@RequestMapping(value = "/userJoin", method = RequestMethod.POST)
	public String userJoin(UserVO userVo) throws Exception{
		userService.insert(userVo);
		return "redirect:index";
	}

	@RequestMapping(value = "/userLogin", method = RequestMethod.POST)
	public String userLogin(UserVO user,HttpServletRequest req, HttpServletResponse response) throws Exception {
		String userID = req.getParameter("userID");
		String userPW = req.getParameter("userPW");
		int flag = userService.login(user);
		HttpSession session = req.getSession();
		if(flag == 1) {
			user = userService.select(userID);
			session.setAttribute("userID", userID);
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("userEmail", user.getUserEmail());
			return "redirect:index";
		}
		else {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 정보를 확인해주세요.');</script>");
			out.flush();
		}	
		return "user/userLoginForm";
	}

	@RequestMapping(value = "/userLogout", method = RequestMethod.GET)
	public String userLogout(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "redirect:index";
	}

}
