package Shape2D;

public class Rectangle extends Shape {
	protected Point2D[] points;
	
	public Rectangle() {
		points = new Point2D[4];
		for (int i = 0; i < 4; i++) {
			points[i] = new Point2D();
		}
	}
	
	public Point2D[] getPoints() {
		return points;
	}

	public void setPoints(Point2D[] points) {
		this.points = points;
	}

	@Override
	public void inputInfo() {
		System.out.println("[Please input points by clockwise]");
		for (int i = 0; i < 4; i++) {
			System.out.println(String.format("* Point %d *", i + 1));
			points[i] = new Point2D();
			points[i].inputInfo();
		}
	}

	@Override
	public String getInfo() {
		var output = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			var info = String.format("\n+ Point %d: %s", i + 1, points[i].getInfo());
			output.append(info);
		}
		return output.toString();
	}

	@Override
	public double getPerimeter() {
		double length1 = points[0].distanceTo(points[1]);
		double length2 = points[0].distanceTo(points[3]);		
		return 2 * (length1 + length2);
	}

	@Override
	public double getSurface() {
		double length1 = points[0].distanceTo(points[1]);
		double length2 = points[0].distanceTo(points[3]);		
		return length1 * length2;
	}
}
