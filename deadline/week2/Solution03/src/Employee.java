
public class Employee extends Person {
	int defaultSalary;
	int coefficient;
	
	public Employee(String name, int age, String address, int defaultSalary, int coefficient) {
		super(name, age, address);
		this.defaultSalary = defaultSalary;
		this.coefficient = coefficient;
	}
	
	public int getSalary() {
		return defaultSalary * coefficient;
	}
}