import java.util.HashMap;

public class ServiceList {
	Service[] services;
	
	public ServiceList() {
		services = new Service[3];
		services[0] = new RentedCarService();
		services[1] = new PhoneService();
		services[2] = new FlowerService();
	}
	
	public void inputInfo() {
		for (var service : services) {
			service.inputInfo();
			System.out.println();
		}
	}
	
	public String getHighestPaymentService() {
		int index = 0;
		for (int i = 1; i < 3; i++) {
			if (services[index].getPayment() < services[i].getPayment()) {
				index = i;
			}
		}
		return services[index].getName();
	}
	
	public HashMap<String, Long> getAllPayments() {
		var payments = new HashMap<String, Long>();
		for (var service : services) {
			payments.put(service.getName(), service.getPayment());
		}
		return payments;
	}
}
