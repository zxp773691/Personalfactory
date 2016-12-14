package edu.yuhf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class InitController {
	@RequestMapping("/init")
	public String login(){
		return "login";
	}


	
}
