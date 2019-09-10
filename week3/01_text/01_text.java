import java.io.*;
import java.util.*;

public class Solution {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			// read array A from A.txt
			System.out.println("--- READ ARRAY A FROM FILE ---");
			var readFile = "A.txt";
			var reader = new BufferedReader(new FileReader(readFile));
			var lineToParse = reader.readLine().split(" ");
			System.out.println("[Read file successfully!]");
			
			int n = lineToParse.length;
			int[] arr_A = new int[n];
			for (int i = 0; i < n; i++) {
				arr_A[i] = Integer.parseInt(lineToParse[i]);
			}
			System.out.println("Array A: " + Arrays.toString(arr_A));
			reader.close();
			
			// generate array B and save in B.txt
			System.out.println("\n--- GENERATE ARRAY B AND SAVE ---");
			System.out.print("Input number of element of array B: ");
			int m = new Scanner(System.in).nextInt();
			int[] arr_B = new int[m];
			for (int i = 0; i < m; i++) {
				arr_B[i] = (int)(Math.random() * 100);
			}
			System.out.println("Array B: " + Arrays.toString(arr_B));
			
			var writeFile = "B.txt";
			var writer = new BufferedWriter(new FileWriter(writeFile));
			for (var i : arr_B) {
				writer.write(i + " ");
			}
			writer.close();
			System.out.println("[Write file successfully!]");
			
			// generate array C from array A & B and save in C.txt
			System.out.println("\n--- GENERATE ARRAY C AND SAVE ---");
			int[] arr_C = Arrays.copyOf(arr_A, n);
			System.arraycopy(arr_B, m - 3, arr_C, 0, 3);
			Arrays.sort(arr_C);
			System.out.println("Array C: " + Arrays.toString(arr_C));
			
			writeFile = "C.txt";
			writer = new BufferedWriter(new FileWriter(writeFile));
			for (var i : arr_C) {
				writer.write(i + " ");
			}
			writer.close();
			System.out.println("[Write file successfully!]");			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}