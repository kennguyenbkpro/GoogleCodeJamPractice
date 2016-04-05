package ken.codejam;

import java.io.BufferedWriter;

import ken.codejam.utils.FirstLineNumOfTCProblem;

public class Omino extends FirstLineNumOfTCProblem{

	public Omino() {
		super(new TestCase());
	}

	public static void main(String[] args) {
		new Omino().start();
	}

	@Override
	public boolean onReadTestCase(BaseTestCase testcase, String line) {
		((TestCase) testcase).setValue(line);
		return true;
	}
	
	public static class TestCase extends BaseTestCase {
		int X, R, C;
		public void setValue(String line){
			String[] val = line.split(" ");
			X = Integer.valueOf(val[0]);
			R = Integer.valueOf(val[1]);
			C = Integer.valueOf(val[2]);
		}

		@Override
		public void process(int order, BufferedWriter output) {
			int state = 0;
			
			int S = R > C ? C : R;
			int L = R > C ? R : C;
			
			int csqX = (int) Math.ceil((double) X /(double) 2);
			
			if (X >= 7 || (X > L)){
				state = 1;
			} else if ((R * C) % X != 0){
				state = 2;
			} else if (X == 3 && S == 1){
				state = 3;
			} else if (X == 4 && S <= 2){
				state = 4;
			} else if (X == 5 && (S < 3 || (S == 3 && L == 5))){
				state = 5;
			} else if (X == 6 && S <= 3){
				state = 6;
			}
			if (state > 0){
				print(order, output, "RICHARD"
//											+ state + ": " + X + " - " + R + " - " + C
											);
			} else {
				print(order, output, "GABRIEL"
//											+ ": " + X + " - " + R + " - " + C
											);
			}
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
		
	}

}
