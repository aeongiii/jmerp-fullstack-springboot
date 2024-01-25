package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Mg_Controller {
@GetMapping("/mg")
@ResponseBody
public String Mg() {
	return "mg";
}

}
