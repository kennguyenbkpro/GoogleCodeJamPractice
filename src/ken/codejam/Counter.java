package ken.codejam;

import java.io.BufferedWriter;

import ken.codejam.utils.AlgorithmUtils;
import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class Counter extends AutoParseInputProblem{

	public Counter(){
		super(new TestCase());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Counter().start();
	}
	
	public static class TestCase extends AutoParseTestCase {
		public Long N;
		
		public TestCase(){
			super("N");
		}

		@Override
		public void process(int order, BufferedWriter output) {
			long count = 0;
			long n = N;
			while (n > 20){
				String s = "";
				String NS = "" + n;
				if (NS.charAt(NS.length()-1) == '0'){//xxxxx0
					count ++;
					n--;
				} else {
					long ra = AlgorithmUtils.fastExponentiation(10, NS.length()/2);
					long left = n /ra; 
					long right = n % ra; 
					if (right == 1){//xxxx0001
						s += (ra/10);
						s += new StringBuffer().append(left).reverse().toString();
						long next = Long.valueOf(s);
						if (n == next){//1000x0001
							next = AlgorithmUtils.fastExponentiation(10, NS.length()-1) - 1;
							count += n - next;
						} else {
							count ++;// just flip
						}
						n = next;
					} else {
						count += right - 1;
						s += left;
						s += AlgorithmUtils.generateString(NS.length()/2 - 1, '0');
						s += '1';
						n = Long.valueOf(s);
					}
					
				}
			}
			count += n;
			print(order, output, "" + count);
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
