package ken.codejam.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

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
			arraySize = lineCountForArray = field1.getInt(this);
			
			//Create an array with size equal lineCountForArray
			varNameOfArray = pattern.substring(pattern.indexOf(')') + 1);
			Field field2 = getClass().getField(varNameOfArray);
			field2.set(this, Array.newInstance(field2.getType().getComponentType(), lineCountForArray));
		} 
		if (lineCountForArray > 0){
			Field field = getClass().getField(varNameOfArray);
			if (field.getType().getComponentType().getComponentType().equals(Character.class)){
				Character[] chars = ParserUtils.convertStringToArrayChar(lineInput);
				Array.set(field.get(this), arraySize - lineCountForArray, chars);
			} if (field.getType().getComponentType().getComponentType().isAssignableFrom(Number.class)){
				Object obj = ParserUtils.convertStringToArrayNumber(lineInput, " ", field.getType().getComponentType().getComponentType());
				Array.set(field.get(this), arraySize - lineCountForArray, obj);
			} 
			
			lineCountForArray --;
		} else {
			if (pattern.charAt(0) == '@'){//Add whole a line to a String. Ex: @anyStringName
				String varName = pattern.substring(1);
				try {
					Field field = getClass().getField(varName);
					field.set(this, lineInput);
				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
			} else if (pattern.charAt(0) == '#'){//Parse whole a line to an array. Ex: @anyArrayName
				String varName = pattern.substring(1);
				Field field = getClass().getField(varName);
				field.set(this, ParserUtils.convertStringToArrayNumber(lineInput, " ", field.getType().getComponentType()));
			} else {//Read discrete variables
				ParserUtils.convertLineStringByPattern(pattern, lineInput, " ", this);
			}
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
}
