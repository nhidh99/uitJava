import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		var in = new Scanner(System.in);
		System.out.print("Nhap chuoi a: ");
		String a = in.nextLine();
		System.out.print("Nhap chuoi b: ");
		String b = in.nextLine();

		int sumLength = a.length() + b.length();
		String firstThreeA = a.substring(0, 3);
		String lastThreeB = b.substring(b.length() - 3, b.length());
		char sixthCharA = a.charAt(5);
		String firstA_lastB = firstThreeA + lastThreeB;
		boolean isEqual = a.equals(b);
		boolean isEqualIgnoreCase = a.equalsIgnoreCase(b);
		int indexB_inA = a.indexOf(b);
		
		//a
		System.out.println("Tong chieu dai: " + sumLength);
		
		//b
		System.out.println("Ba ki tu dau cua chuoi a: " + firstThreeA);
		
		//c
		System.out.println("Ba ki tu cuoi cua chuoi b: " + lastThreeB);
		
		//d
		System.out.println("Ki tu thu 6 cua chuoi a: " + sixthCharA);
		
		//e
		System.out.println("Noi chuoi 3 dau A + 3 cuoi B: " + firstA_lastB);
		
		//f
		if (isEqual) {
			System.out.println("Hai chuoi bang nhau");
		}
		else System.out.println("Hai chuoi khong bang nhau");
		
		//g
		if (isEqualIgnoreCase) {
			System.out.println("Hai chuoi bang nhau (khong phan biet hoa, thuong)");
		}
		else System.out.println("Hai chuoi khong bang nhau (khong phan biet hoa, thuong)");
		
		//h
		if (indexB_inA >= 0) {
			System.out.println("b xuat hien tai a dau tien tai vi tri " + indexB_inA);
		}
		else System.out.println("b khong xuat hien tai a");
		
		//i
		if (indexB_inA >= 0) {
			System.out.print("Tat ca vi tri xuat hien cua b trong a: ");
			int n = a.length() - b.length() + 1;
			for (int i = 0; i < n; i++) {
				String subStr = a.substring(i, i + b.length());
				if (subStr.equals(b)) {
					System.out.print(i + " ");
				}
			}
		}
		else System.out.println("b khong xuat hien tai a");
	}
}