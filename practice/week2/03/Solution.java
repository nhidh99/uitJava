public class Solution {
	public static void main(String[] args) {
		System.out.println("[INPUT FRACTION 1]");
		var f1 = new Fraction();
		f1.inputInfo();

		System.out.println("\n[INPUT FRACTION 2]");
		var f2 = new Fraction();
		f2.inputInfo();
		
		System.out.println("\n[RESULT]");
		System.out.println(String.format("f1 = %s", f1.getInfo()));
		System.out.println(String.format("f2 = %s", f2.getInfo()));
		System.out.println(String.format("f1 + f2 = %s", f1.add(f2).getInfo()));
		System.out.println(String.format("f1 - f2 = %s", f1.subtract(f2).getInfo()));
		System.out.println(String.format("f1 * f2 = %s", f1.multiply(f2).getInfo()));
		System.out.println(String.format("f1 / f2 = %s", f1.divide(f2).getInfo()));
	}
}