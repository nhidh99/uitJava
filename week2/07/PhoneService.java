import java.util.Scanner;

public class PhoneService extends Service {

	private int[] rentedMinutesList;
	
	public PhoneService() {
		this.name = "Phone Service";
	}
	
	@Override
	public void inputInfo() {
		@SuppressWarnings("resource")
		var scanner = new Scanner(System.in);
		System.out.println("--- PHONE SERVICE ---");
		
		System.out.print("- Input unit price: ");
		unitPrice = scanner.nextLong();

		System.out.print("- Input number of phone call(s): ");
		numUnits = scanner.nextInt();
		
		rentedMinutesList = new int[numUnits];		
		for (int i = 0; i < numUnits; i++) {
			System.out.print(String.format("[Call %d] - Input rented minutes(s): ", i + 1));
			rentedMinutesList[i] = scanner.nextInt();
		}
	}

	@Override
	public long getPayment() {
		long totalPayment = 0;
		for (var minutes : rentedMinutesList) {
			totalPayment += minutes * unitPrice;
		}
		if (totalPayment > 300000) {
			totalPayment = 80 * (totalPayment - 300000) / 100 + 300000;
		}
		return totalPayment;
	}
}
