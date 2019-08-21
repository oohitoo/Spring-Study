package com.mySpring.SpringComm.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mySpring.SpringComm.DAO.LoginDAO;
import com.mySpring.SpringComm.DTO.usersDTO;

@Controller
public class LoignController {

	@Autowired
	private SqlSession sqlsession;
	
	@RequestMapping(value = "SignIn", method = RequestMethod.POST)
	public String SignIn(Model model) {
		return "SignIn";
	}
	
	@RequestMapping(value = "logout")
	public String logout(Model model, HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
		return "Main";
	}
	
	
	@RequestMapping(value = "/loginuser", method = RequestMethod.GET)
	public @ResponseBody String loginuser(HttpServletRequest req, Model model) {
		boolean flag = false;
		LoginDAO dao = sqlsession.getMapper(LoginDAO.class);
		if(dao.login(req.getParameter("userid"), req.getParameter("userpwd")) == null) {
			return "1";
			/* return flag; */
			/* return "Login/SignIn"; */
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("idKey", req.getParameter("userid"));
			flag = true;
			return "2";
			/* return flag; */
			/* return "Login/loginMain"; */
		}		
	}
	
	@RequestMapping(value = "/createuser", method = RequestMethod.POST)
	public String createuser(Model model, HttpServletRequest req, usersDTO dto) {
		LoginDAO dao = sqlsession.getMapper(LoginDAO.class);
		dto.setUserid(req.getParameter("createuserid"));
		dto.setUserpwd(req.getParameter("createuserpwd"));
		dto.setUsername(req.getParameter("createusername"));
		dto.setUseremail(req.getParameter("createuseremail"));
		dao.createDAO(dto);
		return "Main";
	}
}
