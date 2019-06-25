package com.journaldev.elasticsearch.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.journaldev.elasticsearch.dao.UserDao;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserDao userDao;

	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}

	@GetMapping("/firstname/{firstname}")
	public Map<String, Object> getUserByFirstname(@PathVariable String firstname) {
		return userDao.getUserByFirstname(firstname);
	}
	
	@GetMapping("/id/{id}")
	public Map<String, Object> getUserById(@PathVariable String id) {
		return userDao.getUserById(id);
	}
	
	@GetMapping("/state/{state}")
	public Map<String, Object> getUserByAccountState(@PathVariable String state) {
		return userDao.getUserByAccountState(state);
	}
}
