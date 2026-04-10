package com.demo.csv;

public class User_POJO {

	
	private String username;
	private String password;
	
	public User_POJO() {
		
	}
	
	
	public User_POJO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User_POJO [username=" + username + ", password=" + password + "]";
	}
	
	
}
