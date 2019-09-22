public class Solution {
	public static void main(String[] args) {
		var services = new ServiceList();
		services.inputInfo();
		
		System.out.println("[Service with highest payment]: " + services.getHighestPaymentService());
		System.out.println("\n--- PAYMENT ---");
		var payments = services.getAllPayments();
		for (var key : payments.keySet()) {
			System.out.println(String.format("+ %s payment: %d", key, payments.get(key)));
		}
		
		int totalPayments = 0;
		for (var payment : payments.values()) {
			totalPayments += payment;
		}
		System.out.println("=> Total payment: " + totalPayments);
	}
}