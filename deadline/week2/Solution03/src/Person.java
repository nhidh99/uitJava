
public class Person {
	protected String name;
	protected int age;
	protected String address;

	public Person(String name, int age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
	}

	public void showInfo() {
		System.out.printf(
				"- Ho ten: %s\n" 
				+ "- Tuoi: %d\n" 
				+ "- Dia chi: %s\n",
				name, age, address);
	}
}
