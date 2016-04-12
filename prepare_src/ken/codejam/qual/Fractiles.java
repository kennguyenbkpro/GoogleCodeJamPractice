package ken.codejam.qual;

import java.io.BufferedWriter;
import java.util.ArrayList;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class Fractiles extends AutoParseInputProblem{
	public Fractiles(){
		super(new TestCase());
	}

	public static void main(String[] args) {
		new Fractiles().start();
	}
	
	public static class TestCase extends AutoParseTestCase {
		public Integer K, C, S;
		
		public TestCase() {
			super("K C S");
		}
		
		void printResult(int order, BufferedWriter output){
			StringBuilder builder = new StringBuilder();
			int minS = K/2;
			if ((K + 1)/2 > S){
				print(order, output, "IMPOSSIBLE");
				return;
			} else {
				long sum = 2;
				builder.append(sum);
				for (int i = 1; i < minS; i ++){
					sum = 2 + sum + K * 2;
					builder.append(" ").append(sum);
				}
				if (K % 2 == 1){
					builder.append(" ").append((sum + 1));
				}
			}
			print(order, output, /*K + " " + C + " " + S + ":" +*/ builder.toString());
		}

		@Override
		public void process(int order, BufferedWriter output) {
			if (C == 1){
				if (S < K){
					print(order, output, "IMPOSSIBLE");
				} else {
					StringBuilder builder = new StringBuilder();
					for (int i = 0; i < K - 1; i ++){
						builder.append(i + 1).append(" ");
					}
					builder.append(K);
					print(order, output,/* K + " " + C + " " + S + ":" +  */builder.toString());
				}
				return;
			} else if (K == 1){
				print(order, output, /* K + " " + C + " " + S + ":" + */ "1");
			} else {
//				if (C >= 3){
//					C = 3;
//				}
				C = 2;
				printResult(order, output);
				return;
			}
//			printDirect(order, output);
		}
		
		public void printDirect(int order, BufferedWriter output) {
			if (K > 16) return;
			listString = new ArrayList<>();
			
			genString(new char[K], 0);
			
			int length = listString.get(0).length();
			int[] count = new int[length];
			StringBuilder builder = new StringBuilder();
			int countStep = 0;
			
			printListString(order, output);
			for (int i = 0; i < length; i ++){
				if (listString.size() == 1) break;
				for (int j = listString.size() - 1; j >= 0; j --){
					if (listString.get(j).charAt(i) == 'L'){
						count[i] ++;
					}
				}
				if (listString.size() >= 4 && count[i] == (listString.size() >> 2)){
					builder.append(i + 1).append(" ");
					countStep ++;
					for (int j = listString.size() - 1; j >= 0; j --){
						if (listString.get(j).charAt(i) == 'G'){
							listString.remove(j);
						}
					}
					printListString(order, output);
				} else if (listString.size() < 4 && count[i] == (listString.size() >> 1)){
					builder.append(i + 1).append(" ");
					countStep ++;
					for (int j = listString.size() - 1; j >= 0; j --){
						if (listString.get(j).charAt(i) == 'G'){
							listString.remove(j);
						}
					}
					printListString(order, output);
				}
			}
			if (countStep <= S){
				print(order, output, builder.toString());
			} else {
				print(order, output, "IMPOSSIBLE");
			}
		}
		
		public void printListString(int order, BufferedWriter output) {
			for (int i = 0; i < listString.size(); i ++){
				System.out.println(listString.get(i));
			}
			System.out.println();
			System.out.println();
			System.out.println();
		};
		
		ArrayList<String> listString;
		void genString(char[] s, int i){
			if (i >= s.length){
//				listString.add(doArt(s));
				String result = doArt(s, s, C - 1);
				listString.add(result);
//				if (result.charAt(1) == 'L' && result.charAt(result.length() - 2) == 'L'){
//					System.out.println(String.valueOf(s) + " ==> " + result);
//				}
				return;
			}
			s[i] = 'G';
			genString(s, i + 1);
			s[i] = 'L';
			genString(s, i + 1);
		}
		
		String doArt(char[] ori, char[] s, int c){
			if (c == 0) return new String(s);
			char[] result = new char[s.length * K];
			for (int i = 0; i < s.length; i ++){
				if (s[i] == 'G'){
					for (int j = 0; j < K; j ++){
						result[j + i*K] = 'G';
					}
				} else {
					for (int j = 0; j < K; j ++){
						result[j + i*K] = ori[j];
					}
				}
			}
			return doArt(ori, result, c - 1);
		}

		@Override
		public void clear() {
		}
		
	}

}
