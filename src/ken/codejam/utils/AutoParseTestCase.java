package ken.codejam.utils;

import java.io.BufferedWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import ken.codejam.utils.ReadWriteProblem.BaseTestCase;

public abstract class AutoParseTestCase extends BaseTestCase{

	private String[] patterns;
	private int inputLineCount = 0;
	
	public AutoParseTestCase(String pattern){
		patterns = pattern.split("\n");
	}
	
	/**
	 * 
	 * @param lineInput
	 * @return whether finish reading a test case or not
	 * @throws Exception
	 */
	public boolean parser(String lineInput) throws Exception{
		if (patterns[inputLineCount].charAt(0) == '@'){//Add whole a line to a String
			String varName = patterns[inputLineCount].substring(1);
			try {
				Field field = getClass().getField(varName);
				field.set(this, lineInput);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} else if (patterns[inputLineCount].charAt(0) == '#'){//Parse whole a line to an array
			String varName = patterns[inputLineCount].substring(1);
			Field field = getClass().getField(varName);
			field.set(this, ParserUtils.convertStringToArrayNumber(lineInput, " ", field.getType().getComponentType()));
		} else {//Read discrete variables
			String[] valPattern = patterns[inputLineCount].split(" ");
			String[] valInput = lineInput.split(" ");
			for (int j = 0; j < valPattern.length; j++){
				Field field = getClass().getField(valPattern[j]);
				Method valueOfMethof = field.getType().getMethod("valueOf", String.class);
				field.set(this, valueOfMethof.invoke(field.getType(), valInput[j]));
			}
		}
		inputLineCount ++;
		if (inputLineCount == patterns.length){
			inputLineCount = 0;
			return true;//Finish read data for this test case
		}
		return false;
	}
}
