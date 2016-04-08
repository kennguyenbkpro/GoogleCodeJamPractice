package ken.codejam.qual;

import java.io.BufferedWriter;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class Chapter4 extends AutoParseInputProblem{
	public Chapter4(){
		super(new TestCase());
	}

	public static void main(String[] args) {
		new Chapter4().start();
	}
	
	public static class TestCase extends AutoParseTestCase {

		public TestCase() {
			super("TO DO");
		}

		@Override
		public void process(int order, BufferedWriter output) {
			
		}

		@Override
		public void clear() {
		}
		
	}

}
