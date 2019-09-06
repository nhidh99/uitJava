import java.util.Scanner;

public class RentedCarService extends Service {

	private int[] rentedHoursList;
	
	public RentedCarService() {
		this.name = "Rented Car Service";
	}
	
	@Override
	public void inputInfo() {
		@SuppressWarnings("resource")
		var scanner = new Scanner(System.in);
		System.out.println("--- RENT CAR SERVICE ---");
		
		System.out.print("- Input unit price: ");
		unitPrice = scanner.nextLong();

		System.out.print("- Input number of car(s): ");
		numUnits = scanner.nextInt();
		
		rentedHoursList = new int[numUnits];		
		for (int i = 0; i < numUnits; i++) {
			System.out.print(String.format("[Car %d] - Input rented hour(s): ", i + 1));
			rentedHoursList[i] = scanner.nextInt();
		}
	}

	@Override
	public long getPayment() {
		int totalRentedHours = 0;
		long totalPayment = 0;
		
		for (var hours : rentedHoursList) {
			totalRentedHours += hours;			
			if (hours > 6) {
				totalPayment += 95 * hours * unitPrice / 100;
			}
			else totalPayment += hours * unitPrice;
		}
		
		if (totalRentedHours > 72) {
			totalPayment = 98 * totalPayment / 100;
		}
		
		return totalPayment;
	}
}
