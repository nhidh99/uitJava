public abstract class Robot {
	protected String type;
	protected int weight;
	
	public String getType() {
		return type;
	}

	public int getWeight() {
		return weight;
	}
	
	public abstract String getInfo();
	public abstract double getUsedEnergy(int distance);
}