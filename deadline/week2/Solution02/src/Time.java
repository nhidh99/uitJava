import java.util.Scanner;

public class Time {
	private int hour;
	private int minute;
	private int second;

	public Time() {}
	
	public Time(int h, int m, int s) {
		this.hour = h;
		this.minute = m;
		this.second = s;
	}
	
	public int getHour() {
		return hour;
	}
	
	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
	}
	
	private int toSecond() {
		return hour * 3600 + minute * 60 + second;
	}
	
	private void convertFromSecond(int seconds) {
		this.hour = seconds / 3600;
		seconds %= 3600;
		this.minute = seconds / 60;
		seconds %= 60;
		this.second = seconds;
	}
	
	public void input(Scanner in) {
		System.out.print("- Nhap gio: ");
		hour = in.nextInt();
		System.out.print("- Nhap phut: ");
		minute = in.nextInt();
		System.out.print("- Nhap giay: ");
		second = in.nextInt();
	}
	
	public String toString() {
		return String.format("%02d:%02d:%02d", hour, minute, second);
	}
	
	public int compareTo(Time other) {
		return Integer.compare(this.toSecond(), other.toSecond());
	}
	
	public void addOneSecond() {
		int newSecond = this.toSecond() + 1;
		this.convertFromSecond(newSecond);
	}
}