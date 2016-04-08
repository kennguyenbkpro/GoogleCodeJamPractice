package ken.codejam;

import java.io.BufferedWriter;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

/**
 * https://code.google.com/codejam/contest/6224486/dashboard#s=p0
 * 
 * Input 
 	
Output 
 
4
4 11111
1 09
5 110011
0 1

Case #1: 0
Case #2: 1
Case #3: 2
Case #4: 0



 * @author Ken
 *
 */
public class Standing extends AutoParseInputProblem{

	public Standing() {
		super(new TestCase());
	}

	public static void main(String[] args) {
		new Standing().start();
	}
	
	public static class TestCase extends AutoParseTestCase {
		public TestCase(){
			super("@line");
		}
		public String line;

		@Override
		public void process(int order, BufferedWriter output) {
			String[] val = line.split(" ");
			
			int numLevel = Integer.valueOf(val[0]);
			int[] people = new int[numLevel + 1];
			for (int i = 0; i < val[1].length(); i ++){
				people[i] = Integer.valueOf(val[1].charAt(i) + "");
			}
			
			
			print(order, output, solve(people) + "");
		}
		
		private long solve(int[] p){
			long result = 0;
			long count = p[0];
			for (int i = 1; i < p.length; i++){
				if (p[i] != 0){
					if (count < i){
						result += (i - count);
						count = i;
					}
					count += p[i];
				}
			}
			return result;
		}

		@Override
		public void clear() {
			
		}
		
	}


}
