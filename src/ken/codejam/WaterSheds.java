package ken.codejam;

import java.io.BufferedWriter;
import java.io.IOException;

import ken.codejam.utils.FirstLineNumOfTCProblem;

public class WaterSheds extends FirstLineNumOfTCProblem{

	public WaterSheds() {
		super(new TestCase());
	}

	public static void main(String[] args) {
		new WaterSheds().start();
	}

	@Override
	protected String getInputFile() {
		return "water.in";
	}

	@Override
	protected String getOutputFile() {
		return "water.out";
	}
	
	public static class TestCase extends BaseTestCase {
		String abc = "abcdefghijklmnopqrstuvwxyz";
		
		private int countType = 0;

		public int H = 0, W = 0;
		private int[][] alt, result, sink;
		private int order;
		
		public void set(int H, int W){
			this.H = H;
			this.W = W;
			alt = new int[H][W];
			result = new int[H][W];
			for (int i = 0; i < H; i ++){
				for (int j = 0; j < W; j ++){
					result[i][j] = -1;
				}
			}
			sink = new int[H][W];
			order = 0;
			countType = 0;
		}
		
		//return order
		public int add(String line){
			String[] val = line.split(" ");
			for (int i = 0; i < val.length; i ++){
				alt[order][i] = Integer.valueOf(val[i]);
			}
			order ++;
			return order;
		}
		
		private int check(int i, int j){//n w e s
			if (result[i][j] > - 1) return result[i][j];
			
			int mini = 0, minj = 0;
			int c = alt[i][j];
			
			if ( i > 0 && c > alt[i - 1][j]){
				c = alt[i - 1][j];
				mini = - 1; minj = 0;
			} 
			if ( j > 0 && c > alt[i][j - 1]){
				c = alt[i][j - 1];
				mini = 0; minj = -1;
			} 
			if ( j < W - 1 && c > alt[i][j + 1]){
				c = alt[i][j + 1];
				mini = 0; minj = 1;
			} 
			if ( i < H - 1 && c > alt[i + 1][j]){
				c = alt[i + 1][j];
				mini = 1; minj = 0;
			} 
			if (mini == 0 && minj == 0){
				result[i][j] = countType;
				countType ++;
			} else {
				result[i][j] = check(i + mini, j + minj);
			}
			return result[i][j];
//			if (result[i][j] == -1 && result[i + mini][j + minj] > -1){
//				result[i][j] = result[i + mini][j + minj];
//			} else if (result[i][j] > -1 && result[i + mini][j + minj] == -1){
//				result[i + mini][j + minj] = result[i][j];
//			} else if (result[i][j] == -1 && result[i + mini][j + minj] == -1){
//				result[i][j] = countType;
//				result[i + mini][j + minj] = countType;
//				countType ++;
//			}
		}
		
		@Override
		public void process(int order, BufferedWriter output) {
			for (int i = 0; i < H; i ++){
				for (int j = 0; j < W; j ++){
					check(i, j);
				}
			}
			
			
			//Print result
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < H; i ++){
				builder.append("\n");
				for (int j = 0; j < W - 1; j ++){
					builder.append(abc.charAt(result[i][j])).append(" ");
				}
				builder.append(abc.charAt(result[i][W - 1]));
			}
			try {
				output.write("Case #" + order + ":" + builder.toString() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void clear() {
			
		}
		
	}
	
	private int state = 0;//H, W
	



	@Override
	public boolean onReadTestCase(BaseTestCase testcase, String line) {
		switch (state) {
		case 0:
			String[] val = line.split(" ");
			int H = Integer.valueOf(val[0]);
			int W = Integer.valueOf(val[1]);
			((TestCase) testcase).set(H, W);
			state = 1;
			break;
		case 1:
			int order = ((TestCase) testcase).add(line);
			if (order >= ((TestCase) testcase).H){
				state = 0;
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}

}
