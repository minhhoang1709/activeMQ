package com.elasticsearchserver.model;

import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "users", type ="userModel", shards = 1)
public class UserModel {
	private int id;
	private String userName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public UserModel(int id, String userName) {
		this.id = id;
		this.userName = userName;
	}
	public UserModel() {
	}
	
}
