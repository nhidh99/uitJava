import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		var pointA = new Point2D();
		var pointB = new Point2D();
		var in = new Scanner(System.in);
		
		System.out.println("Nhap diem A");
		pointA.input(in);
		System.out.println("Nhap diem B");
		pointB.input(in);
		
		int translateX, translateY;
		System.out.println("Nhap vector");
		System.out.print("- Nhap x: ");
		translateX = in.nextInt();
		System.out.print("- Nhap y: ");
		translateY = in.nextInt();
		
		pointA.translate(translateX, translateY);
		pointB.translate(translateX, translateY);
		
		System.out.println("---");
		System.out.println("Diem A sau khi di chuyen: " + pointA.toString());
		System.out.println("Diem B sau khi di chuyen: " + pointB.toString());
		System.out.println("Khoang cach AB: " + pointA.distanceTo(pointB));
		in.close();
	}
}
