package ken.codejam.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class FileUtils {
	public interface OnReadFileListener {
		public void onReadLine(String line);
		public void onReadLine(String line, BufferedWriter output);
	}
	
	public interface OnWriteFileListener {
		public void onWriteFile(BufferedWriter output);
	}
	
	public static void readFileByLine(OnReadFileListener listener, String filePath, BufferedWriter output){
		try {
			File file = new File(filePath);
            BufferedReader input = new BufferedReader(new FileReader(file));
            try {
            	while(true){
            		String temp = input.readLine();
            		if (temp == null) break;
            		if (listener != null){
            			if (output != null){
            				try {
            					listener.onReadLine(temp, output);
            				} catch (Exception e){
            					e.printStackTrace();
            				}
            			} else {
            				listener.onReadLine(temp);
            			}
            		}
            	}
            } finally {
                input.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	public static void readFileByLine(OnReadFileListener listener, String filePath){
		readFileByLine(listener, filePath, null);
	}
	
	public static void writeLineToFile(String filePath, OnWriteFileListener listener){
		try {
			File file = new File(filePath);
			BufferedWriter output = new BufferedWriter(new FileWriter(file));
			try {
				listener.onWriteFile(output);
			} finally {
				output.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
