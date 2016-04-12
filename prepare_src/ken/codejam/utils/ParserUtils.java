package ken.codejam.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ParserUtils{

	
	@SuppressWarnings("unchecked")
	public static <T> T[] convertStringToArrayNumber(String line, String splitter, Class<T> outClass){
		try {
			String[] val = line.split(splitter);
			T[] result = (T[]) Array.newInstance(outClass, val.length);
			for (int i = 0; i < val.length; i ++){
				Method valueOfMethof = outClass.getMethod("valueOf", String.class);
				result[i] = (T) valueOfMethof.invoke(outClass, val[i]);
			}
			return result;
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static Character[] convertStringToArrayChar(String line){
		Character[] chars = new Character[line.length()];
		for (int i = 0; i < line.length(); i ++){
			chars[i] = line.charAt(i);
		}
		return chars;
	}

	
	public static void convertLineStringByPattern(String pattern, String line, String splitter, Object obj) throws Exception{
		String[] valPattern = pattern.split(splitter);
		String[] valInput = line.split(splitter);
		for (int j = 0; j < valPattern.length; j++){
			Field field = obj.getClass().getField(valPattern[j]);
			Method valueOfMethof = field.getType().getMethod("valueOf", String.class);
			field.set(obj, valueOfMethof.invoke(field.getType(), valInput[j]));
		}
	}
}
