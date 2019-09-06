public class Solution {
	public static void main(String[] args) {
		var pointA = new Point2D();
		var pointB = new Point2D();
		
		// input
		System.out.println("--- Input Point A ---");
		pointA.inputInfo();
		System.out.println("--- Input Point B ---");
		pointB.inputInfo();
		
		// show information
		System.out.println("--- Information ---");		
		System.out.print("+ Point A: ");
		System.out.println(pointA.getInfo());
		System.out.print("+ Point B: ");
		System.out.println(pointB.getInfo());
		
		// show distance
		var distanceAB = pointA.distanceTo(pointB);
		System.out.println("+ Distance: " + distanceAB);
	}
}