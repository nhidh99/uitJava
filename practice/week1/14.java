public class Solution {
	public static void main(String[] args) {
		int[][] output = new int[7][7];
		for (int count = 0; count < 7; count++) {
			output[count][0] = output[count][count] = 1;
			for (int index = 1; index < count; index++) {
				output[count][index] = output[count-1][index-1] + output[count-1][index];			
			}
		}
		
		for (int i = 0; i < 7; i++) {
			for (int j = 0; j <= i; j++) {
				System.out.print(output[i][j] + " ");
			}
			System.out.println();
		}
	}
}