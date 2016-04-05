package ken.codejam;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Comparator;

import ken.codejam.utils.FirstLineNumOfTCProblem;


public class Pancakes extends FirstLineNumOfTCProblem{

	public Pancakes() {
		super(new TestCase());
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Pancakes().start();
	}
	
	public static class TestCase extends BaseTestCase {
		public int D;
		public String D_line;

		@Override
		public void process(int order, BufferedWriter output) {
			ArrayList<Integer> listP = new ArrayList<>();
			String[] val = D_line.split(" ");
			for (int i = 0; i < val.length; i ++){
				listP.add(Integer.valueOf(val[i]));
			}
			
			print(order, output, solve(listP) + "");
		}
		
		private long solve(ArrayList<Integer> P){
			
			P.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});
			long result = P.get(0);
			
			for (int i = 1; i < P.get(0); i ++){
				long sum = i;
				for (int j = 0; j < P.size(); j ++){
					if (i > P.get(j)) break;
					sum += (Math.ceil((float) P.get(j)/ (float) i) - 1);
				}
				if (result > sum) result = sum;
			}
			return result;
		}
		

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
		
	}

	private int state = 0;
	@Override
	public boolean onReadTestCase(BaseTestCase testcase, String line) {
		if (state == 0){
			((TestCase) testcase).D = Integer.valueOf(line);
			state = 1;
		} else if (state == 1){
			((TestCase) testcase).D_line = line;
			state = 0;
			return true;
		}
		return false;
	}

	@Override
	protected String getInputFile() {
		return "pancakes.in";
	}

	@Override
	protected String getOutputFile() {
		return "pancakes.out";
	}

}
