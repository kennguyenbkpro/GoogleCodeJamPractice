package ken.codejam;

import java.io.BufferedWriter;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class Snapper extends AutoParseInputProblem{


	public Snapper() {
		super(new TestCase());
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
//		new Snapper().start();
		short A = 0x7FFF;
		System.out.println(A);
	}
	
	public static class TestCase extends AutoParseTestCase {

		public Long N, K;
		public TestCase() {
			super("N K");
		}

		@Override
		public void process(int order, BufferedWriter output) {
			long numOfBit = fastExponentiation(2, N);
			if ((K + 1) % numOfBit == 0){
				print(order, output, "ON");
			} else {
				print(order, output, "OFF");
			}
		}
		
		long fastExponentiation(int a, long n){
			if (n == 0) return 1;
			if (n % 2 == 0){
				long result = fastExponentiation(a, n/2);
				return result * result;
			} else {
				return a * fastExponentiation(a, n - 1);
			}
		}

		@Override
		public void clear() {
			
		}
		
	}


}
