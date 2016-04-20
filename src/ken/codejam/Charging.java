//package ken.codejam;
//
//import java.io.BufferedWriter;
//import java.util.Arrays;
//import java.util.Comparator;
//
//import ken.codejam.utils.AutoParseInputProblem;
//import ken.codejam.utils.AutoParseTestCase;
//
//public class Charging extends AutoParseInputProblem{
//
//	public Charging(){
//		super(new TestCase());
//	}
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		new Charging().start();
//	}
//	
//	public static class TestCase extends AutoParseTestCase {
//		public Integer N, L;
//		public String[] O;
//		public String[] D;
//		public String[] R;
//		
//		public TestCase(){
//			super("N L\n"
//					+ "#O\n"
//					+ "#D");
//		}
//		
//		int betterAlg(){
//			char[][] flip = new char[N][L];
//			for (int i = 0; i < N; i ++){
//				for (int j = 0; j < L; j ++){
//					if (D[0].charAt(j) == O[i].charAt(j)){
//						flip[i][j] = '0';
//					} else {
//						flip[i][j] = '1';
//					}
//				}
//			}
//			
//			int min = N + 1;
//			int mini = -1;
//			char[][] Q = new char[N][L];
//			for (int i = 0; i < N; i ++){
//				for (int j = 0; j < N; j ++){
//					for (int k = 0; k < L; k ++){
//						Q[j][k] = (flip[i][k] ^ O[j].charAt(k)) == 1 ? '1' : '0';
////						System.out.println(flip[j][k] + ":" + O[j].charAt(k) + "=" + Q[j][k]);
//					}
//					R[j] = String.valueOf(Q[j]);
//				}
////				System.out.println(flip[i]);
//				if (compare()){
//					int count = 0;
//					for (int j =0; j < L; j ++){
//						if (flip[i][j] == '1') count ++;
//					}
//					if (min > count) {
//						min = count;
//						mini = i;
//					}
//				}
//			}
//			if (mini >= 0){
//				System.out.println(String.valueOf(flip[mini]));
//			}
//			return min;
//		}
//		
//		boolean compare(){
//			Arrays.sort(R, new Comparator<String>(){
//
//				@Override
//				public int compare(String o1, String o2) {
//					long r = Long.valueOf(o1, 2) - Long.valueOf(o2, 2);
//					if (r == 0) return 0;
//					return r > 0 ? 1 : -1;
//				}
//				
//			});
//			for (int i = 0; i < N; i ++){
////				System.out.println(R[i] + ":" + D[i]);
//				if (!R[i].equals(D[i])) return false;
//			}
//			return true;
//		}
//
//		@Override
//		public void process(int order, BufferedWriter output) {
//			Arrays.sort(D, new Comparator<String>(){
//
//				@Override
//				public int compare(String o1, String o2) {
//					long r = Long.valueOf(o1, 2) - Long.valueOf(o2, 2);
//					if (r == 0) return 0;
//					return r > 0 ? 1 : -1;
//				}
//				
//			});
//			R = new String[N];
//			
//			int r = betterAlg();
//			if (r == N +1){
//				print(order, output, "NOT POSSIBLE");
//			} else {
//				print(order, output, r + "");
//			}
//			
////			
////			int[] sw = new int[L];
////			for (int i = 0; i < L; i ++){
////				int c1 = 0, c0 = 0;
////				for (int j = 0; j < N; j ++){
////					if (O[j].charAt(i) == '1'){
////						c1 ++;
////					}
////					if (D[j].charAt(i) == '0'){
////						c0 ++;
////					}
////				}
////				if (c1 == c0){
////					if (c1 + c0 == N){
////						sw[i] = 2;
////					} else {
////						sw[i] = 1;
////					}
////				} else if (c1 + c0 == N){
////					sw[i] = 0;
////				} else {
////					print(order, output, "NOT POSSIBLE");
////					return;
////				}
////			}
////			int count = 0;
////			R = new String[N];
////			char[][] Q = new char[N][L];
////			for (int i = 0; i < N; i ++){
////				for (int j = 0; j < L; j ++){
////					if (sw[j] == 1){
////						count ++;
////						Q[i][j] = O[i].charAt(j) == '0' ? '1' : '0';
////					} else if (sw[j] == 0){
////						Q[i][j] = O[i].charAt(j);
////					}
////				}
////				R[i] = String.valueOf(Q[i]);
////			}
////			if (!compare(sw)){
////				print(order, output, "NOT POSSIBLE");
////			} else {
////				for (int i = 0; i < L; i ++){
////					if (sw[i] == 2){
////						sw[i] = 0;
////						if (!compare(sw)) {
////							
////						}
////					}
////				}
////			}
//		}
//		
//		boolean compare(int[] sw){
//			Arrays.sort(R, new Comparator<String>(){
//
//				@Override
//				public int compare(String o1, String o2) {
//					long r = Long.valueOf(o1, 2) - Long.valueOf(o2, 2);
//					if (r == 0) return 0;
//					return r > 0 ? 1 : -1;
//				}
//				
//			});
//			for (int i = 0; i < N; i ++){
//				for (int j = 0; j < L; j ++){
//					if (sw[j] != 2){
//						if (R[i].charAt(j) != D[i].charAt(j)) return false;
//					}
//				}
//			}
//			return true;
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
