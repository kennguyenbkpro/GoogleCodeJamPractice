package ken.codejam.r1c;

import java.io.BufferedWriter;
import java.io.IOException;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class R1CPBA extends AutoParseInputProblem{

	public R1CPBA(){
		super(new TestCase());
	}
	public static void main(String[] args) {
		new R1CPBA().start();
	}
	
	public static String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public static class TestCase extends AutoParseTestCase {
		public Integer N;
		public Integer[] P;
		
		public TestCase(){
			super("N\n"
					+ "#P");
		}

		@Override
		public void process(int order, BufferedWriter output) {
			int max1 = 0, max2 = 0;
			int pmax1 = -1, pmax2 = -1;
			for (int i = 0; i < P.length; i ++){
				if (P[i] > max1){
					max1 = P[i];
					pmax1 = i;
				}
			}
			for (int i = 0; i < P.length; i ++){
				if (P[i] > max2 && i != pmax1){
					max2 = P[i];
					pmax2 = i;
				}
			}
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < max1 - max2; i ++){
				builder.append(" ").append(abc.charAt(pmax1));
			}
			for (int i = 0; i < P.length; i ++){
				if (i != pmax1 && i != pmax2){
					for (int j = 0; j < P[i]; j ++){
						builder.append(" ").append(abc.charAt(i));
					}
				}
			}
			for (int i = 0; i < max2; i ++){
				builder.append(" ").append(abc.charAt(pmax1)).append(abc.charAt(pmax2));
			}
			
			try {
				output.write("Case #" + order + ":" + builder.toString() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
	}

}