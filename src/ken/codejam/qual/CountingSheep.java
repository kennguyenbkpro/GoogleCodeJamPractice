package ken.codejam.qual;

import java.io.BufferedWriter;
import java.util.HashSet;
import java.util.Set;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class CountingSheep extends AutoParseInputProblem{
	public CountingSheep(){
		super(new TestCase());
	}

	public static void main(String[] args) {
		new CountingSheep().start();
	}
	
	public static class TestCase extends AutoParseTestCase {
		public Long N;
		
		
		public TestCase() {
			super("N");
		}

		@Override
		public void process(int order, BufferedWriter output) {
			Set<Character> setDigit = new HashSet<>();
			
			if (N == 0){
				print(order, output, "INSOMNIA");
				return;
			}
			long tN = N;
			while(true){
				String NString = tN + "";
				for (int i = 0; i < NString.length(); i ++){
					setDigit.add(NString.charAt(i));
				}
				if (setDigit.size() == 10){
					print(order, output, tN + "");
					return;
				}
				tN = tN + N;
			}
		}

		@Override
		public void clear() {
		}
		
	}

}
