package ken.codejam;

import java.io.BufferedWriter;

import ken.codejam.utils.FirstLineNumOfTCProblem;

public class Standing extends FirstLineNumOfTCProblem{

	public Standing() {
		super(new TestCase());
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Standing().start();
	}
	
	public static class TestCase extends BaseTestCase {
		public String line;

		@Override
		public void process(int order, BufferedWriter output) {
			String[] val = line.split(" ");
			
			int numLevel = Integer.valueOf(val[0]);
			int[] people = new int[numLevel + 1];
			for (int i = 0; i < val[1].length(); i ++){
				people[i] = Integer.valueOf(val[1].charAt(i) + "");
			}
			
			
			print(order, output, solve(people) + "");
		}
		
		private long solve(int[] p){
			long result = 0;
			long count = p[0];
			for (int i = 1; i < p.length; i++){
				if (p[i] != 0){
					if (count < i){
						result += (i - count);
						count = i;
					}
					count += p[i];
				}
			}
			return result;
		}

		@Override
		public void clear() {
			
		}
		
	}

	@Override
	public boolean onReadTestCase(BaseTestCase testcase, String line) {
		((TestCase) testcase).line = line;
		return true;
	}

	@Override
	protected String getInputFile() {
		return "standing.in";
	}

	@Override
	protected String getOutputFile() {
		return "standing.out";
	}

}
