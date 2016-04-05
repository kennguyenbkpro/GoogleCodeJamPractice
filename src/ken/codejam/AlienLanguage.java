package ken.codejam;

import java.io.BufferedWriter;
import java.util.ArrayList;

import ken.codejam.utils.ReadWriteProblem;

public class AlienLanguage extends ReadWriteProblem{
	private int D = 0, L = 0, N = 0;
	
	public static class TestCase extends BaseTestCase {
		public String pattern = null;
		public ArrayList<String> listSentences = new ArrayList<>();

		@Override
		public void process(int order, BufferedWriter output){
			solve1(order, output);
		}
		
		public void solve1(int order, BufferedWriter output) {
			
			//process patterns
			ArrayList<ArrayList<Character>> listElements = new ArrayList<>();
			int state = 2;//start
			ArrayList<Character> chars = new ArrayList<>();
			for (int i = 0; i < pattern.length(); i ++){
				char c = pattern.charAt(i);
				if (c == '('){
					state = 1;//open
					chars = new ArrayList<>();
				} else if (c == ')') {
					state = 2;//close
					listElements.add(chars);
					chars = new ArrayList<>();
				} else if (state == 1) {//after open
					chars.add(c);
				} else if (state == 2){//after close
					chars.add(c);
					listElements.add(chars);
					chars = new ArrayList<>();
				}
			}
			//Compare
			int result = 0;
			for (int i = 0; i < listSentences.size(); i ++){
				boolean ok = false;
				String s = listSentences.get(i);
				for (int j = 0; j < s.length(); j ++){
					ok = false;
					char c = s.charAt(j);
					ArrayList<Character> es = listElements.get(j);
					for (int k = 0; k < es.size(); k ++){
						if (es.get(k) == c){
							ok = true;
						}
					}
					if (ok == false) break;
				}
				if (ok == true) result ++;
			}
			print(order, output, "" + result);
		}
		
		

		@Override
		public void clear() {
			
		}
		
		
	}

	public static void main(String[] args) {
		new AlienLanguage().start();
	}

	@Override
	protected String getInputFile() {
		return "alien.in";
	}

	@Override
	protected String getOutputFile() {
		return "alien.out";
	}

	private int state = 0;//L D N
	private TestCase testCase = new TestCase();
	int order = 0;
	
	@Override
	public void onReadLine(String line, BufferedWriter output) {
		switch (state) {
		case 0:
		{
			String[] vals = line.split(" ");
			L = Integer.valueOf(vals[0]);
			D = Integer.valueOf(vals[1]);
			N = Integer.valueOf(vals[2]);
			state = 1;//Test case
		}
			break;
		case 1: //Test case
		{
			testCase.listSentences.add(line);
			if (testCase.listSentences.size() >= D){
				state = 2;//Patterns
			}
		}
			break;
		case 2: //Patterns
		{
			testCase.pattern = line;
			order ++;
			testCase.process(order, output);
		}
			break;

		default:
			break;
		}
	}

}
