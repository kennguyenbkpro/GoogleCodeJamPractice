package ken.codejam;

import java.io.BufferedWriter;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class TicTacToe extends AutoParseInputProblem{

	public TicTacToe(){
		super(new TestCase());
	}
	public static void main(String[] args) {
		new TicTacToe().start();
	}
	
	public static class TestCase extends AutoParseTestCase {
		public static int N = 4;
		public Character[][] data;
		public String breakLine;
		public TestCase(){
			super("&(N)data\n"
					+ "@breakLine");
		}
		@Override
		public void process(int order, BufferedWriter output) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < N; i ++){
				for (int j = 0; j < N; j ++){
					builder.append(data[i][j]);
				}
				builder.append("\n");
			}
			print(order, output, builder.toString());
		}
		@Override
		public void clear() {
			
		}
	}

}
