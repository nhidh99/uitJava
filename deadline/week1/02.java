package deadline;

import java.util.*;

public class Solution {
	public double[] solve(double a, double b, double c) {
		double delta = Math.pow(b, 2) - 4 * a * c;
		if (delta < 0) {
			return new double[] {};
		} else if (delta == 0) {
			return new double[] { -b / (2 * a) };
		} else {
			return new double[] { 
					(-b + Math.sqrt(delta)) / (2 * a), 
					(-b - Math.sqrt(delta)) / (2 * a) };
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		double a, b, c;

		do {
			System.out.print("Nhap he so a: ");
			a = in.nextDouble();
		} while (a == 0);

		System.out.print("Nhap he so b: ");
		b = in.nextDouble();

		System.out.print("Nhap he so c: ");
		c = in.nextDouble();

		double[] output = new Solution().solve(a, b, c);
		switch (output.length) {
		case 0: {
			System.out.print("-> Phuong trinh vo nghiem");
			break;
		}
		case 1: {
			System.out.printf("-> Phuong trinh co nghiem kep x = %f", output[0]);
			break;
		}
		case 2: {
			System.out.printf("-> Phuong trinh co nghiem x1 = %f ; x2 = %f", output[0], output[1]);
			break;
		}
		default: {
			System.out.print("-> Error!");
			break;
		}
		}
		in.close();
	}
}
