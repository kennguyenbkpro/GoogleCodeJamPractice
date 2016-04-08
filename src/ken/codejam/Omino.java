package ken.codejam;

import java.io.BufferedWriter;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;
import ken.codejam.utils.FirstLineNumOfTCProblem;

/**
 * https://code.google.com/codejam/contest/6224486/dashboard#s=p3
 * 
 * 
 
Input 
 	
Output 
 
4
2 2 2
2 1 3
4 4 1
3 2 3

Case #1: GABRIEL
Case #2: RICHARD
Case #3: RICHARD
Case #4: GABRIEL



 * @author Ken
 *
 */
public class Omino extends AutoParseInputProblem{

	public Omino() {
		super(new TestCase());
	}

	public static void main(String[] args) {
		new Omino().start();
	}
	
	public static class TestCase extends AutoParseTestCase {
		public Integer X, R, C;
		
		public TestCase(){
			super("X R C");
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
