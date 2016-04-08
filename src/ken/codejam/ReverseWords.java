package ken.codejam;

import java.io.BufferedWriter;
import java.io.IOException;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;
import ken.codejam.utils.ReadWriteProblem;


public class ReverseWords extends AutoParseInputProblem {
	
	public ReverseWords() {
		super(new TestCase());
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ReverseWords().start();
	}
	
	public static class TestCase extends AutoParseTestCase {
		public String[] words;
		public TestCase() {
			super("#words");
		}


		@Override
		public void process(int order, BufferedWriter output) {
			StringBuilder builder = new StringBuilder();
			int l = words.length;
			for (int i = l - 1; i > 0; i --){
				builder.append(words[i]).append(" ");
			}
			if (l > 0){
				builder.append(words[0]).append("\n");
			}
			print(order, output, builder.toString());
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
		
	}
	


}
