package model;

public class UserModel {
	private int userID;
	private String username;
	private String password;
	private String name;
	private long income;
	
	private static UserModel instance;
		
	public static UserModel getInstance() {
		if (instance == null) {
			instance = new UserModel();
		}
		return instance;
	}
	
	private UserModel() {}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
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
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getIncome() {
		return income;
	}
	
	public void setIncome(long income) {
		this.income = income;
	}
}