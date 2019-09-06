import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		var in = new Scanner(System.in);
		System.out.print("Nhap n: ");
		int n = in.nextInt();
		
		int output = 0;
		for (int i = 1; i <= n; i++) {
			output += Math.pow(i, i);
		}
		
		System.out.println("Ket qua: " + output);
		in.close();
	}
}