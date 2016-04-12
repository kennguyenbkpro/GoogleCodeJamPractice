package ken.codejam;

import java.io.BufferedWriter;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class HairCut extends AutoParseInputProblem{

	public HairCut(){
		super(new TestCase());
	}
	public static void main(String[] args) {
		new HairCut().start();
	}
	
	public static class TestCase extends AutoParseTestCase {
		public Integer B;
		public Integer N;
		public Integer[] M;
		
		public Baber[] babers;
		
		static class Baber {
			public int pos;
			public int t;
			public int max;
			public Baber(int pos, int t, int max) {
				super();
				this.pos = pos;
				this.max = max;
				this.t = t;
			}
			
		}
		
		public TestCase(){
			super("B N\n"
					+ "#M");
		}
		
		public long countServedCustomers(long T) {
			if (T < 0) return 0;
			long served_customers = 0;
			for (int barber = 0; barber < B; barber++)
				served_customers += T / M[barber] + 1;
			return served_customers;
		}
		public int fastGetBarberNumber(int N) {
			long low = -1, high = 100001L*N;
			while (low + 1 < high) {
				long mid = (low + high) / 2;
				if (countServedCustomers(mid) < N)
					low = mid;
				else
					high = mid;
			}
			long T = high;
			long customers_served_before = 
					countServedCustomers(T - 1);
			long customers_to_be_served = 
					N - customers_served_before;
			System.out.println(customers_served_before + " " + customers_to_be_served);
			for (int barber = 0; barber < B; barber++) 
				// Is the barber available at time T?
				if (T % M[barber] == 0) {
					customers_to_be_served--;
					if (customers_to_be_served == 0)
						return barber;
				}
			return -1;
		}
		
		
		@Override
		public void process(int order, BufferedWriter output) {
			print(order, output, "" + (1 + fastGetBarberNumber(N)));
//			print(order, output, "" + (1 + slowGetBarberNumber()));
		}
		
		int slowGetBarberNumber(){
			int p = 1;
			for (int m: M){
				p = p * m;
			}
			int n = 0;
			for (int m : M){
				n += p/m;
			}
			
			n = (N - 1) % n + 1;
			System.out.println(n + " " + p);
			
			long T = 0;
			while (true){
				long mint = 100001;
				for (int i = 0; i < B; i ++){
					long t = T % M[i];
					if (M[i] - t < mint){
						mint = M[i] - t;
					}
					if (t == 0){
						n --;
						if (n == 0) return i;
					}
				}
				T += mint;
			}
		}
		
		

		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
	}

}
