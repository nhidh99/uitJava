package deadline;

import java.util.*;

public class MyCalendar {
	
	public int getDayOfWeek(int day, int month, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DATE, day);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.YEAR, year);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		return dayOfWeek == Calendar.SUNDAY ? 8 : dayOfWeek;
	}
	
	public int getDayOfMonth(int month, int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.YEAR, year);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	public void printMonthCalendar(int month, int year) {
		int dayOfWeek = getDayOfWeek(1, month, year);
		int dayOfMonth = getDayOfMonth(month, year);
		
		System.out.println("\tMon\tTue\tWed\tThu\tFri\tSat\tSun\n");
		
		for (int i = 2; i < dayOfWeek; i++) {
			System.out.print("\t");
		}
		
		for (int curDate = 1; curDate <= dayOfMonth; curDate++) {
			System.out.print("\t" + curDate);
			if (dayOfWeek++ == 8) {
				System.out.println("\n");
				dayOfWeek = 2;
			}
		}
		System.out.println();
	}
	
	public void printYearCalendar(int year) {
		System.out.println("\n\t\t\t\tLICH NAM " + year);
		String[] monthName = new String[] {
				"January", "February", "March",
				"April", "May", "June", "July",
				"August", "September", "October",
				"November", "December"
		};
		
		for (int month = 1; month <= 12; month++) {
			System.out.println("\n=========================================================================");
			System.out.printf("\n\t\t\t\t%s\n\n", monthName[month - 1]);
			printMonthCalendar(month, year);
		}
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Nhap nam: ");
		int year = in.nextInt();
		new MyCalendar().printYearCalendar(year);
		in.close();
	}
}