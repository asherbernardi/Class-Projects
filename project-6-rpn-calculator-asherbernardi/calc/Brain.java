package calc;

import java.util.LinkedList;

public class Brain {
	/**
	 * The CalculatorFace that all the operations will be made on.
	 */
	private CalculatorFace face;
	
	/**
	 * The queue that will keep track of RPN.
	 */
	private LinkedList<Integer> stack;
	
	/**
	 * A field to hold what is currently being displayed.
	 */
	private String onScreen;
	
	/**
	 * Constructor to pass in the correct CalculatorFace.
	 * @param face
	 */
	public Brain(CalculatorFace face) {
		this.face = face;
		stack = new LinkedList<Integer>();
		onScreen = "";
	}
	
	/**
	 * Add a number to the screen.
	 * @param num
	 */
	public void clickNumber(int num) {
		onScreen += num;
		face.writeToScreen(onScreen);
	}
	
	/**
	 * Adds what is on the screen to the stack.
	 */
	public void clickDot() {
		if (!onScreen.isEmpty())
			stack.push(Integer.parseInt(onScreen));
		onScreen = "";
	}
	
	/**
	 * Adds the two latest elements of the stack.
	 * If what is on the screen hasn't been added to the stack yet,
	 * it is pushed before the operation.
	 * Pop both elements to do the operation, then push the result.
	 */
	public void clickPlus() {
		clickDot();
		int last = stack.pop();
		int first = stack.pop();
		int result = first + last; // Calculation
		onScreen = "" + result;
		face.writeToScreen(onScreen);
		onScreen = "";
		stack.push(result);
	}

	/**
	 * Finds the difference of the two latest elements of the stack.
	 * If what is on the screen hasn't been added to the stack yet,
	 * it is pushed before the operation.
	 * Pop both elements to do the operation, then push the result.
	 */
	public void clickMinus() {
		clickDot();
		int last = stack.pop();
		int first = stack.pop();
		int result = first - last; // Calculation
		onScreen = "" + result;
		face.writeToScreen(onScreen);
		onScreen = "";
		stack.push(result);	
	}

	/**
	 * Multiplies the two latest elements of the stack.
	 * If what is on the screen hasn't been added to the stack yet,
	 * it is pushed before the operation.
	 * Pop both elements to do the operation, then push the result.
	 */
	public void clickTimes() {
		clickDot();
		int last = stack.pop();
		int first = stack.pop();
		int result = first * last; // Calculation
		onScreen = "" + result;
		face.writeToScreen(onScreen);
		onScreen = "";
		stack.push(result);
	}

	/**
	 * Divides the two latest elements of the stack.
	 * If what is on the screen hasn't been added to the stack yet,
	 * it is pushed before the operation.
	 * Pop both elements to do the operation, then push the result.
	 */
	public void clickDiv() {
		clickDot();
		int last = stack.pop();
		int first = stack.pop();
		int result = first / last; // Calculation
		onScreen = "" + result;
		face.writeToScreen(onScreen);
		onScreen = "";
		stack.push(result);
	}

	/**
	 * Completely resets the calculator.
	 */
	public void clear() {
		stack.clear();
		onScreen = "";
		face.writeToScreen(onScreen);
	}
	
	/**
	 * Checks whether operator buttons should apply. They shouldn't do
	 * anything if there's only one thing in the stack.
	 * @return Whether operators buttons should do anything.
	 */
	public boolean canOp() {
		return stack.size() > 1;
	}
	
}
