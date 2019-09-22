package Shape2D;

public class Square extends Rectangle {
	@Override
	public double getPerimeter() {
		double length = points[0].distanceTo(points[1]);	
		return 4 * length;
	}

	@Override
	public double getSurface() {
		double length = points[0].distanceTo(points[1]);	
		return Math.pow(length, 2);
	}
}
