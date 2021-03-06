package ken.codejam.utils;

public class AlgorithmUtils {
	public static long fastExponentiation(int a, long n){
		if (n == 0) return 1;
		if (n % 2 == 0){
			long result = fastExponentiation(a, n/2);
			return result * result;
		} else {
			return a * fastExponentiation(a, n - 1);
		}
	}
}
