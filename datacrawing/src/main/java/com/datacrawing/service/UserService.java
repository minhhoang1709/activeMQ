package com.datacrawing.service;

import com.datacrawing.messaging.Producer;
import com.datacrawing.model.UserModel;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	Producer producer;

	public List<UserModel> getUser(String userName) {
		List<UserModel> userModel = new ArrayList<UserModel>();
		Gson gson = new Gson();
		try {

			System.out.println("Reading JSON from a file");
			System.out.println("----------------------------");

			BufferedReader br = new BufferedReader(new FileReader("D:\\user_data.json"));

			// convert the json string back to object
			Type listType = new TypeToken<ArrayList<UserModel>>() {
			}.getType();
			userModel = gson.fromJson(br, listType);

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return userModel;
	}

	public void processUser(String userName) {
		String message = "";
		try {

			System.out.println("Reading JSON from a file");
			System.out.println("----------------------------");
			BufferedReader br = new BufferedReader(new FileReader("D:\\user_data.json"));
			message = org.apache.commons.io.IOUtils.toString(br);

			br.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		producer.sendMessagev2(message);
	}

}
