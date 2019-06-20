package com.datacrawing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.datacrawing.model.UserModel;
import com.datacrawing.service.UserService;

@Controller
@RequestMapping(path="/api")
public class UserController {
	@Autowired UserService userService;
	
	@RequestMapping(path="/user" ,method= {RequestMethod.GET})
	@ResponseBody
	public List<UserModel> getUser() {
		return userService.getUser();
	}
}
