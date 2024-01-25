package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HR_controller {

	@GetMapping("/list")
	@ResponseBody
	public String list() {
		return "list333";
	}
}
