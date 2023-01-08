package com.emlakcepte.dto.user.request;

public class UserLogin {

	private String email;
	private String password;
	
	public UserLogin() {}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	} 
	
}
