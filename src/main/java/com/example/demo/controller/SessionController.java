package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.form.LoginForm;

@Controller
public class SessionController {
	@RequestMapping(path = "/loginUsingForm", method = RequestMethod.GET)
	public String login() {
		return "session/loginUsingForm";
	}
	
	@RequestMapping(path = "/doLogin", method = RequestMethod.GET)
	public String doLoginGet(String userId) {
		System.out.println("ユーザID" + userId);
		return "session/login";
	}
	
	@PostMapping(path="/doLoginUsingForm")
	public String doLoginPost(LoginForm form) {
		System.out.println("ユーザID：" + form.getUserId());
		return "session/loginUsingForm";
	}
}
