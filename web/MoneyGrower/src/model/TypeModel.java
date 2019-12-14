package model;

public class TypeModel {
	private int typeID;
	private String name;
	private String icon;
	private boolean isIncome;
	
	public TypeModel(int typeID, String name, String icon, boolean isIncome) {
		this.typeID = typeID;
		this.name = name;
		this.icon = icon;
		this.isIncome = isIncome;
	}

	public int getTypeID() {
		return typeID;
	}
	
	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public boolean isIncome() {
		return isIncome;
	}
	
	public void setIncome(boolean isIncome) {
		this.isIncome = isIncome;
	}
}
