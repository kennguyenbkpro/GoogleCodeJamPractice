package ken.codejam;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.*;

import ken.codejam.utils.ReadWriteProblem;

public class T9Spelling extends ReadWriteProblem{

	private static boolean isReadTestCase = false;
	private static int mOrder = 0;
	
	private HashMap<String, String> letterToCode = new HashMap<String, String>();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start..");
		T9Spelling spelling = new T9Spelling();
		spelling.setupHashMap();
		spelling.start();
		System.out.println("Done!");
	}

	public void onReadLine(String line, BufferedWriter output) {
		if (isReadTestCase){
			mOrder ++;
			processTestCase(line, mOrder, output);
		} else {
			isReadTestCase = true;
		}
	}
	
	private void processTestCase(String line, int order, BufferedWriter output){
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

	protected String getInputFile() {
		return "t9.txt";
	}

	protected String getOutputFile() {
		return "t9_out.txt";
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
