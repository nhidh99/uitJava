package Shape2D;
import java.util.Scanner;

public class Point2D {
	private double x;
	private double y;
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public void setX(double newX) {
		this.x = newX;
	}
	
	public void setY(double newY) {
		this.y = newY;
	}
	
	public void setPoint(double newX, double newY) {
		this.x = newX;
		this.y = newY;
	}
	
	@SuppressWarnings("resource")
	public void inputInfo() {
		var scanner = new Scanner(System.in);
		System.out.print("Input x: ");
		x = scanner.nextDouble();
		System.out.print("Input y: ");
		y = scanner.nextDouble();
	}
	
	public String getInfo() {
		return String.format("[x = %f, y = %f]", x, y);
	}
	
	public double distanceTo(Point2D other) {
		return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
	}
}
