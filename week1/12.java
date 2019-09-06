import java.math.BigInteger;

public class Solution {
	public static void main(String[] args) {
		BigInteger output = new BigInteger("987654321");
		output = output.multiply(new BigInteger("3"));
		output = output.add(new BigInteger("1234567890"));
		System.out.println(output);
	}
}