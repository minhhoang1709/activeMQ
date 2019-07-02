package com.dataprocessing.servicce;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import com.dataprocessing.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataprocessing.model.UserModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Service
public class CrawingService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<UserModel> convertStringToUserList(String jsonString){
		System.out.println("String\n"+jsonString);
		Type listType = new TypeToken<ArrayList<UserModel>>() {}.getType();
		ArrayList<UserModel> users = new Gson().fromJson(jsonString, listType);
		return users;
	}
	
	public void insertUserList(List<UserModel> users) {	
		for(UserModel item:users) {
			userRepository.save(item);
		}
		
	}
}