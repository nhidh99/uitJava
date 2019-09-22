public class RobotList {
	Robot[] robots;
	int numPedions;
	int numZattacker;
	int numCarrier;

	public RobotList(int numPedions, int numZattacker, int numCarrier) {
		this.numPedions = numPedions;
		this.numCarrier = numCarrier;
		this.numZattacker = numZattacker;
		robots = new Robot[numPedions + numCarrier + numZattacker];

		int index = 0;

		for (int i = 0; i < numPedions; i++) {
			robots[index++] = new Pedion();
		}

		for (int i = 0; i < numZattacker; i++) {
			robots[index++] = new Zattacker();
		}

		for (int i = 0; i < numCarrier; i++) {
			robots[index++] = new Carrier();
		}
	}

	public void showInfo() {
		int count = 1;
		for (var robot : robots) {
			System.out.println(String.format("+ Robot %d: %s", count++, robot.getInfo()));
		}
	}

	public String mostUsedEnergyRobotType() {
		int totalPedionEnergy = 0;
		int totalZattackEnergy = 0;
		int totalCarrierEnergy = 0;
		
		for (Robot robot : robots) {
			switch (robot.getType()) {
			case "Pedion": {
				totalPedionEnergy += robot.getUsedEnergy(10);
				break;
			}

			case "Zattacker": {
				totalZattackEnergy += robot.getUsedEnergy(10);
				break;
			}

			case "Carrier": {
				totalCarrierEnergy += robot.getUsedEnergy(10);
				break;
			}
			}
		}
		
		if (totalPedionEnergy > totalZattackEnergy && totalPedionEnergy > totalCarrierEnergy) {
			return "Pedion";
		}
		
		if (totalZattackEnergy > totalPedionEnergy && totalZattackEnergy > totalCarrierEnergy) {
			return "Zattacker";
		}
		return "Carrier";
	}
	
	public double getTotalUsedEnergy() {
		int totalUsedEnergy = 0;
		for (Robot robot : robots) {
			totalUsedEnergy += robot.getUsedEnergy(10);
		}
		return totalUsedEnergy;
	}
}
