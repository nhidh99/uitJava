import Shape2D.*;

public class Solution {
	public static void main(String[] args) {
		Shape[] shapes = new Shape[4];
		shapes[0] = new Triangle();
		shapes[1] = new Rectangle();
		shapes[2] = new Square();
		shapes[3] = new Circle();
		
		for (Shape shape : shapes) {
			var name = shape.getClass().getSimpleName().toUpperCase();
			System.out.println("--- INPUT " + name + " ---");
			shape.inputInfo();
			System.out.print("\n[INFORMATION]");
			System.out.println(shape.getInfo());
			System.out.println(String.format("+ Perimeter = %f", shape.getPerimeter()));
			System.out.println(String.format("+ Surface = %f", shape.getSurface()));
			System.out.println();
		}
	}
}