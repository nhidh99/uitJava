import java.io.*;
import java.util.*;

public class Solution {
	public static void main(String[] args) {
		try {
			// read matrix
			var reader = new BufferedReader(new FileReader("input.txt"));
			List<List<Integer>> input = new ArrayList<List<Integer>>();
			String line = null;

			while ((line = reader.readLine()) != null) {
				List<Integer> arr = new ArrayList<Integer>();
				for (var str : line.split(" ")) {
					arr.add(Integer.parseInt(str));
				}
				input.add(arr);
			}
			reader.close();
			
			// get array From ArrayList
			int row = input.size();
			int col = input.get(0).size();
			int[][] matrix = new int[row][col];
			
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < col; j++) {
					matrix[i][j] = input.get(i).get(j);
				}
			}
			
			// write result
			var writer = new BufferedWriter(new FileWriter("output.txt"));
			var max = maxInMatrix(matrix, row, col);
			var min = minInMatrix(matrix, row, col);
			var sum = sumOfMatrix(matrix, row, col);
			var primes = primesInMatrix(matrix, row, col);
			
			writer.write(String.format(
					"Max = %d\n"
					+ "Min = %d\n"
					+ "Primes = %s\n"
					+ "Sum = %d", max, min, Arrays.toString(primes), sum));
			writer.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
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