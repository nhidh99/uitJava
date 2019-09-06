package Shape2D;

public class Triangle extends Shape {
	private Point2D[] points;
	
	public Triangle() {
		points = new Point2D[3];
		for (int i = 0; i < 3; i++) {
			points[i] = new Point2D();
		}
	}
	
	@Override
	public void inputInfo() {
		for (int i = 0; i < 3; i++) {
			System.out.println(String.format("* Point %d *", i + 1));
			points[i] = new Point2D();
			points[i].inputInfo();
		}
	}

	@Override
	public String getInfo() {
		var output = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			var info = String.format("\n+ Point %d: %s", i + 1, points[i].getInfo());
			output.append(info);
		}
		return output.toString();
	}

	@Override
	public double getPerimeter() {
		double length1 = points[0].distanceTo(points[1]);
		double length2 = points[0].distanceTo(points[2]);
		double length3 = points[1].distanceTo(points[2]);
		return length1 + length2 + length3;
	}

	@Override
	public double getSurface() {
		double length1 = points[0].distanceTo(points[1]);
		double length2 = points[0].distanceTo(points[2]);
		double length3 = points[1].distanceTo(points[2]);
		double halfPerimeter = (length1 + length2 + length3) / 2;
		return Math.sqrt(halfPerimeter * (halfPerimeter - length1)
				* (halfPerimeter - length2) * (halfPerimeter - length3));
	}

	public Point2D[] getPoints() {
		return points;
	}

	public void setPoints(Point2D[] points) {
		this.points = points;
	}

	@Override
	public boolean isContainPoint(Point2D point) {
		return point.isInsidePolygon(points);
	}
}
