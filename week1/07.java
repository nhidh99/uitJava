import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		var in = new Scanner(System.in);
		System.out.print("Nhap n: ");
		int n = in.nextInt();
		
		int output = Integer.toString(n).length();
		System.out.println("So luong chu so cua " + n + " la: " + output);
		in.close();
	}
}