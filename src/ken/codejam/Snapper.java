package ken.codejam;

import java.io.BufferedWriter;

import ken.codejam.utils.AlgorithmUtils;
import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class Snapper extends AutoParseInputProblem{


	public Snapper() {
		super(new TestCase());
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		new Snapper().start();
	}
	
	public static class TestCase extends AutoParseTestCase {

		public Long N, K;
		public TestCase() {
			super("N K");
		}

		@Override
		public void process(int order, BufferedWriter output) {
			long numOfBit = 1<<N;
			if ((K + 1) % numOfBit == 0){
				print(order, output, "ON");
			} else {
				print(order, output, "OFF");
			}
		}
		
		@Override
		public void clear() {
			
		}
		
	}


}
