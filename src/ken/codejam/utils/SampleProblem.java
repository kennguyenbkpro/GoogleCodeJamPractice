package ken.codejam.utils;

import java.io.BufferedWriter;

public class SampleProblem extends AutoParseInputProblem{

	public SampleProblem(){
		super(new TestCase());
	}
	public static void main(String[] args) {
		new SampleProblem().start();
	}
	
	public static class TestCase extends AutoParseTestCase {
		public TestCase(){
			super("TODO");
		}

		@Override
		public void process(int order, BufferedWriter output) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
	}

}