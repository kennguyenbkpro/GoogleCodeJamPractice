package ken.codejam;

import java.io.BufferedWriter;

import ken.codejam.utils.FirstLineNumOfTCProblem;

public class Welcome extends FirstLineNumOfTCProblem {

	public Welcome() {
		super(new TestCase());
	}

	public static void main(String[] args) {
		new Welcome().start();
	}
	
	public static class TestCase extends BaseTestCase {
		String pattern = "welcome to code jam";
		public String input;
		private int count = 0;
		
		int[][] temp;
		
		private int matchString(String pattern, int pos1, String input, int pos2){
			if (pattern.length() <= pos1){
				return 1;
			} else if (input.length() > pos2)  {
				int r = 0;
				if (pattern.charAt(pos1) == input.charAt(pos2)){
					if (temp[pos1 + 1][pos2 + 1] == -1){
						temp[pos1 + 1][pos2 + 1] = matchString(pattern, pos1 + 1, input, pos2 + 1); 
					}
					r += temp[pos1 + 1][pos2 + 1];
				}
				if (temp[pos1][pos2 + 1] == -1){
					temp[pos1][pos2 + 1] = matchString(pattern, pos1, input, pos2 + 1); 
				}
				r += temp[pos1][pos2 + 1];
				return r % 10000;
			} 
			return 0;
		}
		
		@Override
		public void process(int order, BufferedWriter output) {
			count = 0;
			/////////////////////////
			
			temp = new int[pattern.length() + 1][input.length() + 1];
			for (int i = 0; i < pattern.length() + 1; i ++){
				for (int j = 0; j < input.length() + 1; j ++){
					temp[i][j] = -1;
				}
			}
			count = matchString(pattern, 0, input, 0);
			
			////////////////////////////
			String result;
			if (count < 10){
				result = "000" + count;
			} else if (count < 100){
				result = "00" + count;
			} else if (count < 1000){
				result = "0" + count;
			} else {
				result = "" + count;
			}
			System.out.println(order);
			print(order, output, result);
		}

		@Override
		public void clear() {
		}
		
	}

	@Override
	public boolean onReadTestCase(BaseTestCase testcase, String line) {
		((TestCase) testcase).input = line;
		return true;
	}

	@Override
	protected String getInputFile() {
		return "welcome.in";
	}

	@Override
	protected String getOutputFile() {
		return "welcome.out";
	}

}
