package com.journaldev.elasticsearch.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.journaldev.elasticsearch.bean.User;
import com.journaldev.elasticsearch.dao.UserDao;

@RestController
@RequestMapping("/user")
public class UserController {
	private UserDao userDao;

	public UserController(UserDao userDao) {
		this.userDao = userDao;
	}

	@GetMapping("/name")
	public Map<String, Object> getUserByName(@RequestParam(value = "name") String name) {
		return userDao.getUserByName(name);
	}
	
	@GetMapping("/id/{id}")
	public Map<String, Object> getUserById(@PathVariable String id) {
		return userDao.getUserById(id);
	}
	
//    @GetMapping(value = "/name-search")
//    public List<User> searchByName(@RequestParam(value = "name") String name) throws Exception {
//        return userDao.searchByName(name);
//    }
    
    @GetMapping
    public List<User> findAll() throws Exception {

        return userDao.findAll();
    }
    
    @GetMapping(value = "/namesearch")
    public List<User> findByName(@RequestParam(value = "name") String name) throws Exception {
        return userDao.findByName(name);
    }
    
    @GetMapping(value = "/aboutsearch")
    public List<User> findByAbout(@RequestParam(value = "about") String about) throws Exception {
        return userDao.findByAbout(about);
    }
	
}
