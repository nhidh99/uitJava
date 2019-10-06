
public abstract class Person {
	protected String name;
	protected String id;
	
	public Person(String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public String toString() {
		return String.format(
				"- Ho ten: %s\n"
				+ "- Ma: %s\n", 
				name, id);
	}
}
