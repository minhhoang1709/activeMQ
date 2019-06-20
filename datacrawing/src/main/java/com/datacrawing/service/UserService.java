package com.datacrawing.service;

import com.datacrawing.model.UserModel;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;


import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	public List<UserModel> getUser() {
		List<UserModel> userModel = new ArrayList<UserModel>();
		Gson gson = new Gson();
		try {
			  
			   System.out.println("Reading JSON from a file");
			   System.out.println("----------------------------");
			   
			   	BufferedReader   br = new BufferedReader(
			     new FileReader("D:\\user_data.json"));
			   
			    //convert the json string back to object
			   	Type listType = new TypeToken<ArrayList<UserModel>>(){}.getType();
			   	userModel = gson.fromJson(br, listType);
		        br.close();

		}catch (IOException e) {
			   e.printStackTrace();
		  }
		
		return userModel;
		
	}
}
