package ken.codejam.r1b;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class R1BPA extends AutoParseInputProblem{

	public R1BPA(){
		super(new TestCase());
	}
	public static void main(String[] args) {
		new R1BPA().start();
	}
	
	public static String[] nums = {
			 "ZERO", "ONE", "TWO", "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"
	};
	
	public static class TestCase extends AutoParseTestCase {
		public Integer T;
		public String S;
		public TestCase(){
			super("@S");
		}
		

		@Override
		public void process(int order, BufferedWriter output) {
			ArrayList<Character> list = new ArrayList<>();
			char[] ac = S.toCharArray();
			for (int i = 0; i < ac.length; i ++){
				list.add(ac[i]);
			}
			Collections.sort(list);
			
			HashMap<Character, Integer> hm = new HashMap<>();
			
			for (int i = 0; i < list.size(); i ++){
				char key = list.get(i);
				if (hm.containsKey(key)){
					int val = hm.get(key);
					hm.put(key, val + 1);
				} else {
					hm.put(key, 1);
				}
			}
			
			int[] result = new int[10];
			Arrays.fill(result, 0);
			
			result[0] = getHM(hm, 'Z');
			result[2] = getHM(hm, 'W');
			result[4] = getHM(hm, 'U');
			result[6] = getHM(hm, 'X');
			result[8] = getHM(hm, 'G');
			result[3] = getHM(hm, 'R') - result[4] - result[0];
			result[5] = getHM(hm, 'F') - result[4];
			result[7] = getHM(hm, 'S') - result[6];
			result[9] = getHM(hm, 'I') - result[8] - result[6] - result[5];
			result[1] = getHM(hm, 'O') - result[4] - result[2] - result[0];
			
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < 10; i ++){
				for (int j = 0; j < result[i]; j ++){
					builder.append(i);
				}
			}
				
			
			print(order, output, builder.toString());
		}
		
		int getHM(HashMap<Character, Integer> hm, char key){
			if (hm.containsKey(key)){
				return hm.get(key);
			} else return 0;
		}
		
		int bSearch(ArrayList<Character> list, int low, int high, char val){
			if (low == high){
				if (val == list.get(low)) return low;
				else return -1;
			}
			int mid = low + high;
			if (list.get(mid) == val){
				return mid;
			} else if (list.get(mid) < val){
				return bSearch(list, mid, high, val);
			} else {
				return bSearch(list, low, mid, val);
			}
			
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
	}

}