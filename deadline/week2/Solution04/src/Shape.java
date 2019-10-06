
public abstract class Shape {
	protected String name;
	
	public abstract double getSurface();
	
	public String toString() {
		double surface = this.getSurface();
		return String.format(
				"- Loai hinh: %s\n"
				+ "- Dien tich: %.2f",
				name, surface);
	}
}