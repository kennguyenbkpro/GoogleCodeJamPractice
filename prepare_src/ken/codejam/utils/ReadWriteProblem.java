package ken.codejam.utils;

import java.io.BufferedWriter;
import java.io.IOException;

import ken.codejam.MilkShake.Customer;
import ken.codejam.MilkShake.TestCase;
import ken.codejam.utils.FileUtils.OnReadFileListener;
import ken.codejam.utils.FileUtils.OnWriteFileListener;

public abstract class ReadWriteProblem implements OnWriteFileListener, OnReadFileListener  {
	
	public static abstract class BaseTestCase {
		public abstract void process(int order, BufferedWriter output);
		public abstract void clear();
		public void print(int order, BufferedWriter output, String mes){
			try {
				output.write("Case #" + order + ": " + mes + "\n");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static final int BASE_STATE_NUMTEST = 0;
	public static final int BASE_STATE_TESTCASE = 1;
	
	
	
	public ReadWriteProblem(){}
	
	
	
	
	protected abstract String getInputFile();
	protected abstract String getOutputFile();
	
	public void start(){
		System.out.println("Start " + getOutputFile());
		FileUtils.writeLineToFile(getOutputFile(), this);
		System.out.println("Done!");
	}
	
	public void onReadLine(String line){}
	
	public abstract void onReadLine(String line, BufferedWriter output);
	
	
	public void onWriteFile(BufferedWriter output) {
		FileUtils.readFileByLine(this, getInputFile(), output);
	}
}
