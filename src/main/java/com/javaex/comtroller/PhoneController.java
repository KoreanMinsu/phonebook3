package com.javaex.comtroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PhoneController {
	//field
	//constructor
	//method-g/s
	//method-generic
	@RequestMapping(value ="/test")
	
	public String test() {
		System.out.println("test");
		
		return "WEB-INF/views/test.jsp";
	}
	 
}
