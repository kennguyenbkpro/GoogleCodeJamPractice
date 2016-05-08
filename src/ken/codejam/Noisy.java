//package ken.codejam;
//
//import java.io.BufferedWriter;
//
//import ken.codejam.utils.AutoParseInputProblem;
//import ken.codejam.utils.AutoParseTestCase;
//
//public class Noisy extends AutoParseInputProblem{
//
//	public Noisy(){
//		super(new TestCase());
//	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new Noisy().start();
//	}
//	
//	public static class TestCase extends AutoParseTestCase {
//		public Integer R, C, N;
//		public TestCase(){
//			super("R C N");
//		}
//		
//		public void process(int order, BufferedWriter output) {
//			int s= R*C; 
//			if (N <= (s + 1)/2){
//				print(order, output, "0");
//				return;
//			}
//			int sum = 0;
//			int[] r = new int[5];
//			for (int i = 0; i < R; i ++){
//				for (int j = 0; j < C; j ++){
//					int t = checkEdge(i, j);
//					r[t] ++;
//					sum += t;
//				}
//			}
//			sum = sum/2;
//			if (s <= N){
//				print(order, output, sum + "");
//				return;
//			}
//			int d = r[4]/2;
//			while (d > 0){
//				d --;
//				s -= 1; sum -= 4;
//				if (s <= N){
//					print(order, output, sum + "");
//					return;
//				}
//			}
//			
//			d = (R -1) + (C - 1);
//			while (d > 0){
//				d --;
//				s -= 1; sum -= 3;
//				if (s <= N){
//					print(order, output, sum + "");
//					return;
//				}
//			}
//			
//			d = 2;
//			while (d > 0){
//				d --;
//				s -= 1; sum -= 2;
//				if (s <= N){
//					print(order, output, sum + "");
//					return;
//				}
//			}
//			
//			
//			print(order, output, "" + sum);
//		}
//
//		public void process2(int order, BufferedWriter output) {
//			int r = 0;
//			int[][] val = new int[R][C];
//			for (int i = 0; i < R; i ++){
//				for (int j = 0; j < C; j ++){
//					val[i][j] = checkEdge(i, j);
//				}
//			}
//			int s= R*C; 
//			if (N <= (s + 1)/2){
//				print(order, output, "0");
//				return;
//			}
//			int MIN = 0;
//			
//			for (int k = s; k > N; k --){
//				int maxi = -1, maxj = -1, max = 0;
//				for (int i = 0; i < R; i ++){
//					for (int j = (i + MIN) % 2; j < C; j += 2){
//						if (val[i][j] > max){
//							max = val[i][j]; maxi = i; maxj = j;
//						}
//					}
//				}
//				if (maxi == -1 || maxj == -1){
//					break;
//				} else {
//					set(val, maxi, maxj);
//					System.out.println(order + ":" + maxi + "," + maxj);
//				}
//			}
//			
//			for (int i = 0; i < R; i ++){
//				for (int j = 0; j < C; j ++){
//					r += val[i][j];
//				}
//			}
//			print(order, output, "" + (r/2));
//			System.out.println(order + ":" + (r/2));
//		}
//		
//		void set(int[][] v, int i, int j){
//			v[i][j] = 0;
//			try {
//				if (v[i - 1][j] > 0)
//					v[i - 1][j] --;
//			} catch (Exception e){}
//			try {
//				if (v[i + 1][j] > 0)
//					v[i + 1][j] --;
//			} catch (Exception e){}
//			try {
//				if (v[i][j - 1] > 0) 
//					v[i][j - 1] --;
//			} catch (Exception e){}
//			try {
//				if (v[i][j + 1] > 0) 
//					v[i][j + 1] --;
//			} catch (Exception e){}
//		}
//		
//		int checkEdge(int i, int j){
//			int r = 4;
//			if (i == 0){
//				r --;
//			}
//			if (i == R - 1){
//				r --;
//			}
//			if (j == 0){
//				r --;
//			}
//			if (j == C - 1){
//				r--;
//			}
//			return r;
//		}
//
//		@Override
//		public void clear() {
//			// TODO Auto-generated method stub
//			
//		}
//	}
//
//}
