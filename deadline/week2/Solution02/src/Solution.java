import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		var timeA = new Time();
		var timeB = new Time();
		var in = new Scanner(System.in);
	
		System.out.println("Nhap thoi gian thu nhat:");
		timeA.input(in);
		System.out.println("Nhap thoi gian thu hai:");
		timeB.input(in);
		
		System.out.print("\nThoi gian lon hon: ");
		System.out.println((timeA.compareTo(timeB) > 0 ? timeA : timeB).toString());

		timeA.addOneSecond();
		System.out.println("Thoi gian thu nhat sau khi tang 1 giay: " + timeA.toString());
		in.close();
	}
}
