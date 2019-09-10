import java.io.*;

public class Solution {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		//create array
		int[][] output = new int[7][7];
		for (int count = 0; count < 7; count++) {
			output[count][0] = output[count][count] = 1;
			for (int index = 1; index < count; index++) {
				output[count][index] = output[count-1][index-1] + output[count-1][index];			
			}
		}
		
		try {
			// write array to file
			var writer = new BufferedWriter(new FileWriter("output.txt"));
			for (int i = 0; i < 7; i++) {
				for (int j = 0; j <= i; j++) {
					writer.write(output[i][j] + " ");
				}
				writer.write("\n");
			}
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}