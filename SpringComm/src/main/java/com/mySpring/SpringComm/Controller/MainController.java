package com.mySpring.SpringComm.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping(value = "Index")
	public String Index(Model model) {
		return "Main";
	}
	
	@RequestMapping(value = "SignIn")
	public String SignIn(Model model) {
		return "Login/SignIn";
	}
	
	@RequestMapping(value = "SignUp")
	public String SignUp(Model model) {
		return "Login/SignUp";
	}
}
