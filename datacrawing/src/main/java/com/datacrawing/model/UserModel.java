package com.datacrawing.model;

import java.io.Serializable;

public class UserModel implements Serializable {
	
	private String id;
	private int age;
	private String name;
	private String gender;
	private String company;
	private String email;
	private String phone;
	private String address;
	private String about;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public UserModel(String id, int age, String name, String gender, String company, String email, String phone,
			String address, String about) {
		this.id = id;
		this.age = age;
		this.name = name;
		this.gender = gender;
		this.company = company;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.about = about;
	}

	public UserModel() {
	}
}
