
public class Rectangle extends Shape {
	double length;
	double width;
	
	public Rectangle(double length, double width) {
		this.name = "Hinh chu nhat";
		this.length = length;
		this.width = width;
	}
	
	@Override
	public double getSurface() {
		return width * length;
	}
}
