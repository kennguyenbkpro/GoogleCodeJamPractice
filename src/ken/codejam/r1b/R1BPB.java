//package ken.codejam.r1b;
//
//import java.io.BufferedWriter;
//
//import ken.codejam.utils.AutoParseInputProblem;
//import ken.codejam.utils.AutoParseTestCase;
//
//public class R1BPB extends AutoParseInputProblem{
//
//	public R1BPB(){
//		super(new TestCase());
//	}
//	public static void main(String[] args) {
//		new R1BPB().start();
//	}
//	
//	public static class TestCase extends AutoParseTestCase {
//		public String[] S;
//		public TestCase(){
//			super("#S");
//		}
//
//		@Override
//		public void process(int order, BufferedWriter output) {
//			String C = S[0];
//			String J = S[1];
//			
//			int pC = -1, pJ = -1;
//			int vC = -1, vJ = -1;
//			
//			for (int i = 0; i < C.length(); i ++){
//				if (C.charAt(i) != '?'){
//					pC = i;
//					vC = C.charAt(i);
//					break;
//				}
//			}
//			
//			for (int i = 0; i < J.length(); i ++){
//				if (J.charAt(i) != '?'){
//					pJ = i;
//					vJ = J.charAt(i);
//					break;
//				}
//			}
//			
//			if (pC == -1 && pJ == -1){
//				C = C.replace('?', '0');
//				J = J.replace('?', '0');
//			} else if (pC == -1 || pJ == -1){
//				if (pC == -1){
//					C = J;
//				} else {
//					J = C;
//				}
//				C = C.replace('?', '0');
//				J = J.replace('?', '0');
//			} else {
//				if (pC == pJ){
//					if (vC == vJ){
//						C = C.replace('?', '0');
//						J = J.replace('?', '0');
//					} else {
//						if (vC > vJ){
//							String tem = C;
//							C = J; J = tem;
//							int tp = pC; pC = pJ; pJ = tp;
//						}
//						String C2 = C;
//						char[] cC = C2.toCharArray();
//						for (int i = pC + 1; i < cC.length; i ++){
//							if (cC[i] == '?')
//								cC[i] = '9';
//						}
//						C2 = new String(cC);
//						C2 = C2.replace('?', '0');
//						long val0 = Long.valueOf(C2);
//						
//						String J2 = J.replace('?', '0');
//						long val1 = Long.valueOf(J2);
//						long d1 = Math.abs(val1- val0);
//						
//						
//						if (pC > 0){
//							String J3 = J;
//							char[] cJ = J3.toCharArray();
//							for (int i = pJ + 1; i < cJ.length; i ++){
//								if (cJ[i] == '?')
//									cJ[i] = '9';
//							}
//							J3 = new String(cJ);
//							J3 = J3.replace('?', '0');
//							long val2 = Long.valueOf(J3);
//							
//							String C3 = C;
//							cC = C3.toCharArray();
//							if (cC[pC - 1] == '?')
//								cC[pC - 1] = '1';
//							C3 = new String(cC);
//							C3 = C3.replace('?', '0');
//							long val3 = Long.valueOf(C3);
//							long d2 = Math.abs(val3- val2);
//							if (d2 < d1){
//								C = C3; J = J3;
//							} else {
//								C = C2; J = J2;
//							} 
//						} else {
//							C = C2; J = J2;
//						}
//						
//						
//						
//						
////						J = J.replace('?', '0');
////						if (pJ > 0){
////							char[] cJ = J.toCharArray();
////							long val1 = Long.valueOf(new String(cJ));
////							cJ[pJ - 1] = '1';
////							long val2 = Long.valueOf(new String(cJ));
////							if (Math.abs(val1 - val0) <= Math.abs(val2 - val0)){
////								cJ[pJ - 1] = '0';
////							}
////							J = new String(cJ);
////						}
//
//						if (vC > vJ){
//							String tem = C;
//							C = J; J = tem;
//						}
//								
//					}
//				} else {
//					
//					char[] cC = C.toCharArray();
//					char[] cJ = J.toCharArray();
//					for (int i = 0; i < cC.length; i ++){
//						if (cJ[i] != '?' && cC[i] == '?')
//							cC[i] = cJ[i];
//						if (cC[i] != '?' && cJ[i] == '?')
//							cJ[i] = cC[i];
//					}
//					J = new String(cJ);
//					C = new String(cC);
//					C = C.replace('?', '0');
//					J = J.replace('?', '0');
//				}
//			}
//			
//			print(order, output, C + " " + J); 
//		}
//		
//		
//		
//		
//
//		@Override
//		public void clear() {
//			// TODO Auto-generated method stub
//			
//		}
//	}
//
//}