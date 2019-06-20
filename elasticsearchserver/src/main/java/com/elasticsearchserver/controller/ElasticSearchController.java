package com.elasticsearchserver.controller;

import com.elasticsearchserver.model.UserModel;
import com.elasticsearchserver.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/rest/search")
public class ElasticSearchController {
	   @Autowired
	   UserRepository userRepository;
	   
	   @GetMapping(value = "/name/{text}")
	    public List<UserModel> searchUserName(@PathVariable final String text) {
	        return userRepository.findByUserName(text);
	    }


	    @GetMapping(value = "/id/{id}")
	    public List<UserModel> searchUserId(@PathVariable final int id) {
	        return userRepository.findById(id);
	    }


	    @GetMapping(value = "/all")
	    public List<UserModel> searchAll() {
	        List<UserModel> usersList = new ArrayList<>();
	        Iterable<UserModel> userses = userRepository.findAll();
	        userses.forEach(usersList::add);
	        return usersList;
	    }
}
