package ken.codejam;

import java.io.BufferedWriter;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class MustRoom extends AutoParseInputProblem{

	public MustRoom(){
		super(new TestCase());
	}
	public static void main(String[] args) {
		new MustRoom().start();
	}
	
	public static class TestCase extends AutoParseTestCase {
		public Integer N;
		public Integer[] m;
		public TestCase() {
			super("N\n"
					+ "#m");
		}
		@Override
		public void process(int order, BufferedWriter output) {
			long sum = 0;
			for (int i = 0; i < N - 1; i ++){
				if (m[i] > m[i + 1]){
					sum += m[i] - m[i + 1];
				}
			}
			long v = 0;
			for (int i = 0; i < N - 1; i ++){
				if (m[i] > m[i + 1]){
					int temp = m[i] - m[i + 1];
					if (temp > v) v = temp;
				}
			}
			
			long sum2 = 0;
			for (int i = 0; i < N - 1; i ++){
				long t = m[i] - v;
				sum2 += (t > 0 ? v : m[i]);
			}
			print(order, output, sum + " " + sum2);
		}
		@Override
		public void clear() {
			
		}
		
	}

}
