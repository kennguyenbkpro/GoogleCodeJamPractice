package ken.codejam.utils;

public class AutoParseInputProblem extends FirstLineNumOfTCProblem{

	public AutoParseInputProblem(AutoParseTestCase testcase) {
		super(testcase);
	}

	@Override
	public boolean onReadTestCase(BaseTestCase testcase, String line) {
		try {
			return ((AutoParseTestCase) testcase).parser(line);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

}
