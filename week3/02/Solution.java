import java.io.*;
import java.util.*;

public class Solution {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		try {
			// read array from binary file
			var readFile = "C.bin";
			var binReader = new ObjectInputStream(new FileInputStream(readFile));
			var arr_binary = (int[]) binReader.readObject();
			binReader.close();
			
			// read array from text file
			readFile = "C.txt";
			var bfReader = new BufferedReader(new FileReader(readFile));
			var datas = bfReader.readLine().split(" ");			
			int n = datas.length;
			int[] arr_text = new int[n];
			for (int i = 0; i < n; i++) {
				arr_text[i] = Integer.parseInt(datas[i]);
			}
			bfReader.close();
			
			// display array
			System.out.println("Array binary: " + Arrays.toString(arr_binary));
			System.out.println("Array text: " + Arrays.toString(arr_text));
			
			// check number x is in array
			System.out.print("Input x = ");
			int x = new Scanner(System.in).nextInt();
			for (int i = 0; i < n; i++) {
				if (x == arr_binary[i]) {
					System.out.println("=> in array");
					return;
				}
			}
			System.out.println("=> not in array");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}