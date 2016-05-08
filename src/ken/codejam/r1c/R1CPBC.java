package ken.codejam.r1c;

import java.io.BufferedWriter;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class R1CPBC extends AutoParseInputProblem{

	public R1CPBC(){
		super(new TestCase());
	}
	public static void main(String[] args) {
		new R1CPBC().start();
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