
public class Employee extends Person implements Measurable {
	
	int workHour;
	double salaryPerHour;
	
	public Employee(String name, String id, int workHour, double salaryPerHour) {
		super(name, id);
		this.workHour = workHour;
		this.salaryPerHour = salaryPerHour;
	}
	
	public double valuate() {
		return workHour * salaryPerHour;
	}
}