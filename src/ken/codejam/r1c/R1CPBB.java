package ken.codejam.r1c;

import java.io.BufferedWriter;
import java.util.Arrays;

import ken.codejam.utils.AlgorithmUtils;
import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class R1CPBB extends AutoParseInputProblem{

	public R1CPBB(){
		super(new TestCase());
	}
	public static void main(String[] args) {
		new R1CPBB().start();
	}
	
	public static class TestCase extends AutoParseTestCase {
		public Integer B;
		public Long M;
		public TestCase(){
			super("B M");
		}

		@Override
		public void process(int order, BufferedWriter output) {
			long max = AlgorithmUtils.fastExponentiation(2, B - 2);
			if (max < M){
				print(order, output, "IMPOSSIBLE");
			} else {
				StringBuilder builder = new StringBuilder();
				builder.append("POSSIBLE");
				int BB = findBB(B, M);
				
				int[][] matrix = new int[B][B];
				
				int rB = BB-1;
				for (int i = 0; i < rB; i ++){
					for (int j = 0; j < B; j ++){
						if (j > i && j < rB){
							matrix[i][j] = 1;
						}
					}
					matrix[i][B-1] = 1;
				}
				
				if (BB < B){
					long re = M - AlgorithmUtils.fastExponentiation(2, BB - 2);
					String s = Long.toString(re, 2);
					System.out.println(s);
					for (int i = s.length()-1; i >= 0; i --){
						if (s.charAt(i) == '1'){
							matrix[s.length() - i][BB-1] = 1;
						}
					}
					matrix[BB-1][B-1] = 1;
				}
			
				
				for (int i = 0; i < B; i ++){
					builder.append("\n");
					for (int j = 0; j < B; j ++){
						builder.append(matrix[i][j]);
					}
				}
				print(order, output, builder.toString());
				
			}
		}
		
		public int findBB(int B, long M){
			for (int i = 2; i <= B; i  ++){
				if (AlgorithmUtils.fastExponentiation(2, i - 2) > M){
					return i - 1;
				}
			}
			return B;
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
	}

}