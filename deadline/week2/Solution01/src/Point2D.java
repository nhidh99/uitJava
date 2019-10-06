import java.util.Scanner;

public class Point2D {

	private int x;
	private int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void input(Scanner in) {
		System.out.print("- Nhap x: ");
		x = in.nextInt();
		System.out.print("- Nhap y: ");
		y = in.nextInt();
	}

	public void translate(int x, int y) {
		this.x += x;
		this.y += y;
	}
	
	public String toString() {
		return String.format("[%d, %d]", x, y);
	}

	public double distanceTo(Point2D other) {
		return Math.sqrt(Math.pow(x - other.x, 2) + Math.pow(y - other.y, 2));
	}
}