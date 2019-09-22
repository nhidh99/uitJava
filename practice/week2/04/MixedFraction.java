import java.util.Scanner;

public class MixedFraction {
	private int whole;
	private Fraction fraction;
	
	public MixedFraction() {
		fraction = new Fraction();
	}
	
	public MixedFraction(int whole, int numerator, int denominator) {
		this.whole = whole;
		fraction = new Fraction(numerator, denominator);
	}
	
	public MixedFraction(Fraction f) {
		f.simplify();
		int whole = f.getNumerator() / f.getDenominator();
		int denominator = f.getDenominator();
		int numerator = f.getNumerator();
		
		if (whole >= 0) {
			numerator -= whole * numerator;
		}
		else numerator += whole * numerator;				
		fraction = new Fraction(numerator, denominator);
	}
	
	
	public int getWhole() {
		return whole;
	}
	
	public void setWhole(int whole) {
		this.whole = whole;
	}
	
	public Fraction getFraction() {
		return fraction;
	}
	
	public void setFraction(Fraction fraction) {
		this.fraction = fraction;
	}
	
	public Fraction toFraction() {
		int newNumerator = whole * fraction.getDenominator();
		int newDenominator = fraction.getDenominator();

		if (whole >= 0) {
			newNumerator += fraction.getNumerator();
		}
		else newNumerator -= fraction.getNumerator();

		var output = new Fraction(newNumerator, newDenominator);
		output.simplify();
		return output;
	}
	
	public void inputInfo() {
		@SuppressWarnings("resource")
		var scanner = new Scanner(System.in);
		System.out.print("- whole = ");
		whole = scanner.nextInt();
		fraction.inputInfo();
	}
	
	public String getInfo() {
		StringBuilder sb = new StringBuilder();
		if (whole != 0) sb.append(Integer.toString(whole));
		sb.append("[" + fraction.getInfo() + "]");
		return sb.toString();
	}
		
	public String getSimplifyInfo() {
		var fraction = this.toFraction();
		fraction.simplify();
		var mixedFraction = new MixedFraction(fraction);
		return mixedFraction.getInfo();
	}
	
	public MixedFraction add(MixedFraction other) {
		var f1 = this.toFraction();
		var f2 = other.toFraction();
		return new MixedFraction(f1.add(f2));
	}
	
	public MixedFraction subtract(MixedFraction other) {
		var f1 = this.toFraction();
		var f2 = other.toFraction();
		return new MixedFraction(f1.subtract(f2));
	}
	
	public MixedFraction multiply(MixedFraction other) {
		var f1 = this.toFraction();
		var f2 = other.toFraction();
		return new MixedFraction(f1.multiply(f2));
	}
	
	public MixedFraction divide(MixedFraction other) {
		var f1 = this.toFraction();
		var f2 = other.toFraction();
		return new MixedFraction(f1.divide(f2));
	}
}
