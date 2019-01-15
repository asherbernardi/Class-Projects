package test;

import impl.FixCapStack;


public class FCSTest extends StackTest {

    public static int cap = 100;
    
    protected void reset() {
        testStack = new FixCapStack<String>(cap);
    }

	@Override
	protected void resetInt() {
		testStackInt = new FixCapStack<Integer>(cap);
	}


}
