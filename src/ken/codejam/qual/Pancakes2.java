package ken.codejam.qual;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.Stack;

import ken.codejam.utils.AutoParseInputProblem;
import ken.codejam.utils.AutoParseTestCase;

public class Pancakes2 extends AutoParseInputProblem{
	public Pancakes2(){
		super(new TestCase());
	}

	public static void main(String[] args) {
		new Pancakes2().start();
	}
	
	public static class TestCase extends AutoParseTestCase {
		public Character[] data;
		public TestCase() {
			super("#data");
		}
		
		Stack<Integer> createStack(){
			Stack<Integer> stack = new Stack<>();
			for (int i = data.length - 1; i >= 0; i --){
				stack.push(data[i] == '+' ? 1 : 0);
			}
			return stack;
		}
		
		void flipStack(Stack<Integer> stack, int bot, int top){
			ArrayList<Integer> temList = new ArrayList<>();
			for (int i = bot; i < top; i ++){
				Integer val = stack.pop();
				val = (val + 1) % 2;
				temList.add(val);
			}
			for (int i = 0; i < temList.size(); i ++){
				stack.push(temList.get(i));
			}
		}
		
		int solve(Stack<Integer> stack, int bot, int top){
			if (bot >= top) return 0;
			if (stack.get(bot) == 1){
				return solve(stack, bot + 1, top);
			} else {
				int nB = 0, nT = 0;
				for (int i = bot; i < top; i ++){
					if (stack.get(i) == 0) nB ++;
					else break;
				}
				if (nB == top - bot){
					flipStack(stack, bot, top);
					return 1;
				}
				for (int i = top - 1; i >= bot; i--){
					if (stack.get(i) == 0) nT ++;
					else break;
				}
				if (nT > 0){
					flipStack(stack, bot, top);
					return 1 + solve(stack, bot + nT, top);
				} else {
					flipStack(stack, bot + nB, top);
					return 1 + solve(stack, bot, top);
				}
			}
		}

		@Override
		public void process(int order, BufferedWriter output) {
			Stack<Integer> stack = createStack();
			print(order, output, solve(stack, 0, stack.size()) + "");
		}

		@Override
		public void clear() {
		}
		
	}

}
