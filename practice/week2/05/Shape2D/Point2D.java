package Shape2D;

import java.util.Scanner;

public class Point2D {
	private double x;
	private double y;

	public Point2D() {
	}

	public Point2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

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

	// Given three colinear Point2Ds p, q, r, the function checks if
	// Point2D q lies on line segment 'pr'
	private static boolean onSegment(Point2D p, Point2D q, Point2D r) {
		return q.getX() <= Math.max(p.getX(), r.getX()) && q.getX() >= Math.min(p.getX(), r.getX())
				&& q.getY() <= Math.max(p.getY(), r.getY()) && q.getY() >= Math.min(p.getY(), r.getY());
	}

	// To find orientation of ordered triplet (p, q, r).
	// The function returns following values
	// 0 --> p, q and r are colinear
	// 1 --> Clockwise
	// 2 --> Counterclockwise
	private static int orientation(Point2D p, Point2D q, Point2D r) {
		double val = ((q.getY() - p.getY()) * (r.getX() - q.getX()) - (q.getX() - p.getX()) * (r.getY() - q.getY()));
		if (val == 0)
			return 0; // colinear
		return (val > 0) ? 1 : 2; // clock or counterclock wise
	}

	// The function that returns true if line segment 'p1q1'
	// and 'p2q2' intersect.
	private static boolean isIntersect(Point2D p1, Point2D q1, Point2D p2, Point2D q2) {
		// Find the four orientations needed for general and
		// special cases
		int o1 = orientation(p1, q1, p2);
		int o2 = orientation(p1, q1, q2);
		int o3 = orientation(p2, q2, p1);
		int o4 = orientation(p2, q2, q1);

		// General case
		if (o1 != o2 && o3 != o4)
			return true;

		// Special Cases
		// p1, q1 and p2 are colinear and p2 lies on segment p1q1
		if (o1 == 0 && onSegment(p1, p2, q1))
			return true;

		// p1, q1 and p2 are colinear and q2 lies on segment p1q1
		if (o2 == 0 && onSegment(p1, q2, q1))
			return true;

		// p2, q2 and p1 are colinear and p1 lies on segment p2q2
		if (o3 == 0 && onSegment(p2, p1, q2))
			return true;

		// p2, q2 and q1 are colinear and q1 lies on segment p2q2
		if (o4 == 0 && onSegment(p2, q1, q2))
			return true;

		return false; // Doesn't fall in any of the above cases
	}

	// Returns true if the Point2D p lies inside the polygon[] with n vertices
	public boolean isInsidePolygon(Point2D polygon[]) {
		// There must be at least 3 vertices in polygon[]
		int n = polygon.length;

		// Create a Point2D for line segment from p to infinite
		Point2D extreme = new Point2D(Double.POSITIVE_INFINITY, y);

		// Count intersections of the above line with sides of polygon
		int count = 0, i = 0;
		do {
			int next = (i + 1) % n;

			// Check if the line segment from 'p' to 'extreme' intersects
			// with the line segment from 'polygon[i]' to 'polygon[next]'
			if (isIntersect(polygon[i], polygon[next], this, extreme)) {
				// If the Point2D 'p' is colinear with line segment 'i-next',
				// then check if it lies on segment. If it lies, return true,
				// otherwise false
				if (orientation(polygon[i], this, polygon[next]) == 0)
					return onSegment(polygon[i], this, polygon[next]);
				count++;
			}
			i = next;
		} while (i != 0);

		// Return true if count is odd, false otherwise
		return count % 2 == 1;
	}
	
	public boolean isInsideCircle(Point2D center, double radius) {
		return this.distanceTo(center) <= radius;
	}
}