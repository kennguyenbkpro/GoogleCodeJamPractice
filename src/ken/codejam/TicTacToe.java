package ken.codejam;

import java.io.BufferedWriter;
import java.util.ArrayList;

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
			ArrayList<String> listString = new ArrayList<>();
			boolean canbeDraw = true;
			
			StringBuilder builder3 = new StringBuilder();
			StringBuilder builder4 = new StringBuilder();
			for (int i = 0; i < N; i ++){
				StringBuilder builder1 = new StringBuilder();
				StringBuilder builder2 = new StringBuilder();
				for (int j = 0; j < N; j ++){
					if (data[i][j] == '.'){
						canbeDraw = false;
					}
					builder1.append(data[i][j]);
					builder2.append(data[j][i]);
				}
				listString.add(builder1.toString());
				listString.add(builder2.toString());
				
				builder3.append(data[i][i]);
				builder4.append(data[i][N - 1 - i]);
			}
			listString.add(builder3.toString());
			listString.add(builder4.toString());
			
			
			for (String s : listString){
				if (!s.contains("O") && !s.contains(".")){
					print(order, output, "X won");
					return;
				} else if (!s.contains("X") && !s.contains(".")){
					print(order, output, "O won");
					return;
				}
			}
			if (canbeDraw){
				print(order, output, "Draw");
			} else {
				print(order, output, "Game has not completed");
			}
		}
		@Override
		public void clear() {
			
		}
	}

}
