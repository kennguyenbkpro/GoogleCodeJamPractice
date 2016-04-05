package ken.codejam;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import ken.codejam.utils.FirstLineNumOfTCProblem;
import ken.codejam.utils.ClassUtils.Pair;



public class MilkShake extends FirstLineNumOfTCProblem{
	
	

	public static final int STATE_FLA = 1;
	public static final int STATE_CUST = 2;
	public static final int STATE_FAV = 3;
	
	private int state = STATE_FLA;
	
	public static class TestCase extends BaseTestCase{
		public int flavor = 0;
		public ArrayList<Customer> customers = new ArrayList<>();
		
		public void clear(){
			customers.clear();
		}
		
		public void process(int order, BufferedWriter output){
			int[] result = new int[flavor];
			for (int i = 0; i < result.length; i ++){
				result[i] = 0;
			}
			
			boolean isLoop = true;
			while (isLoop){
				isLoop = false;
				for (Customer customer : customers){
					if (customer.isSastified) continue;
					if (customer.numFav == 0){
						print(order, output, "IMPOSSIBLE");
						return;
					} else if (customer.numFav == 1){
						isLoop = true;
						Pair<Integer, Integer> val = customer.getOnlyPair();
						clearColumn(val.getKey(), val.getValue());
						customer.isSastified = true;
						result[val.getKey()] = val.getValue();
					}
				}
			}
			
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < flavor - 1; i ++){
				builder.append(result[i]).append(" ");
			}
			builder.append(result[flavor - 1]);
			
			print(order, output, builder.toString());
		}
		
		private void clearColumn(int col, int value){
			for (Customer customer : customers){
				customer.clearColumn(col, value);
			}
		}
	}
	
	public static class Customer {
		public HashMap<Integer, Integer> mapFavorite = new HashMap<>();
		public boolean isSastified = false;
		public int numFav = 0;
		
		public void setValue(String line, int flavor){
			String[] values = line.split(" ");
			numFav = Integer.valueOf(values[0]);
			for (int i = 1; i < values.length - 1; i += 2){
				mapFavorite.put(Integer.valueOf(values[i]) - 1, Integer.valueOf(values[i + 1]));
			}
		}
		
		public void clearColumn(int col, int value){
			if (isSastified) return;
			if (mapFavorite.containsKey(col)){
				if (value == mapFavorite.get(col)){
					isSastified = true;
				} else {
					mapFavorite.remove(col);
					numFav --;
				}
			}
		}
		
		public Pair<Integer, Integer> getOnlyPair(){
			for (Entry<Integer, Integer> entry : mapFavorite.entrySet()){
				return new Pair<Integer, Integer>(entry.getKey(), entry.getValue());
			}
			return null;
		}
	}

//	private TestCase mTestCase = new TestCase();
	private int numCustomer = 0;
	
	public static void main(String[] args) {
		new MilkShake().start();
	}
	
	public MilkShake() {
		super(new TestCase());
	}
	
	@Override
	public boolean onReadTestCase(BaseTestCase testcase, String line) {
		switch (state) {
		case STATE_FLA:
			((TestCase) testcase).flavor = Integer.valueOf(line);
			state = STATE_CUST;
			break;
			
		case STATE_CUST:
			numCustomer = Integer.valueOf(line);
			state = STATE_FAV;
			break;
			
		case STATE_FAV:
			{
				Customer customer = new Customer();
				customer.setValue(line, ((TestCase) testcase).flavor);
				((TestCase) testcase).customers.add(customer);
				
				numCustomer --;
				if (numCustomer <= 0){
					state = STATE_FLA;
					return true;
				}
				break;
				
			}
		default:
			break;
		}
		return false;
	}

	@Override
	protected String getInputFile() {
		return "milkshake.in";
	}

	@Override
	protected String getOutputFile() {
		return "milkshake.out";
	}

}
