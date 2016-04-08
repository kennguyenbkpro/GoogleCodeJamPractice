package ken.codejam;

import java.io.BufferedWriter;
import java.lang.reflect.Array;
import java.util.Arrays;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class DWar extends AutoParseInputProblem{

	public DWar() {
		super(new TestCase());
	}

	public static void main(String[] args) {
		new DWar().start();
	}
	
	public static class TestCase extends AutoParseTestCase {
		public Integer N;
		public Double[] Naomi;
		public Double[] Ken;
		
		public TestCase() {
			super("N\n"
					+ "#Naomi\n"
					+ "#Ken");
		}

		@Override
		public void process(int order, BufferedWriter output) {
			Arrays.sort(Naomi);
			Arrays.sort(Ken);
			
			int warKenWin = 0;
			for (int i = 0; i < N; i ++){
				if (Ken[i] > Naomi[warKenWin]){
					warKenWin ++;
				}
			}
			
			int deWarNaomiWin = 0;
			for (int i = 0; i < N; i ++){
				if (Naomi[i] > Ken[deWarNaomiWin]){
					deWarNaomiWin ++;
				}
			}
			
			print(order, output, deWarNaomiWin + " " +  (N - warKenWin));
		}

		@Override
		public void clear() {
			
		}
		
	}

}
