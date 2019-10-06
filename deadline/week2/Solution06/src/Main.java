import java.util.*;

public class Main {
	public static void main(String[] args) {
		var in = new Scanner(System.in);
		var c1 = new Complex();
		var c2 = new Complex();
		
		System.out.println("Nhap so thuc thu nhat: ");
		c1.input(in);
		System.out.println("Nhap so thuc thu hai: ");
		c2.input(in);
		in.close();
		
		System.out.println("-> Ket qua:");
		System.out.println("- Tong: " + c1.add(c2).toString());
		System.out.println("- Hieu: " + c1.minus(c2).toString());
		System.out.println("- Tich: " + c1.multiply(c2).toString());
		System.out.println("- Thuong: " + c1.divide(c2).toString());
	}
}