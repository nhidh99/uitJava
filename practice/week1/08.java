import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		var in = new Scanner(System.in);
		System.out.print("Nhap n: ");
		int n = in.nextInt();
		
		String numInString = Integer.toString(n);
		String reverseInString = new StringBuilder(numInString).reverse().toString();
		
		if (numInString.equals(reverseInString)) {
			System.out.println("La so doi xung");
		}
		else System.out.println("Khong phai la so doi xung");
		in.close();
	}
}