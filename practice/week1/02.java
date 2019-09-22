import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		var in = new Scanner(System.in);
		System.out.print("Nhap ban kinh duong tron: ");
		int r = in.nextInt();
		
		double output = 2 * Math.PI * r;
		System.out.println("Chu vi duong tron: " + output);
		in.close();
	}
}