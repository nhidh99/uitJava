import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		var in = new Scanner(System.in);
		System.out.print("Nhap n: ");
		int n = in.nextInt();
		
		System.out.print("Cac uoc so cua " + n + ": ");
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) {
				System.out.print(i + " ");
			}
		}
		in.close();
	}
}