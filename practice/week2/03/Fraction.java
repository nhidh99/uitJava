import java.util.Scanner;

public class Fraction {
	private int numerator;
	private int denominator;
	
	public Fraction() {}
	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public int getNumerator() {
		return numerator;
	}

	public void setNumerator(int numerator) {
		this.numerator = numerator;
	}
	
	public int getDenominator() {
		return denominator;
	}
	
	public void setDenominator(int denominator) {
		this.denominator = denominator;
	}
	
	// greatest common factor
	private static int GCD(int a, int b) {
		if (b == 0) return a;
		return GCD(b, a % b);
	}
	
	private void simplify() {
		int gcd = GCD(numerator, denominator);
		numerator /= gcd;
		denominator /= gcd;
		
		if (numerator * denominator >= 0) {
			numerator = Math.abs(numerator);
			denominator = Math.abs(denominator);
		}
		else {
			numerator = -Math.abs(numerator);
			denominator = Math.abs(denominator);
		}
	}
	
	public void inputInfo() {
		@SuppressWarnings("resource")
		var scanner = new Scanner(System.in);
		System.out.print("- numerator = ");
		numerator = scanner.nextInt();
		System.out.print("- denominator = ");
		denominator = scanner.nextInt();
	}
	
	public String getInfo() {
		return String.format("%d/%d", numerator, denominator);
	}
	
	public String getSimplifyInfo() {
		this.simplify();
		return String.format("%d/%d", numerator, denominator);
	}
	
	public Fraction add(Fraction other) {
		int newNumerator = this.numerator * other.denominator + other.numerator * this.denominator;
		int newDenominator = this.denominator * other.denominator;
		Fraction output = new Fraction(newNumerator, newDenominator);
		output.simplify();
		return output;
	}
	
	public Fraction subtract(Fraction other) {
		int newNumerator = this.numerator * other.denominator - other.numerator * this.denominator;
		int newDenominator = this.denominator * other.denominator;
		Fraction output = new Fraction(newNumerator, newDenominator);
		output.simplify();
		return output;
	}
	
	public Fraction multiply(Fraction other) {
		int newNumerator = this.numerator * other.numerator;
		int newDenominator = this.denominator * other.denominator;
		Fraction output = new Fraction(newNumerator, newDenominator);
		output.simplify();
		return output;
	}
	
	public Fraction divide(Fraction other) {
		int newNumerator = this.numerator * other.denominator;
		int newDenominator = this.denominator * other.numerator;
		Fraction output = new Fraction(newNumerator, newDenominator);
		output.simplify();
		return output;
	}
}