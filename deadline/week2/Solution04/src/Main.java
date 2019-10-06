import java.util.*;

public class Main {
	public static void main(String[] args) {
		var shapeList = new ArrayList<Shape>();
		var in = new Scanner(System.in);
		
		System.out.print("Nhap so luong hinh: ");
		var numShape = Integer.parseInt(in.nextLine());
		var numRectangle = 0;
		var numCircle = 0;
		
		for (int i = 0; i < numShape; i++) {
			System.out.print("- Chon loai hinh (1: chu nhat, 2: tron): ");
			int type = Integer.parseInt(in.nextLine());
			switch (type) {
			case 1:
				numRectangle++;
				System.out.print("+ Nhap chieu dai: ");
				var length = Double.parseDouble(in.nextLine());
				System.out.print("+ Nhap chieu rong: ");
				var width = Double.parseDouble(in.nextLine());
				shapeList.add(new Rectangle(length, width));
				break;

			case 2:
				numCircle++;
				System.out.print("+ Nhap ban kinh: ");
				var radius = Double.parseDouble(in.nextLine());
				shapeList.add(new Circle(radius));
				break;
			}
		}
		in.close();
		
		var maxSurfaceShape = shapeList.get(0);
		for (var shape : shapeList) {
			if (maxSurfaceShape.getSurface() < shape.getSurface()) {
				maxSurfaceShape = shape;
			}
		}
		
		System.out.println();
		System.out.println("-> So luong hinh chu nhat: " + numRectangle);
		System.out.println("-> So luong hinh tron: " + numCircle);
		System.out.println("-> Thong tin hinh co dien tich lon nhat:\n" + maxSurfaceShape.toString());
	}
}
