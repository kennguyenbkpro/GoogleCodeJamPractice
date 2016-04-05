package ken.codejam;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

import ken.codejam.utils.ReadWriteProblem;

/**
 * Problem

You are given two vectors v1=(x1,x2,...,xn) and v2=(y1,y2,...,yn). The scalar product of these vectors is a single number, calculated as x1y1+x2y2+...+xnyn.

Suppose you are allowed to permute the coordinates of each vector as you wish. Choose two permutations such that the scalar product of your two new vectors is the smallest possible, and output that minimum scalar product.

Input

The first line of the input file contains integer number T - the number of test cases. For each test case, the first line contains integer number n. The next two lines contain n integers each, giving the coordinates of v1 and v2 respectively.
Output

For each test case, output a line

Case #X: Y
where X is the test case number, starting from 1, and Y is the minimum scalar product of all permutations of the two given vectors.
Limits

Small dataset

T = 1000
1 ≤ n ≤ 8
-1000 ≤ xi, yi ≤ 1000

Large dataset

T = 10
100 ≤ n ≤ 800
-100000 ≤ xi, yi ≤ 100000

Sample


Input 
 	
Output 
 
2
3
1 3 -5
-2 4 1
5
1 2 3 4 5
1 0 1 0 1

Case #1: -25
Case #2: 6

 * @author Ken
 *
 */


public class MinScalarProduct extends ReadWriteProblem{

	public static final String INPUT_FILE = "scalar.txt";
	public static final String OUTPUT_FILE = "scalar_out.txt";
	
	public static final int STATE_N = 0;
	public static final int STATE_SPACE = 1;
	public static final int STATE_V1 = 2;
	public static final int STATE_V2 = 3;
	
	private int state = STATE_N;
	
	public static class TestCase {
		public ArrayList<Long> vector1 = new ArrayList<>();
		public ArrayList<Long> vector2 = new ArrayList<>();
		
		public void clear(){
			vector1.clear();
			vector2.clear();
		}
		
		public void process(int order, BufferedWriter output){
			vector1.sort(new Comparator<Long>() {

				@Override
				public int compare(Long o1, Long o2) {
					return (int) (o1 - o2);
				}
			});
			vector2.sort(new Comparator<Long>() {

				@Override
				public int compare(Long o1, Long o2) {
					return (int) (o2 - o1);
				}
			});
			
			long product = 0;
			for (int i = 0; i < vector1.size(); i ++){
				product += ( vector1.get(i)) * ( vector2.get(i));
			}
			
			try {
				output.write("Case #" + order + ": " + product + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private TestCase mTestCase = new TestCase();
	private int order = 0;
	
	public static void main(String[] args) {
		System.out.println("Start..");
		new MinScalarProduct().start();
		System.out.println("Done!");
	}
	
	@Override
	public void onReadLine(String line, BufferedWriter output) {
		switch (state) {
		case STATE_N:
			state = STATE_SPACE;
			break;
		case STATE_SPACE:
			mTestCase.clear();
			order ++;
			state = STATE_V1;
			break;
		case STATE_V1:
			{
				String[] coords = line.split(" ");
				for (String coor : coords){
					mTestCase.vector1.add(Long.valueOf(coor));
				}
				state = STATE_V2;
				break;
			}
		case STATE_V2:
			{
				String[] coords = line.split(" ");
				for (String coor : coords){
					mTestCase.vector2.add(Long.valueOf(coor));
				}
				mTestCase.process(order, output);
				state = STATE_SPACE;
				break;
			}
				

		default:
			break;
		}
	}

	@Override
	protected String getInputFile() {
		return INPUT_FILE;
	}

	@Override
	protected String getOutputFile() {
		return OUTPUT_FILE;
	}

}
