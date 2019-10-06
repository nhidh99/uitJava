
public class MyMath {
	public static int gcd(int a, int b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}
	
	public static float max(float a, float b, float c) {
		return Math.max(a, Math.max(b, c));
	}
	
	public static float min(float a, float b, float c) {
		return Math.min(a, Math.min(b, c));
	}
	
	public static boolean isPrime(int n) {
		int count = 0;
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) count++;
		}
		return count == 2;
	}
	
	public static int sumOneToNumber(int n) {
		return n * (n + 1) >>> 1;
	}
	
	public static int absolute(int n) {
		return Math.abs(n);
	}
	
	public static int round(float n) {
		return (int)((n * 10 + 5) / 10);
	}
}
