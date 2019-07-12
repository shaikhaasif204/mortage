package com.hcl.mortageq.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mortageq.service.UserService;

@RestController
@RequestMapping(value="/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
}
