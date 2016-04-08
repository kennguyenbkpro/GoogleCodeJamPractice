package ken.codejam.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import ken.codejam.utils.ReadWriteProblem.BaseTestCase;

public abstract class AutoParseTestCase extends BaseTestCase{

	private String[] patterns;
	private int patternLineCount = 0;
	
	private int arraySize = 0;
	private int lineCountForArray = 0;
	private String varNameOfArray;
	
	
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
		String pattern = patterns[patternLineCount];
		if (lineCountForArray == 0 && pattern.charAt(0) == '&'){
			//Get number of Line from prev variable
			String varNameOfNumLine = pattern.substring(pattern.indexOf('(') + 1, pattern.indexOf(')'));
			Field field1 = getClass().getField(varNameOfNumLine);
			arraySize = lineCountForArray = (int) field1.get(this);
			
			//Create an array with size equal lineCountForArray
			varNameOfArray = pattern.substring(pattern.indexOf(')') + 1);
			Field field2 = getClass().getField(varNameOfArray);
			field2.set(this, Array.newInstance(field2.getType().getComponentType(), lineCountForArray));
		} 
		if (lineCountForArray > 0){
			Field field = getClass().getField(varNameOfArray);
			Class array1DType = field.getType().getComponentType();
			if (array1DType.getComponentType() != null && array1DType.getComponentType().equals(Character.class)){//==============Char[][]
				Character[] chars = ParserUtils.convertStringToArrayChar(lineInput);
				Array.set(field.get(this), arraySize - lineCountForArray, chars);
			} else if (array1DType.equals(String.class)){//=======================================================================String[]
				Array.set(field.get(this), arraySize - lineCountForArray, lineInput);
			} else if (array1DType.getComponentType() != null && array1DType.getComponentType().isAssignableFrom(Number.class)){//Number[][]
				Object obj = ParserUtils.convertStringToArrayNumber(lineInput, " ", field.getType().getComponentType().getComponentType());
				Array.set(field.get(this), arraySize - lineCountForArray, obj);
			} else if (array1DType.getComponentType() != null && array1DType.getComponentType().equals(String.class)){//============String[][]
				Object obj = lineInput.split(" ");
				Array.set(field.get(this), arraySize - lineCountForArray, obj);
			} 
			
			lineCountForArray --;
		} else {
			parseSingleLine(pattern, lineInput);
		}
		if (lineCountForArray == 0){
			patternLineCount ++;
		}
		if (patternLineCount == patterns.length){
			patternLineCount = 0;
			return true;//Finish read data for this test case
		}
		return false;
	}
	
	void parseSingleLine(String pattern, String lineInput) throws Exception{
		if (pattern.charAt(0) == '@'){//================================================Add whole a line to a String. Ex: @anyStringName
			String varName = pattern.substring(1);
			try {
				Field field = getClass().getField(varName);
				field.set(this, lineInput);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		} else if (pattern.charAt(0) == '#'){//=========================================Parse whole a line to an array. Ex: @anyArrayName
			String varName = pattern.substring(1);
			Field field = getClass().getField(varName);
			Class array1DType = field.getType().getComponentType();
			try {
				array1DType.getMethod("valueOf", String.class);
				field.set(this, ParserUtils.convertStringToArrayNumber(lineInput, " ", array1DType));
			} catch (NoSuchMethodException e){
				if (array1DType != null &&  array1DType.equals(Character.class)){//====================Char[]
					field.set(this, ParserUtils.convertStringToArrayChar(lineInput));
				}  else if (array1DType != null &&  array1DType.equals(String.class)){//================String[] in a line
					field.set(this, lineInput.split(" "));
				}
			}
			
		} else {//========================================================================Read discrete variables
			if (pattern.contains("@") || pattern.contains("#")){
				ParserUtils.convertLineStringByPattern(
						pattern.substring(0, pattern.indexOf(' ')), 
						lineInput.substring(0, lineInput.indexOf(' ')), 
						" ", this);
				parseSingleLine(pattern.substring(pattern.indexOf(' ') + 1), lineInput.substring(lineInput.indexOf(' ') + 1));
			} else {
				ParserUtils.convertLineStringByPattern(pattern, lineInput, " ", this);
			}
		}
	}
}
