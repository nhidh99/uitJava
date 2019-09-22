package deadline;
import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Nhap n: ");
		int n = in.nextInt();
		int[] arr = new int[n];
		
		for (int i = 0; i < n; i++) {
			System.out.printf("Nhap so thu %d: ", i + 1);
			arr[i] = in.nextInt();
		}
		
		Arrays.sort(arr);
		System.out.println("Mang da sap xep: " + Arrays.toString(arr));
		in.close();
	}
}
