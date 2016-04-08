package ken.codejam;

import java.io.BufferedWriter;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;


/**
 * Problem

You receive a credit C at a local store and would like to buy two items. 
You first walk through the store and create a list L of all available items. 
From this list you would like to buy two items that add up to the entire value of the credit. 
The solution you provide will consist of the two integers indicating 
the positions of the items in your list (smaller number first).

Input

The first line of input gives the number of cases, N. N test cases follow. For each test case there will be:

One line containing the value C, the amount of credit you have at the store.
One line containing the value I, the number of items in the store.
One line containing a space separated list of I integers. Each integer P indicates the price of an item in the store.
Each test case will have exactly one solution.
Output

For each test case, output one line containing "Case #x: " followed by the indices of the two items 
whose price adds up to the store credit. The lower index should be output first.

Limits

5 ≤ C ≤ 1000
1 ≤ P ≤ 1000

Small dataset

N = 10
3 ≤ I ≤ 100

Large dataset

N = 50
3 ≤ I ≤ 2000

Sample


Input 

3
100
3
5 75 25
200
7
150 24 79 50 88 345 3
8
8
2 1 9 4 4 56 90 3

Output 
Case #1: 2 3
Case #2: 1 4
Case #3: 4 5

 * @author Ken
 *
 */
public class StoreCredit extends AutoParseInputProblem{
	public StoreCredit() {
		super(new TestCase());
	}



	public static class TestCase extends AutoParseTestCase{
		
		public TestCase(){
			super("C\n"
					+ "I\n"
					+ "#P");
		}
		public Integer C;
		public Integer I;
		public Integer[] P;
		
		@Override
		public void process(int order, BufferedWriter output) {
			for (int i = 0; i < P.length; i ++){
				if (P[i] < C){
					for (int j = i + 1; j < P.length; j ++){
						if (P[i] + P[j] == C){
							print(order, output, (i + 1) + " " + (j + 1));
							return;
						}
					}
				}
			}
		}

		@Override
		public void clear() {
			
		}
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new StoreCredit().start();
	}
	


}
