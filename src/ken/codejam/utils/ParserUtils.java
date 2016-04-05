package ken.codejam.utils;

import java.lang.reflect.Array;
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
}
