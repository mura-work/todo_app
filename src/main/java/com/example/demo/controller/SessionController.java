package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.form.LoginForm;

@Controller
public class SessionController {
	@RequestMapping(path = "/loginOnRequest", method = RequestMethod.GET)
	public String loginOnRequest() {
		return "session/loginOnRequest";
	}
	
	@RequestMapping(path = "/doLogin", method = RequestMethod.GET)
	public String doLoginGet(String userId) {
		System.out.println("ユーザID" + userId);
		return "session/login";
	}
	
	@PostMapping(path="/doLoginOnRequest")
	public String doLoginOnRequest(LoginForm form, Model model) {
		model.addAttribute("userId", form.getUserId());
		return "session/LoginOnRequest";
	}
	
	@RequestMapping(path="/loginOnSession", method = RequestMethod.GET)
	public String loginOnSession() {
		return "session/loginOnSession";
	}
	
	@PostMapping(path="/doLoginOnSession")
	public String doLoginOnSession(LoginForm form, HttpSession session) {
		if (form.getUserId() == 123) {
			session.setAttribute("userId", form.getUserId());
			return "redirect:/";
		}else {
			return "session/loginOnSession";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
}





