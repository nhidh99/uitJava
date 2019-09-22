import java.util.*;

public class Solution {
	public static void main(String[] args) {
		var scanner = new Scanner(System.in);
		System.out.print("Nhap so hang: ");
		int row = scanner.nextInt();
		System.out.print("Nhap so cot: ");
		int col = scanner.nextInt();
		scanner.nextLine();
		
		int[][] input = new int[row][col];
		for (int r = 0; r < row; r++) {
			System.out.print("Nhap hang thu " + (r + 1) + ": ");
			String[] line = scanner.nextLine().split(" ");
			for (int c = 0; c < col; c++) {
				input[r][c] = Integer.parseInt(line[c]);
			}
		}
		scanner.close();
		
		var max = maxInMatrix(input, row, col);
		var min = minInMatrix(input, row, col);
		var primes = primesInMatrix(input, row, col);
		var sum = sumOfMatrix(input, row, col);
		
		System.out.println("max = " + max);
		System.out.println("min = " + min);
		System.out.println("primes = " + Arrays.toString(primes));
		System.out.println("sum = " + sum);
	}
	
	public static int maxInMatrix(int[][] matrix, int row, int col) {
		int max = matrix[0][0];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				max = Math.max(max, matrix[i][j]);
			}
		}
		return max;
	}
	
	public static int minInMatrix(int[][] matrix, int row, int col) {
		int min = matrix[0][0];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				min = Math.min(min, matrix[i][j]);
			}
		}
		return min;
	}
	
	public static boolean isPrime(int n) {
		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) {
				count++;
			}
		}
		return count == 2;
	}
	
	public static Integer[] primesInMatrix(int[][] matrix, int row, int col) {
		Set<Integer> output = new HashSet<Integer>();
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (isPrime(matrix[i][j])) {
					output.add(matrix[i][j]);
				}
			}
		}
		return output.toArray(new Integer[output.size()]);
	}
	
	public static int sumOfMatrix(int[][] matrix, int row, int col) {
		int output = 0;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				output += matrix[i][j];
			}
		}
		return output;
	}
}