package com.dataprocessing.servicce;

import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dataprocessing.messaging.CrawingConsumer;
import com.dataprocessing.messaging.Producer;
import com.dataprocessing.model.UserModel;

@Service
public class SearchingService {
	
	@Autowired Producer producer; 
	@Autowired CrawingConsumer crawingconsumer; 
	
	public UserModel UserSearchingById(int userId,List<UserModel> userList) {
		UserModel userModel = new UserModel();
		for(UserModel item:userList) {
			if(item.getId()==userId)
				userModel = item;
		}
		return userModel;
	}
	
	public UserModel UserSearchingByName(String name) {
		UserModel userModel = new UserModel();
		
		return userModel;
	}
	
	public void sendUserName(String userName) {
		producer.sendMessagev2(userName, "crawing.queue");
	}
}