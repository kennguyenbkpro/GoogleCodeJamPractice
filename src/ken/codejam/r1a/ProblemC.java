package ken.codejam.r1a;

import java.io.BufferedWriter;
import java.util.Arrays;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class ProblemC extends AutoParseInputProblem{

	public ProblemC(){
		super(new TestCase());
	}
	public static void main(String[] args) {
		new ProblemC().start();
	}

	public static class TestCase extends AutoParseTestCase {
		public Integer N;
		public Integer[] BFF; 
		public TestCase(){
			super("N\n"
					+ "#BFF");
		}
		
		@Override
		public void process(int order, BufferedWriter output) {
			int[] buf = new int[N];
			int[] ad = new int[N];
			int max1 = 0, max2 = 0;
			
			for (int i = 0; i < N; i ++){
				Arrays.fill(buf, 0);
				int last = i;
				int bff = BFF[i] - 1;
				int count = 1; buf[i] = count;
				while (buf[bff] == 0){
					count ++;
					buf[bff] = count;
					last = bff;
					bff = BFF[bff] - 1;
				}
				int cycle = count - buf[bff] + 1;
				if (cycle == 2){
					if (ad[bff] < buf[bff]) ad[bff] = buf[bff];
					if (ad[last] < 1) ad[last] = 1;
				} else {
					if (max1 < cycle) max1 = cycle;
				}
			}
			for (int i = 0; i < N; i ++){
				max2 += ad[i];
			}
			print(order, output, Math.max(max1, max2) + "");
		}

		

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
	}
}
