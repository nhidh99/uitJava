import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		var in = new Scanner(System.in);
		System.out.print("Nhap n: ");
		int n = in.nextInt();
		
		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) {
				count++;
			}
		}
		
		if (count == 2) {
			System.out.println("La so nguyen to");
		}
		else System.out.println("Khong la so nguyen to");
		
		in.close();
	}
}