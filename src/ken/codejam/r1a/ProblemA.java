package ken.codejam.r1a;

import java.io.BufferedWriter;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class ProblemA extends AutoParseInputProblem{

	public ProblemA(){
		super(new TestCase());
	}
	public static void main(String[] args) {
		new ProblemA().start();
	}

	public static class TestCase extends AutoParseTestCase {
		public Character[] lets;
		public TestCase(){
			super("#lets"
					+ "");
		}

		@Override
		public void process(int order, BufferedWriter output) {
			char first = lets[0];
			String s = "" + first;
			for (int i = 1; i < lets.length; i ++){
				if (lets[i] >= s.charAt(0)){
					s = lets[i] + s;
				} else {
					s = s + lets[i];
				}
			}
			print(order, output, s);
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
	}
}
