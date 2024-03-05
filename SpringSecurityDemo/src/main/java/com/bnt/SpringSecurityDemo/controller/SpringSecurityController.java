package com.bnt.SpringSecurityDemo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class SpringSecurityController {

	@GetMapping("/home")
	public String getHome() {
		return "Welcome to home page";
	}

	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public String getUser() {
		return "Welcome to user page";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String getAdmin() {
		return "Welcome to admin page";

	}

}
