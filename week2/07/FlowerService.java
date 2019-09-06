import java.util.Scanner;

public class FlowerService extends Service {

	public FlowerService() {
		this.name = "Flower Service";
	}
	
	@Override
	public void inputInfo() {
		@SuppressWarnings("resource")
		var scanner = new Scanner(System.in);
		System.out.println("--- FLOWER DELIVERY SERVICE ---");
		
		System.out.print("- Input unit price: ");
		unitPrice = scanner.nextLong();

		System.out.print("- Input number of flower deliveries: ");
		numUnits = scanner.nextInt();
	}

	@Override
	public long getPayment() {
		return unitPrice * numUnits;
	}
}
