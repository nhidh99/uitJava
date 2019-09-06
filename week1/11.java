import java.text.SimpleDateFormat;
import java.time.Year;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		var in = new Scanner(System.in);
		Calendar a = Calendar.getInstance();
		Calendar b = Calendar.getInstance();
		
		// input
		System.out.println("Nhap ngay a");
		System.out.print("Nhap ngay: ");
		int date = in.nextInt();
		System.out.print("Nhap thang: ");
		int month = in.nextInt();
		System.out.print("Nhap nam: ");
		int year = in.nextInt();
		a.set(year, month - 1, date);
		
		System.out.println("Nhap ngay b");
		System.out.print("Nhap ngay: ");
		date = in.nextInt();
		System.out.print("Nhap thang: ");
		month = in.nextInt();
		System.out.print("Nhap nam: ");
		year = in.nextInt();
		b.set(year, month - 1, date);
		in.close();
		
		// a
		int compare = a.compareTo(b);
		if (a.get(Calendar.YEAR) == b.get(Calendar.YEAR)
				&& a.get(Calendar.MONTH) == b.get(Calendar.MONTH)
				&& a.get(Calendar.DATE) == b.get(Calendar.DATE)) {
			System.out.println("Hai ngay bang nhau");
		}			
		else if (compare < 0) {
			System.out.println("Ngay a truoc ngay b");
		}
		else {
			System.out.println("Ngay a sau ngay b");
		}
		
		// b
		a.add(Calendar.DATE, 1);
		System.out.println("Ngay tiep theo cua ngay a: " + new SimpleDateFormat("dd/MM/yyyy").format(a.getTime()));
		a.add(Calendar.DATE, -1);
		
		// c
		System.out.println("a la ngay thu " + a.get(Calendar.DAY_OF_YEAR) + " trong nam");
		
		// d
		int monthOfA = a.get(Calendar.MONTH) + 1;
		int yearOfA = a.get(Calendar.YEAR);
		System.out.println("Thang " + monthOfA + "/" + yearOfA + " co " + YearMonth.of(yearOfA, monthOfA).lengthOfMonth() + " ngay");
		
		// e
		if (Year.of(yearOfA).isLeap()) {
			System.out.println("Nam " + yearOfA + " la nam nhuan");
		}
		else System.out.println("Nam " + yearOfA + " khong la nam nhuan");
	}
}