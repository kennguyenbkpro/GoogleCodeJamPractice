package ken.codejam;

import java.io.BufferedWriter;

import ken.codejam.utils.FirstLineNumOfTCProblem;

public class Numbers extends FirstLineNumOfTCProblem{

	public static class TestCase extends BaseTestCase {
		public int n = 0;
		
		@Override
		public void process(int order, BufferedWriter output) {
			int[][] a = { {3, 5}, { 1, 3} };
			a = fast_exp(a, n);
			int result = ( 2 * a[0][0]) % 1000 - 1;
			if (result < 10){
				print(order, output, "00" + result);
			} else if (result < 100){
				print(order, output, "0" + result);
			} else {
				print(order, output, "" + result);
			}
		}

		@Override
		public void clear() {
			
		}
		
		private int[][] matrix_mult(int[][] a, int[][] b){
			int[][] result = new int[2][2];
			result[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % 1000;
			result[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % 1000;
			result[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % 1000;
			result[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % 1000;
			return result;
		}
		
		//Do phep nhan ma tran co tinh chat ket hop
		private int[][] fast_exp(int[][] a, int n){
			if (n == 1) return a;
			if (n%2 == 0){
				int[][] temp = fast_exp(a, n/2);
				return matrix_mult(temp, temp);
			} else {
				return matrix_mult(a, fast_exp(a, n - 1));
			}
		}
		
	}
	
	public static void main(String[] args) {
		new Numbers().start();
	}
	
	public Numbers() {
		super(new TestCase());
	}
	
	@Override
	public boolean onReadTestCase(BaseTestCase testcase, String line) {
		((TestCase) testcase).n = Integer.valueOf(line);
		return true;
	}
	
	@Override
	protected String getInputFile() {
		return "numbers.in";
	}

	@Override
	protected String getOutputFile() {
		return "numbers.out";
	}

}
