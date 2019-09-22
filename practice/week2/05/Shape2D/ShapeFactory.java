package Shape2D;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ShapeFactory {
	private ArrayList<Shape> shapes;

	private Shape createShape(int id) {
		switch (id) {
		case 1:
			return new Triangle();
		case 2:
			return new Rectangle();
		case 3:
			return new Square();
		case 4:
			return new Circle();
		default:
			return null;
		}
	}
	
	public ShapeFactory() {
		shapes = new ArrayList<Shape>();
	}
	
	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	@SuppressWarnings("resource")
	public void inputInfo() {
		var scanner = new Scanner(System.in);
		int shapeID = 0;
		do {
			System.out.println("--- INPUT SHAPES ---");
			System.out.println("1. Triangle" + "\n2. Rectangle" + "\n3. Square" + "\n4. Circle" + "\n0. EXIT!");
			System.out.print("Choose your shape: ");

			shapeID = scanner.nextInt();
			var shape = createShape(shapeID);
			if (shape != null) {
				var name = shape.getClass().getSimpleName().toUpperCase();
				System.out.println("\n- INPUT " + name + " -");
				shape.inputInfo();
				shapes.add(shape);
			}
			System.out.println();
		} while (shapeID != 0);
	}

	public void showInfo() {
		System.out.println("--- INFORMATION ---");
		int count = 1;
		for (Shape shape : shapes) {
			var name = shape.getClass().getSimpleName();
			System.out.println(String.format("[Shape %d]", count++));
			System.out.println(String.format("- Type: %s", name));
			System.out.println(String.format("- Perimeter = %f", shape.getPerimeter()));
			System.out.println(String.format("- Surface = %f", shape.getSurface()));
		}
	}

	public HashMap<String, Integer> getShapeCount() {
		var count = new HashMap<String, Integer>();
		count.put("Rectangle", 0);
		count.put("Square", 0);
		count.put("Triangle", 0);
		count.put("Circle", 0);

		for (Shape shape : shapes) {
			var name = shape.getClass().getSimpleName();
			var currentCount = count.get(name) + 1;
			count.put(name, currentCount);
		}
		return count;
	}

	public ArrayList<Shape> getShapesContainPoint(Point2D point) {
		var output = new ArrayList<Shape>();
		for (Shape shape : shapes) {
			var name = shape.getClass().getSimpleName();
			boolean isInside = false;
			
			switch (name) {
			case "Rectangle": {
				var rect = (Rectangle)shape;
				isInside = point.isInsidePolygon(rect.getPoints());
				break;
			}
			case "Square": {
				var square = (Square)shape;
				isInside = point.isInsidePolygon(square.getPoints());
				break;
			}
			case "Triangle": {
				var triangle = (Triangle)shape;
				isInside = point.isInsidePolygon(triangle.getPoints());
				break;
			}
			case "Circle": {
				var circle = (Circle)shape;
				isInside = point.isInsideCircle(circle.getCenter(), circle.getRadius());
				break;
			}
			}
			
			if (isInside) {
				output.add(shape);
			}
		}
		return output;
	}
}