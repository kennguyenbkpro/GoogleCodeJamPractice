package ken.codejam;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
//			System.out.println(N);
			
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < N; i ++){
				builder.append("\n").append(large(i));
			}
			
			try {
				output.write("Case #" + order + ":" + builder.toString() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
//			System.out.println("TIME: " + (System.currentTimeMillis() - a));
		}
		
		//====================== SMAL =============================
		int small(int i){
			if (N == 1) return 0;
			int minSide = 3001;
			for (int j = 0; j < N; j ++){
				if (i != j){
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
					int s = side1 > side2 ? side2 : side1;
					
					if (minSide > s) {
						minSide = s;
					}
					if (s == 0) return 0;
				}
			}
			return minSide;
		}
		
		long checkSide(long x1, long y1, long x2, long y2, long x, long y){
			return ((x1 - x2) * (y - y1) - (y1 - y2) * (x - x1));
		}
		
		
		//========================== LARGE =========================
		int large(int i){
			if (N == 1) return 0;
			ArrayList<Double> lT = new ArrayList<>();
			for (int j = 0; j < N; j ++){
				if (i != j){
					long dX = T[i][0] - T[j][0];
					long dY = T[i][1] - T[j][1];
					lT.add(Math.atan2(dY, dX));
				}
			}
			
			
			Collections.sort(lT);
//			System.out.println();
			
			int min = 3001;
			for (int j = 0; j < N-1; j ++){
				double op = lT.get(j) + Math.PI - 0.0000001;
				if (op > Math.PI){
					op -= Math.PI * 2;
				}
				int pos1 = binSearchMin(lT, -1, N-1 , op);
				
				if (pos1 == -1) pos1 = N-2;
				int side1 = 0;
				if (j <= pos1){
					side1 = pos1 - j;
				} else {
					side1 = N-1 - j + pos1;
				}
//				System.out.print(" (" + pos1 + ":" + side1 + ")");
				
				if (side1 == 0) return 0;
				if (min > side1) min = side1;
			}
//			System.out.println(min + "-" + lT.toString());
			
			return min;
		}
		
		int binSearchMin(ArrayList<Double> LT, int low, int heigh, double val){
			if (low +1 >= heigh) return low;
			int mid = (low + heigh)/2;
			if (LT.get(mid) < val){
				return binSearchMin(LT, mid, heigh, val);
			} else {
				return binSearchMin(LT, low, mid, val);
			}
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
		
		
	}

}
