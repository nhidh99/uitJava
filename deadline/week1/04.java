package deadline;
import java.util.*;

public class Solution {
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		Scanner in = new Scanner(System.in);
		
		System.out.print("Nhap thang: ");
		int month = in.nextInt();
		System.out.print("Nhap nam: ");
		int year = in.nextInt();
		
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.YEAR, year);
		int output = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		System.out.printf("-> Thang %d/%d co %d ngay", month, year, output);
		in.close();
	}
}
