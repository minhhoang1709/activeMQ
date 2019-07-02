package com.dataprocessing.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "tbluser")
public class UserModel implements Serializable{

	@Id
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "user_age")
	private byte age;
	
	@Column(name = "user_name")
	private String name;
	
	@Column(name = "user_gender")
	private String gender;
	
	@Column(name = "user_company")
	private String company;
	
	@Column(name = "user_email")
	private String email;
	
	@Column(name = "user_phone")
	private String phone;
	
	@Column(name = "user_address")
	private String address;
	
	@Column(name = "user_about")
	private String about;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte getAge() {
		return age;
	}

	public void setAge(byte age) {
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

	public UserModel(int id, byte age, String name, String gender, String company, String email, String phone,
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

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", age=" + age + ", name=" + name + ", gender=" + gender + ", company=" + company
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", about=" + about + "]";
	}
}
