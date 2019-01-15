package calc;

public class Calculator {
	/**
	 * The CalculatorFace on which all operations will be done.
	 */
	private CalculatorFace face;
	
	/**
	 * To keep track of the last button pressed. Treat space as void.
	 */
	private char lastChar = ' ';
	
	/**
	 * To keep track of the last operator pressed. Treat space as void.
	 */
	private char lastOp = ' ';
	
	/**
	 * The value of the first operand in a simple operation.
	 */
	private double left;
	
	/**
	 * The value of the second operand in a simple operation.
	 */
	private double right;
	
	/**
	 * Keeps track of what is current displayed on the screen.
	 */
	private String onScreen = "";
	
	/**
	 * This variable is used to determine what will be printed on the screen next.
	 * If pressing a button should add to what's on the screen, it will be set to onScreen.
	 * If pressing a button should clear the screen, it will be set to "".
	 */
	private String nextOnScreen = "";

	/**
	 * Constructor
	 * @param f The CalculatorFace on which all operations will be performed.
	 */
	public Calculator(CalculatorFace f) {
		face = f;
	}
	
	/**
	 * Print digit that is passed through to the calculator.
	 * Do not print anything once the display reaches 15 characters.
	 * @param digit The number to be displayed.
	 */
	public void printNum(int digit) {
		if (nextOnScreen.length() < 15) {
			onScreen = nextOnScreen + digit;
			face.writeToScreen(onScreen);
			nextOnScreen = onScreen;
			// The ACSII index of the character of digit is digit + 48,
			// because integers start at 48.
			lastChar = (char) (digit + 48);
		}
	}
	
	/**
	 * Print a decimal point to the display.
	 * Don't not print if the display is already 15 characters long, or if
	 * the display already has a decimal point in it.
	 */
	public void printDot() {
		if (onScreen.length() < 15 && onScreen.indexOf(".") == -1) {
			onScreen = nextOnScreen + ".";
			face.writeToScreen(onScreen);
			nextOnScreen = onScreen;
			lastChar = '.';
		}
	}
	
	/**
	 * The process to occur if '+' is pressed.
	 * If no operator has been recently pressed, set lastOp to '+'.
	 * If '+' was just pressed, do nothing.
	 * If an operator has been recently pressed, treat the button like
	 * '=', then set up a new '+' operation.
	 */
	public void plus() {
		if (lastOp == ' ' && (Character.isDigit(lastChar) || lastChar == '=' || lastChar == CalculatorFace.PLUS_MINUS)) {
			left = getVal();
			lastOp = '+';
			lastChar = '+';
			
			nextOnScreen = "";
		}
		else if (lastChar == '+')
			;
		else {
			right = getVal();
			printResult();
			
			left = getVal();
			lastOp = '+';
			lastChar = '+';
			
			nextOnScreen = "";
		}
	}
	
	/**
	 * The process to occur if '-' is pressed.
	 * If no operator has been recently pressed, set lastOp to '-'.
	 * If '-' was just pressed, do nothing.
	 * If an operator has been recently pressed, treat the button like
	 * '=', then set up a new '-' operation.
	 */
	public void minus() {
		if (lastOp == ' ' && (Character.isDigit(lastChar) || lastChar == '=' || lastChar == CalculatorFace.PLUS_MINUS)) {
			left = getVal();
			lastOp = '-';
			lastChar = '-';
			
			nextOnScreen = "";
		}
		else if (lastChar == '-')
			;
		else {
			right = getVal();
			printResult();
			
			left = getVal();
			lastOp = '-';
			lastChar = '-';
			
			nextOnScreen = "";
		}
	}

	/**
	 * The process to occur if '*' is pressed.
	 * If no operator has been recently pressed, set lastOp to '*'.
	 * If '*' was just pressed, do nothing.
	 * If an operator has been recently pressed, treat the button like
	 * '=', then set up a new '*' operation.
	 */
	public void times() {
		if (lastOp == ' ' && (Character.isDigit(lastChar) || lastChar == '=' || lastChar == CalculatorFace.PLUS_MINUS)) {
			left = getVal();
			lastOp = '*';
			lastChar = '*';
			
			nextOnScreen = "";
		}
		else if (lastChar == '*')
			;
		else {
			right = getVal();
			printResult();
			
			left = getVal();
			lastOp = '*';
			lastChar = '*';
			
			nextOnScreen = "";
		}
	}
	
	/**
	 * The process to occur if '/' is pressed.
	 * If no operator has been recently pressed, set lastOp to '/'.
	 * If '/' was just pressed, do nothing.
	 * If an operator has been recently pressed, treat the button like
	 * '=', then set up a new '/' operation.
	 */
	public void divide() {
		if (lastOp == ' ' && (Character.isDigit(lastChar) || lastChar == '=' || lastChar == CalculatorFace.PLUS_MINUS)) {
			left = getVal();
			lastOp = '/';
			lastChar = '/';
			
			nextOnScreen = "";
		}
		else if (lastChar == '/')
			;
		else {
			right = getVal();
			printResult();
			
			left = getVal();
			lastOp = '/';
			lastChar = '/';
			
			nextOnScreen = "";
		}
	}
	
	/**
	 * Print the result of the operation.
	 * First take the current display and make that the right operand.
	 * Then display the result of the operation.
	 * Then 
	 */
	public void equals() {
		if (lastOp != ' ') {
			right = getVal();
			printResult();
			left = getVal();

			lastOp = ' ';
			lastChar = '=';
		}
	}
	
	/**
	 * Change the sign from positive to negative or vice-versa.
	 */
	public void signChange() {
		if (onScreen.length() < 15) {
			if (nextOnScreen.indexOf("-") != 0) {
				onScreen = "-" + nextOnScreen;
				face.writeToScreen(onScreen);
				nextOnScreen = onScreen;
				
				lastChar = CalculatorFace.PLUS_MINUS;
			}
			else {
				onScreen = nextOnScreen.substring(1, nextOnScreen.length());
				face.writeToScreen(onScreen);
				nextOnScreen = onScreen;
				
				lastChar = CalculatorFace.PLUS_MINUS;
			}
		}
	}
	
	/**
	 * Clear the display.
	 */
	public void clear() {
		onScreen = "";
		face.writeToScreen(onScreen);
		nextOnScreen = onScreen;
		lastOp = ' ';
		lastChar = ' ';
	}
	
	/**
	 * Get the value of the number displayed on the screen.
	 * @return a double value of what is displayed on the screen.
	 */
	private double getVal() {
		return Double.parseDouble(onScreen);
	}
	
	/**
	 * Display the result of the operation perform between left and right.
	 */
	private void printResult() {
		// The result of the operation.
		double result = 0.0;
		
		switch (lastOp) {
		case '+':
			result = left + right;
			break;
		case '-':
			result = left - right;
			break;
		case '*':
			result = left * right;
			break;
		case '/':
			result = left / right;
			break;
		}
		
		onScreen = "" + result;
		face.writeToScreen(onScreen);
		nextOnScreen = "";
	}
}
