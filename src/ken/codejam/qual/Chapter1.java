package ken.codejam.qual;

import java.io.BufferedWriter;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class Chapter1 extends AutoParseInputProblem{
	public Chapter1(){
		super(new TestCase());
	}

	public static void main(String[] args) {
		new Chapter1().start();
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
