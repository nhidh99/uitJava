import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		var in = new Scanner(System.in);
		System.out.print("Nhap a: ");
		float a = in.nextFloat();		
		System.out.print("Nhap b: ");
		float b = in.nextFloat();
		
		float output = a / b;
		System.out.printf("Ket qua: %.3f", output);
		in.close();
	}
}