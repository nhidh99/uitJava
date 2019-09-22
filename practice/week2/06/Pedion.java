
public class Pedion extends Robot {
	private int ability;
	
	public Pedion() {
		this.type = "Pedion";
		this.weight = 20;
		this.ability = (int)(Math.random() * 5) + 1;
	}
	
	@Override
	public String getInfo() {
		return String.format("[type = '%s', weight = %dkg, ability = %d, usedEnergy = %.1f]" ,
				type, weight, ability, getUsedEnergy(10));
	}

	@Override
	public double getUsedEnergy(int distance) {
		// M * S + (F + 1) * S / 2
		return weight * distance + (ability + 1) * distance / 2;
	}

	public int getAbility() {
		return ability;
	}
}
