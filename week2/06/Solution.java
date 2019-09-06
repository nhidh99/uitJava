import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		
		// input
		var scanner = new Scanner(System.in);
		System.out.println("--- INPUT ---");
		System.out.print("+ Input number of Pedion(s): ");		
		int numPedions = scanner.nextInt();
		System.out.print("+ Input number of Zattacker(s): ");				
		int numZattacker = scanner.nextInt();
		System.out.print("+ Input number of Carrier(s): ");		
		int numCarrier = scanner.nextInt();
		scanner.close();
		
		// create list & show info
		System.out.println("\n--- INFORMATION ---");
		var robots = new RobotList(numPedions, numZattacker, numCarrier);
		robots.showInfo();
		System.out.println("+ Most used energy type: " + robots.mostUsedEnergyRobotType());
		
		// total used energy
		System.out.println("+ Total used energy: " + robots.getTotalUsedEnergy());
	}
}