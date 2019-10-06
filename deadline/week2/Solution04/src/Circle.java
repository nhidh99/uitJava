
public class Circle extends Shape {
	double radius;

	public Circle(double radius) {
		this.name = "Hinh tron";
		this.radius = radius;
	}

	@Override
	public double getSurface() {		
		return Math.PI * Math.pow(radius, 2);
	}
}
