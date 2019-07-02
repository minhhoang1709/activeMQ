package com.dataprocessing.controller;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dataprocessing.messaging.CrawingConsumer;
import com.dataprocessing.model.UserModel;
import com.dataprocessing.servicce.CrawingService;
import com.dataprocessing.servicce.SearchingService;

@Controller
public class SearchingController {
	@Autowired SearchingService searchingService;
	@Autowired CrawingConsumer consumer;
	@Autowired
	CrawingService crawingService;
	
	@RequestMapping(path="/sendmesssagev2" ,method= {RequestMethod.POST})
	@ResponseBody
	public String sendUserName(@RequestParam(value = "userName") String userName) throws URISyntaxException, Exception {
		searchingService.sendUserName(userName);
		
		return("Searching\t" + userName);
		
	}
	
//	@RequestMapping(path="/user" ,method= {RequestMethod.GET})
//	@ResponseBody
//	public UserModel UserSearchingByName(@RequestParam(value = "id") int id) {
//		return crawingService.insertUserList(id);
//	}
}