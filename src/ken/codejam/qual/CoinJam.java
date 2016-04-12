package ken.codejam.qual;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;
import ken.codejam.utils.FirstLineNumOfTCProblem;

public class CoinJam extends FirstLineNumOfTCProblem{
	public CoinJam(){
		super(new TestCase());
	}

	public static void main(String[] args) {
		new CoinJam().start();
	}
	
	public static class TestCase extends BaseTestCase {
		
		public int N, J;
		
		BigInteger[] primes;
//		int[] primes;
		
		public TestCase() {
			String prime = "2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199";
			String[] val = prime.split(", ");
			primes = new BigInteger[val.length];
//			primes = new int[val.length];
			for (int i = 0; i < val.length; i ++){
//				primes[i] = Integer.valueOf(val[i]);
				primes[i] = new BigInteger(val[i]);
			}
		}
		
		public void setLine(String line){
			String[] val = line.split(" ");
			N = Integer.valueOf(val[0]);
			J = Integer.valueOf(val[1]);
		}

		@Override
		public void process(int order, BufferedWriter output) {
			char[] chars  = new char[N];
			chars[0] = chars[N-1] = '1';
			for (int i = 1; i < N - 1; i ++ ){
				chars[i] = '0';
			}
			
			genString(chars, 1, N - 1);
			
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < result.size(); i ++){
				builder.append("\n" + result.get(i));
			}
			try {
				output.write("Case #" + order + ":" + builder.toString() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

		
		void genString(char[] s, int i, int j){
			if (result.size() >= J) return;
			if (i >= j) {
				printTest(new String(s));
				return;
			}
			s[i] = '0';
			genString(s, i + 1, j);
			s[i] = '1';
			genString(s, i + 1, j);
		}
		
		ArrayList<String> result = new ArrayList<>();
//		void printTest(String s){
//			StringBuilder builder = new StringBuilder();
//			builder.append(s);
//			for (int i = 2; i < 11; i ++){
//				long a = Long.valueOf(s, i);
//				int div = getFirstDivisor(a);
//				if (div > 1){
//					builder.append(" " + div);
//				} else {
//					return;
//				}
//			}
//			result.add(builder.toString());
//			System.out.println(result.size() + "");
//		}
		
		void printTest(String s){
			StringBuilder builder = new StringBuilder();
			builder.append(s);
			for (int i = 2; i < 11; i ++){
				BigInteger a = new BigInteger(s, i);
				BigInteger div = getFirstDivisor(a);
				if (div.compareTo(BigInteger.ONE) > 0){
					builder.append(" " + div);
				} else {
					return;
				}
			}
			result.add(builder.toString());
			System.out.println(result.size() + "");
		}
		
		BigInteger getFirstDivisor(BigInteger n){
			for (int i = 0; i < primes.length; i ++){
				if (n.mod(primes[i]).equals(BigInteger.ZERO)){
					return primes[i];
				}
			}
			return BigInteger.ONE;
		}
		
		//checks whether an int is prime or not.
		int getFirstDivisor(long n) {
		    //check if n is a multiple of 2
		    if (n%2==0) return 2;
		    //if not, then just check the odds
		    int max = (int) Math.sqrt(n);
		    for(int i=3 ; i <= max; i+=2) {
		        if(n%i==0)
		            return i;
		    }
		    return 1;
		}

		@Override
		public void clear() {
		}
		
	}

	@Override
	public boolean onReadTestCase(BaseTestCase testcase, String line) {
		((TestCase) testcase).setLine(line);
		return true;
	}

}
