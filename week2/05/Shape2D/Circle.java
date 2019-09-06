package Shape2D;

import java.util.Scanner;

public class Circle extends Shape {
	private Point2D center;
	private double radius;

	public Point2D getCenter() {
		return center;
	}

	public void setCenter(Point2D center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	@SuppressWarnings("resource")
	@Override
	public void inputInfo() {
		System.out.println("* Center *");
		center = new Point2D();
		center.inputInfo();
		System.out.print("Input radius: ");
		radius = new Scanner(System.in).nextDouble();
	}

	@Override
	public String getInfo() {
		return String.format("\n+ Center: %s\n+ Radius: %f", center.getInfo(), radius);
	}

	@Override
	public double getPerimeter() {
		return 2 * Math.PI * radius;
	}

	@Override
	public double getSurface() {
		return Math.PI * Math.pow(radius, 2);
	}

	@Override
	public boolean isContainPoint(Point2D point) {		
		return point.isInsideCircle(center, radius);
	}
}
