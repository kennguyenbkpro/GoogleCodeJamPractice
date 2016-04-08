package ken.codejam;

import java.io.BufferedWriter;
import java.util.Arrays;
import java.util.Comparator;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

/**
 * https://code.google.com/codejam/contest/6224486/dashboard#s=p1
 * 
 * Input 
 	
Output 
 
3
1
3
4
1 2 1 2
1
4

Case #1: 3
Case #2: 2
Case #3: 3

 * 
 * @author Ken
 *
 */
public class Pancakes extends AutoParseInputProblem{

	public Pancakes() {
		super(new TestCase());
	}

	public static void main(String[] args) {
		new Pancakes().start();
	}
	
	public static class TestCase extends AutoParseTestCase {
		public Integer D;
		public Integer[] D_line;
		
		public TestCase() {
			super("D\n"
				+ "#D_line");
		}

		@Override
		public void process(int order, BufferedWriter output) {
			
			print(order, output, solve(D_line) + "");
		}
		
		private long solve(Integer[] P){
			Arrays.sort(P, new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});
			long result = P[0];
			
			for (int x = 1; x < P[0]; x ++){
				long sum = x;
				for (int j = 0; j < P.length; j ++){
					if (x > P[j]) break;
					sum += (Math.ceil((float) P[j]/ (float) x) - 1);
				}
				if (result > sum) result = sum;
			}
			return result;
		}
		

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
		
	}


}
