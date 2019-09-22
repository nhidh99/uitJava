import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		var in = new Scanner(System.in);
		System.out.print("Nhap n: ");
		int n = in.nextInt();
		int output = n * (n + 1) / 2;
		System.out.println("Ket qua: " + output);
		in.close();
	}
}