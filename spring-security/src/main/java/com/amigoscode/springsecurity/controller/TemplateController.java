package com.amigoscode.springsecurity.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class TemplateController {

	@GetMapping("login")
	public String getLoginView(){
		return "login";
	}

	@GetMapping("courses")
	public String getCourses(){
		return "courses";
	}
}
