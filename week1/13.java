import java.util.Arrays;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		// Input array A
		var scanner = new Scanner(System.in);
		System.out.print("Nhap so luong phan tu: ");
		int n = scanner.nextInt();
		
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			System.out.print("Nhap phan tu thu " + (i+1) + ": ");
			A[i] = scanner.nextInt();
		}
		
		// Generate array B
		int[] B = new int[n];
		for (int i = 0; i < n; i++) {
			B[i] = (int)(Math.random() * 100);
		}
		System.out.println(Arrays.toString(B));
		
		// Generate array C
		int[] C = Arrays.copyOf(A, n);
		System.arraycopy(B, B.length - 3, C, 0, 3);
		Arrays.sort(C);
		System.out.println(Arrays.toString(C));
		
		// Find x in C
		System.out.print("Nhap so x: ");
		int x = scanner.nextInt();
		scanner.close();
		
		if (Arrays.binarySearch(C, x) >= 0) {
			System.out.println("x thuoc C");
		}
		else System.out.println("x khong thuoc C");
	}
}