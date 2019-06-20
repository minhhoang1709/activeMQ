package com.dataprocessing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dataprocessing.model.UserModel;
import com.dataprocessing.servicce.SearchingService;

@Controller
public class SearchingController {
	@Autowired SearchingService searchingService;
	
	@RequestMapping(path="/user" ,method= {RequestMethod.GET})
	@ResponseBody
	public UserModel UserSearchingById(int userId, List<UserModel> userList) {
		return searchingService.UserSearchingById(userId, userList);
	}
}
