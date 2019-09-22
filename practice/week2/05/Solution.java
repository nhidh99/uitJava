import Shape2D.*;

public class Solution {
	public static void main(String[] args) {
		// input & show information
		var shapeFactory = new ShapeFactory();
		shapeFactory.inputInfo();
		shapeFactory.showInfo();

		// count shapes
		System.out.println("\n--- COUNTING SHAPES ---");
		var shapeCounts = shapeFactory.getShapeCount();
		for (var key : shapeCounts.keySet()) {
			System.out.println(String.format("- Number of %s(s): %d", key, shapeCounts.get(key)));
		}

		// get shapes contain point
		System.out.println("\n--- CHECK SHAPES CONTAIN POINT ---");
		System.out.println("[INPUT POINT]");
		var point = new Point2D();
		point.inputInfo();

		System.out.println("\n[RESULT]");
		var shapes = shapeFactory.getShapes();
		int count = 1;
		for (Shape shape : shapes) {
			System.out.println(String.format("- Shape %d: %b", count++, shape.isContainPoint(point)));
		}
	}
}