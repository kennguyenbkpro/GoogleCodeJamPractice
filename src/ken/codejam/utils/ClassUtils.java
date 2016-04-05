package ken.codejam.utils;

public class ClassUtils {
	public static class Pair<Key ,Value> {
		Key mKey;
		Value mValue;
		public Pair(Key key, Value value){
			this.mKey = key;
			this.mValue = value;
		}
		public Key getKey(){
			return mKey;
		}
		public Value getValue(){
			return mValue;
		}
		
	}
}
