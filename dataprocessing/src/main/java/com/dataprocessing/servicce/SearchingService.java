package com.dataprocessing.servicce;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dataprocessing.model.UserModel;

@Service
public class SearchingService {
	public UserModel UserSearchingById(int userId,List<UserModel> userList) {
		UserModel userModel = new UserModel();
		for(UserModel item:userList) {
			if(item.getUserId()==userId)
				userModel = item;
		}
		return userModel;
	}
}
