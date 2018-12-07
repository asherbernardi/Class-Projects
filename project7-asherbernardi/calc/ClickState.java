package calc;

import java.text.DecimalFormat;

/**
 * ClickState.java
 * 
 * A state of the calculator.
 * 
 * @author ASher Bernardi
 * CS 245, Wheaton College
 * April 23, 2017
 */
public abstract class ClickState {
	
	/**
	 * Keeps track of what number is currently displayed on the screen.
	 */
	protected static String onScreen = "";
	
	/**
	 * A DecimalFormat for the display on the screen.
	 * It has 14 digits on the left and 13 on the right of the decimal.
	 * If the number is longer than 15 digits, it will be substringed later.
	 */
	private static DecimalFormat formatter = new DecimalFormat("#############0.#############");
	
	/**
	 * A state of the operator. Determine which operator will be performed.
	 */
	protected static OpState operator;
	
	/**
	 * The organizer that is keeping track of the variables in this calculator.
	 */
	protected Organizer organizer;

	/**
	 * Constructor.
	 * @param organizer The Organizer that is keeping track of the data for this calculator.
	 */
	public ClickState(Organizer organizer) {
		this.organizer = organizer;
	}
	
	/**
	 * Add a string to what is already on the onScreen.
	 * It keeps everything well formatted, and doesn't add anything past 14 digits.
	 * The display stays at 14 digits so that the PLUS/MINUS can be added at all times.
	 * @param face The CalculatorFace of this calculator.
	 * @param s The string to be added to the screen.
	 */
	public void write(CalculatorFace face, String s) {
		onScreen = String.format("%14s", onScreen.trim() + s).substring(0, 14).trim();
		face.writeToScreen(onScreen);
	}
	
	/**
	 * Display a double to the screen.
	 * It keeps everything well formatted.
	 * @param face The CalculatorFace of this calculator.
	 * @param d The number that should be displayed.
	 */
	public void decimalDisplay(CalculatorFace face, double d) {
		onScreen = String.format("%15s", formatter.format(d)).substring(0, 15).trim();
		face.writeToScreen(onScreen);
	}
	
	/**
	 * Adds a '-' symbol to the front of the display.
	 * @param face The CalculatorFace of this calculator.
	 */
	public void makeNegative(CalculatorFace face) {
		onScreen = "-" + onScreen.trim().replaceAll("-", "");
		face.writeToScreen(onScreen);
	}
	
	/**
	 * Removes a '-' symbol from the display.
	 * @param face The CalculatorFace of this calculator.
	 */
	public void makePositive(CalculatorFace face) {
		onScreen = onScreen.replaceAll("-", "");
		face.writeToScreen(onScreen);
	}
	
	/**
	 * Translates a string into a double.
	 * @param s The String to be translated.
	 * @return The double value that the String represents.
	 */
	public double getVal(String s) {
		return Double.parseDouble(s);
	}
	
	/**
	 * Performs the computation that has been recorded by the organizer and the OpStates.
	 * @param face The CalculatorFace of this calculator.
	 */
	public void compute(CalculatorFace face) {
		decimalDisplay(face, operator.performOperation(organizer.left, organizer.right));
	}
	
	/**
	 * Perform the clicking of a digit on the number pad.
	 * @param face The CalculatorFace of this calculator.
	 * @param num The number that is being clicked.
	 * @return A new state that corresponds to what was just clicked.
	 */
	public abstract ClickState clickNumber(CalculatorFace face, int num);
	
	/**
	 * Perform the clicking of the decimal.
	 * @param face The CalculatorFace of this calculator.
	 * @return A new state that corresponds to what was just clicked.
	 */
	public abstract ClickState clickDot(CalculatorFace face);
	
	/**
	 * Perform the clicking of the plus operator.
	 * @param face The CalculatorFace of this calculator.
	 * @return A new state that corresponds to what was just clicked.
	 */
	public abstract ClickState clickPlus(CalculatorFace face);
	
	/**
	 * Perform the clicking of the minus operator.
	 * @param face The CalculatorFace of this calculator.
	 * @return A new state that corresponds to what was just clicked.
	 */
	public abstract ClickState clickMinus(CalculatorFace face);
	
	/**
	 * Perform the clicking of the multiplication operator.
	 * @param face The CalculatorFace of this calculator.
	 * @return A new state that corresponds to what was just clicked.
	 */
	public abstract ClickState clickTimes(CalculatorFace face);
	
	/**
	 * Perform the clicking of the division operator.
	 * @param face The CalculatorFace of this calculator.
	 * @return A new state that corresponds to what was just clicked.
	 */
	public abstract ClickState clickDiv(CalculatorFace face);
	
	/**
	 * Perform the clicking of the C button.
	 * @param face The CalculatorFace of this calculator.
	 * @return A new state that corresponds to what was just clicked.
	 */
	public ClickState clickC(CalculatorFace face) {
		organizer.left = 0;
		organizer.right = 0;
		onScreen = "";
		face.writeToScreen("");
		return organizer.init;
	}
	
	/**
	 * Perform the clicking of the multiplication operator.
	 * @param face The CalculatorFace of this calculator.
	 * @return A new state that corresponds to what was just clicked.
	 */
	public abstract ClickState clickEquals(CalculatorFace face);
	
	/**
	 * Perform the clicking of the PLUS/MINUS button.
	 * @param face The CalculatorFace of this calculator.
	 * @return A new state that corresponds to what was just clicked.
	 */
	public abstract ClickState clickPE(CalculatorFace face);
	
}
