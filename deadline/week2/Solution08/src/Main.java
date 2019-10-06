import java.util.*;

public class Main {
	public static void main(String[] args) {
		var in = new Scanner(System.in);
		System.out.print("Nhap so nguyen a: ");
		var a = Integer.parseInt(in.nextLine());
		System.out.print("Nhap so nguyen b: ");
		var b = Integer.parseInt(in.nextLine());
		System.out.printf("-> UCLN(%d,%d) = %d\n\n", a, b, MyMath.gcd(a, b));

		System.out.print("Nhap so thuc x: ");
		var x = Float.parseFloat(in.nextLine());
		System.out.print("Nhap so thuc y: ");
		var y = Float.parseFloat(in.nextLine());
		System.out.print("Nhap so thuc z: ");
		var z = Float.parseFloat(in.nextLine());
		System.out.printf(
				"-> Max(%f, %f, %f) = %f\n" 
				+ "-> Min(%f, %f, %f) = %f\n\n", 
				x, y, z, MyMath.max(x, y, z), 
				x, y, z, MyMath.min(x, y, z));
		
		System.out.print("Nhap so nguyen n: ");
		var n = Integer.parseInt(in.nextLine());
		System.out.printf("-> IsPrime(%d) = %b\n", n, MyMath.isPrime(n));
		System.out.printf("-> Sum(1->%d) = %d\n", n, MyMath.sumOneToNumber(n));
		System.out.printf("-> Abs(%d) = %d\n\n", n, MyMath.absolute(n));
		
		System.out.print("Nhap so thuc f: ");
		var f = Float.parseFloat(in.nextLine());
		System.out.printf("-> Round(%f) = %d", f, MyMath.round(f));
		in.close();
	}
}
