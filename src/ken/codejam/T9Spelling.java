package ken.codejam;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;
import ken.codejam.utils.ReadWriteProblem;

public class T9Spelling extends AutoParseInputProblem{

	public T9Spelling(AutoParseTestCase testcase) {
		super(testcase);
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new T9Spelling( new TestCase()).start();
	}

	
	public static class TestCase extends AutoParseTestCase {
		public String line;
		private HashMap<String, String> letterToCode = new HashMap<String, String>();
		
		public TestCase(){
			super("@line");
			setupHashMap();
		}
		@Override
		public void process(int order, BufferedWriter output) {
			StringBuilder builder = new StringBuilder();
			char lastLetterCode = '/';
			for (int i = 0; i < line.length(); i ++){
				String code = letterToCode.get(line.charAt(i) + "");
				if (code == null) return;
				if (lastLetterCode == code.charAt(0)){
					builder.append(" ");
				} 
				builder.append(code);
				lastLetterCode = code.charAt(0);
			}
			try {
				output.write("Case #" + order + ": " + builder.toString() + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		@Override
		public void clear() {
			// TODO Auto-generated method stub
			
		}
		
		private void setupHashMap(){
			letterToCode.put("a", "2");
			letterToCode.put("b", "22");
			letterToCode.put("c", "222");
			
			letterToCode.put("d", "3");
			letterToCode.put("e", "33");
			letterToCode.put("f", "333");
			
			letterToCode.put("g", "4");
			letterToCode.put("h", "44");
			letterToCode.put("i", "444");
			
			letterToCode.put("j", "5");
			letterToCode.put("k", "55");
			letterToCode.put("l", "555");
			
			letterToCode.put("m", "6");
			letterToCode.put("n", "66");
			letterToCode.put("o", "666");
			
			letterToCode.put("p", "7");
			letterToCode.put("q", "77");
			letterToCode.put("r", "777");
			letterToCode.put("s", "7777");
			
			letterToCode.put("t", "8");
			letterToCode.put("u", "88");
			letterToCode.put("v", "888");
			
			letterToCode.put("w", "9");
			letterToCode.put("x", "99");
			letterToCode.put("y", "999");
			letterToCode.put("z", "9999");
			
			letterToCode.put(" ", "0");
			
		}
		
	}


	
}
