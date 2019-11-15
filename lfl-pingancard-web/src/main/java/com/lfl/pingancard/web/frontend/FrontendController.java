package com.lfl.pingancard.web.frontend;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("frontend")
public class FrontendController {
	@GetMapping( "login")
	public String showMainPage() {
        System.out.println("执行了");
		return "hello11";
	}


}
