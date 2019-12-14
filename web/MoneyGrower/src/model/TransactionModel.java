package model;

import java.time.LocalDate;

public class TransactionModel {
	private int transactionID;
	private int userID;
	private TypeModel type;
	private LocalDate date;
	private long price;
	private String note;
	
	public TransactionModel(int transactionID, int userID, TypeModel type, LocalDate date, long price, String note) {
		this.transactionID = transactionID;
		this.userID = userID;
		this.type = type;
		this.date = date;
		this.price = price;
		this.note = note;
	}

	public TransactionModel(int userID, TypeModel type, LocalDate date, long price, String note) {
		this.userID = userID;
		this.type = type;
		this.date = date;
		this.price = price;
		this.note = note;
	}

	public TypeModel getType() {
		return type;
	}

	public void setType(TypeModel type) {
		this.type = type;
	}

	public int getTransactionID() {
		return transactionID;
	}
	
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
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