package ken.codejam.utils;

import java.io.BufferedWriter;

public abstract class FirstLineNumOfTCProblem extends ReadWriteProblem{

	private int mOrder = 0;
	private int state = BASE_STATE_NUMTEST;
	private BaseTestCase mTestCase = null;
	
	public FirstLineNumOfTCProblem (BaseTestCase testcase){
		mTestCase = testcase;
	}

	public void onReadLine(String line, BufferedWriter output){
		if (mTestCase == null) return;
		switch (state) {
		case BASE_STATE_NUMTEST:
			state = BASE_STATE_TESTCASE;
			mTestCase.clear();
			mOrder ++;
			break;

		case BASE_STATE_TESTCASE:
			if (onReadTestCase(mTestCase, line)){
				mTestCase.process(mOrder, output);
				mTestCase.clear();
				mOrder ++;
			}
			break;
		default:
			break;
		}
	}
	
	/**
	 * 
	 * @param line
	 * @return whether finish read testcase or not
	 */
	public abstract boolean onReadTestCase(BaseTestCase testcase, String line) ;
	
	@Override
	protected String getInputFile() {
		return getClass().getSimpleName() + ".in";
	}
	
	@Override
	protected String getOutputFile() {
		return getClass().getSimpleName() + ".out";
	}
}
