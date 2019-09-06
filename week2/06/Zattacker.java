
public class Zattacker extends Robot {
	private int power;
	
	public Zattacker() {
		this.type = "Zattacker";
		this.weight = 50;
		this.power = (int)(Math.random() * 11) + 20;
	}
	
	@Override
	public String getInfo() {
		return String.format("[type = '%s', weight = %dkg, power = %d, usedEnergy = %.1f]" ,
				type, weight, power, getUsedEnergy(10));
	}

	@Override
	public double getUsedEnergy(int distance) {
		// M * S + P * P * S
		return weight * distance + power * power * distance;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}
}
