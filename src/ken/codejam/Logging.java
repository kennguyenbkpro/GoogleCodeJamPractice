package ken.codejam;

import java.io.BufferedWriter;
import java.io.IOException;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class Logging extends AutoParseInputProblem{

	public Logging(){
		super(new TestCase());
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Logging().start();
	}
	
	public static class TestCase extends AutoParseTestCase {
		public Integer N;
		public Long[][] T;
		
		public TestCase(){
			super ("N\n"
					+ "&(N)T");
		}

		@Override
		public void process(int order, BufferedWriter output) {
			long a = System.currentTimeMillis();
			System.out.println(N);
			
			cache = new int[N][N];
			for (int i = 0; i < N; i ++){
				for (int j = 0; j < N; j ++){
					cache[i][j] = -1;
				}
			}
			
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < N; i ++){
				builder.append("\n").append(small(i));
			}
			try {
				output.write("Case #" + order + ":" + builder.toString() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis() - a);
		}
		
		int[][] cache;
		int small(int i){
			if (N == 1) return 0;
			int minSide = 3001;
			for (int j = 0; j < N; j ++){
				if (i != j){
					int s = cache[i][j];
					if (s == -1){
						int side1 = 0, side2 = 0;
						for (int k = 0; k < N; k ++){
							if (j != k && i != k){
								long c = checkSide(T[i][0], T[i][1], T[j][0], T[j][1], T[k][0], T[k][1]);
								if (c > 0){
									side1 ++;
								} else if (c < 0){
									side2 ++;
								}
							}
						}
						s = side1 > side2 ? side2 : side1;
					}
					
					if (minSide > s) {
						minSide = s;
					}
					cache[i][j] = s;
					cache[j][i] = s;
					if (s == 0) return 0;
				}
			}
			return minSide;
		}
		
		long checkSide(long x1, long y1, long x2, long y2, long x, long y){
			return ((x1 - x2) * (y - y1) - (y1 - y2) * (x - x1));
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
		
		
	}

}
