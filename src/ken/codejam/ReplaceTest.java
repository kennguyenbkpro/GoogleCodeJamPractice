package ken.codejam;

public class ReplaceTest {

	public static void main(String[] args) {
		String mes =  " e oi em dang lam gi day hem e";
		mes = mes.replaceAll("( em* | em*(\\!|\\?|\\.)*)", " a ");
		System.out.println(mes);
	}

}
