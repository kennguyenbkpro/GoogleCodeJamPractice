package ken.codejam.r1a;

import java.io.BufferedWriter;
import java.io.IOException;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class ProblemB extends AutoParseInputProblem{

	public ProblemB(){
		super(new TestCase());
	}
	public static void main(String[] args) {
		new ProblemB().start();
	}

	public static class TestCase extends AutoParseTestCase {
		public Integer NL;
		public Integer[][] list;
		
		public TestCase(){
			super("NL\n"
					+ "&(NL)list");
		}
		
		void swap(Integer[][] m, int i, int j){
			Integer[] temp = m[j];
			m[j] = m[i];
			m[i] = temp;
		}

		@Override
		public void process(int order, BufferedWriter output) {
			int[] result = new int[2501];
			for (int i = 0; i < (NL + 1)/2; i ++){
				for (int j = 0; j < NL; j ++){
					result[list[j][i]] ++;
				}
			}
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < result.length; i ++){
				if (result[i] % 2 == 1){
					builder.append(" ").append(i);
				}
			}
			try {
				output.write("Case #" + order + ":" + builder.toString() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
	}
}
