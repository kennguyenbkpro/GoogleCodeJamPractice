package ken.codejam;

import java.io.BufferedWriter;
import java.io.IOException;

import ken.codejam.utils.ReadWriteProblem;


public class ReverseWords extends ReadWriteProblem {
	
	public static final String INPUT_FILE = "reverse_words.txt";
	public static final String OUTPUT_FILE = "reverse_words_out.txt";

	private static boolean isReadTestCase = false;
	private static int mOrder = 0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Start..");
		new ReverseWords().start();
		System.out.println("Done!");
	}
	
	private void processTestCase(String line, int order, BufferedWriter output){
		String[] words = line.split(" ");
		StringBuilder builder = new StringBuilder();
		int l = words.length;
		for (int i = l - 1; i > 0; i --){
			builder.append(words[i]).append(" ");
		}
		if (l > 0){
			builder.append(words[0]).append("\n");
		}
		try {
			output.write("Case #" + order + ": " + builder.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public void onReadLine(String line, BufferedWriter output) {
		if (isReadTestCase){
			mOrder ++;
			processTestCase(line, mOrder, output);
		} else {
			isReadTestCase = true;
		}
	}

	protected String getInputFile() {
		return INPUT_FILE;
	}

	protected String getOutputFile() {
		return OUTPUT_FILE;
	}

}
