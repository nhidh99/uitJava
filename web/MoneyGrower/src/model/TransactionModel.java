package model;

import java.time.LocalDate;

public class TransactionModel {
	private int transactionID;
	private int nameID;
	private int userID;
	private LocalDate date;
	private long price;
	private String note;
	
	public TransactionModel(int transactionID, int nameID, int userID, LocalDate date, long price, String note) {
		this.transactionID = transactionID;
		this.nameID = nameID;
		this.userID = userID;
		this.date = date;
		this.price = price;
		this.note = note;
	}

	public TransactionModel(int nameID, int userID, LocalDate date, long price, String note) {
		this.nameID = nameID;
		this.userID = userID;
		this.date = date;
		this.price = price;
		this.note = note;
	}
	
	public int getTransactionID() {
		return transactionID;
	}
	
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	
	public int getNameID() {
		return nameID;
	}
	
	public void setNameID(int nameID) {
		this.nameID = nameID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public long getPrice() {
		return price;
	}
	
	public void setPrice(long price) {
		this.price = price;
	}
	
	public String getNote() {
		return note;
	}
	
	public void setNote(String note) {
		this.note = note;
	}
}