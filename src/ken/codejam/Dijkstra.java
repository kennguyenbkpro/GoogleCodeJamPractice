package ken.codejam;

import java.io.BufferedWriter;
import java.util.HashMap;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;
import ken.codejam.utils.FirstLineNumOfTCProblem;

/**
 * Link: https://code.google.com/codejam/contest/6224486/dashboard#s=p2
 * @author Ken
 *
 *
 * Input 

5
2 1
ik
3 1
ijk
3 1
kji
2 6
ji
1 10000
i
 	
Output 

Case #1: NO
Case #2: YES
Case #3: NO
Case #4: YES
Case #5: NO

 */
public class Dijkstra extends AutoParseInputProblem{

	public Dijkstra() {
		super(new TestCase());
	}

	public static void main(String[] args) {
		new Dijkstra().start();
	}
	
	public static class TestCase extends AutoParseTestCase {
		
		private HashMap<String, Value> hashMap;
		public Long X;
		public Integer L;
		public Character[] data;
		
		public TestCase() {
			super("L X\n"
					+ "#data");
			
			initHashMap();
		}
		
		
		

		@Override
		public void process(int order, BufferedWriter output) {
			int state = 0;//init
			
			//multiply all values ijk...
			Value eachVal = new Value(false, data[0]);
			for (int i = 1; i < data.length; i ++){
				eachVal = mul(eachVal, data[i]);
			}
			
			//Loop X times
			Value exVal = ex(eachVal, X);
			if (!exVal.negative || exVal.val != '1'){
				print(order, output, "NO");
				return;
			}
			
			Value val = new Value(false, "-");
			long loop = X > 8 ? 8 : X;
			for (int x = 0; x < loop; x ++){
				for (int i = 0; i < data.length; i ++){
					if (state == 0){
						if (data[i] == 'i'){
							state = 2;//i okay
						} else {
							val.negative = false;
							val.val = data[i];
							state = 1;//i read
						}
					} else if (state == 1){
						val = mul(val, data[i]);
						if (!val.negative && val.val == 'i'){
							state = 2;
						}
					} else if (state == 2){
						if (data[i] == 'j'){
							state = 4;//j okay
						} else {
							val.negative = false;
							val.val = data[i];
							state = 3;//j read
						}
					} else if (state == 3){
						val = mul(val, data[i]);
						if (!val.negative && val.val == 'j'){
							state = 4;
						}
					} 
					else if (state == 4){
						break;
					}
				}
				
			}
			if (state == 4){
				print(order, output, "YES");
			} else {
				print(order, output, "NO");
			}
			
		}

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
		
		private void initHashMap(){
			hashMap = new HashMap<>();
			
			hashMap.put("11", new Value(false, "1"));
			hashMap.put("1i", new Value(false, "i"));
			hashMap.put("1j", new Value(false, "j"));
			hashMap.put("1k", new Value(false, "k"));
			
			hashMap.put("i1", new Value(false, "i"));
			hashMap.put("ii", new Value(true, "1"));
			hashMap.put("ij", new Value(false, "k"));
			hashMap.put("ik", new Value(true, "j"));
			
			hashMap.put("j1", new Value(false, "j"));
			hashMap.put("ji", new Value(true, "k"));
			hashMap.put("jj", new Value(true, "1"));
			hashMap.put("jk", new Value(false, "i"));
			
			hashMap.put("k1", new Value(false, "k"));
			hashMap.put("ki", new Value(false, "j"));
			hashMap.put("kj", new Value(true, "i"));
			hashMap.put("kk", new Value(true, "1"));
		}
		
		public static class Value {
			public Value(boolean neg, String val){
				this(neg, val.charAt(0));
			}
			public Value(boolean neg, char val){
				this.negative = neg;
				this.val = val;
			}
			public boolean negative = false;
			public char val;
		}
		
		private Value mul(Value v1, Value v2){
			Value temR = hashMap.get(v1.val + "" + v2.val);
			Value r = new Value((v1.negative ^ v2.negative) ^ temR.negative, temR.val);
			return r;
		}
		
		private Value mul(Value v1, char c){
			Value temR = hashMap.get(v1.val + "" + c);
			Value r = new Value(v1.negative ^ temR.negative, temR.val);
			return r;
		}
		
		private Value ex(Value val, long x){
			if (x %4 == 0){
				return new Value(false, '1');
			} else if (x %4 == 1){
				return mul(val, '1');
			} else if (x %4 == 2){
				return mul(val, val);
			} else {
				return mul(val, mul(val, val));
			}
		}
		
	}


}
