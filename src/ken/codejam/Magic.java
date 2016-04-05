package ken.codejam;

import java.io.BufferedWriter;
import java.lang.reflect.InvocationTargetException;

import ken.codejam.utils.ClassUtils;
import ken.codejam.utils.FirstLineNumOfTCProblem;
import ken.codejam.utils.ParserUtils;

public class Magic extends FirstLineNumOfTCProblem{

	public Magic(BaseTestCase testcase) {
		super(testcase);
	}

	public static void main(String[] args) {
		new Magic(new TestCase()).start();
	}

	private int state = 0;
	private int lineCount = 0;
	@Override
	public boolean onReadTestCase(BaseTestCase testcase, String line) {
		if (state == 0){
			((TestCase) testcase).ans1 = Integer.valueOf(line);
			state = 1;
			lineCount = 0;
		} else if (state == 1){
			((TestCase) testcase).addLineAns1(line, lineCount);
			lineCount ++;
			if (lineCount == 4){
				state = 2;
			}
		} else if (state == 2){
			((TestCase) testcase).ans2 = Integer.valueOf(line);
			state = 3;
			lineCount = 0;
		} else if (state == 3){
			((TestCase) testcase).addLineAns2(line, lineCount);
			lineCount ++;
			if (lineCount == 4){
				state = 0;
				return true;
			}
		}
		return false;
	}
	
	public static class TestCase extends BaseTestCase {
		public int ans1 = -1;
		public int ans2 = -1;
		
		Integer[] rowAns1;
		Integer[] rowAns2;
		
		public void addLineAns1(String line, int lineCount) {
			if (lineCount != ans1  - 1) return;
			rowAns1 = ParserUtils.convertStringToArrayNumber(line, " ", Integer.class);
		}
		public void addLineAns2(String line, int lineCount){
			if (lineCount != ans2  - 1) return;
			rowAns2 = ParserUtils.convertStringToArrayNumber(line, " ", Integer.class);
		}

		@Override
		public void process(int order, BufferedWriter output) {
			int countResult = 0;
			int result = 0;
			for (int i = 0; i < rowAns1.length; i ++){
				for (int j = 0; j < rowAns2.length; j ++){
					if (rowAns1[i] == rowAns2[j]){
						countResult ++;
						result = rowAns1[i];
					}
				}
			}
			if (countResult == 0){
				print(order, output, "Volunteer cheated!");
			} else if (countResult == 1){
				print(order, output, result + "");
			} else {
				print(order, output, "Bad magician!");
			}
			
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
