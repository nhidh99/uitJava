
public class Carrier extends Robot {
	private int energy;
	
	public Carrier() {
		this.type = "Carrier";
		this.weight = 30;
		this.energy = (int)(Math.random() * 51) + 50;
	}
	
	@Override
	public String getInfo() {
		return String.format("[type = '%s', weight = %dkg, energy = %d, usedEnergy = %.1f]", 
				type, weight, energy, getUsedEnergy(10));
	}

	@Override
	public double getUsedEnergy(int distance) {
		// M * S + 4 * E * S
		return weight * distance + 4 * energy * distance;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public void setEnergy(int energy) {
		this.energy = energy;
	}
}
