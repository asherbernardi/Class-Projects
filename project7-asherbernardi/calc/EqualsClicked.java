package calc;

/**
 * EqualsClicked.java
 * 
 * The state in which you have just clicked equals.
 * 
 * @author ASher Bernardi
 * CS 245, Wheaton College
 * April 26, 2017
 */
public class EqualsClicked extends ClickState {

	/**
	 * Constructor.
	 * @param organizer The Organizer that is keeping track of the data for this calculator.
	 */
	public EqualsClicked(Organizer organizer) { super(organizer);}

	/**
	 * Perform the clicking of a digit on the number pad.
	 * Since equals has just been clicked, reset the calculator, and treat it like InitState.
	 * @param face The CalculatorFace of this calculator.
	 * @param num The number that is being clicked.
	 * @return The FirstClicked state.
	 */
	@Override
	public ClickState clickNumber(CalculatorFace face, int num) {
		onScreen = "";
		return organizer.init.clickNumber(face, num);
	}

	/**
	 * Perform the clicking of the decimal.
	 * Since equals has just been clicked, reset the calculator, and treat it like InitState.
	 * @param face The CalculatorFace of this calculator.
	 * @return A new First.DotClicked.
	 */
	@Override
	public ClickState clickDot(CalculatorFace face) {
		onScreen = "";
		return organizer.init.clickDot(face);
	}

	/**
	 * Perform the clicking of the plus operator.
	 * Put what is on the screen into the left variable,
	 * and make the operator a plusState.
	 * @param face The CalculatorFace of this calculator.
	 * @return The operator state.
	 */
	@Override
	public ClickState clickPlus(CalculatorFace face) {
		return organizer.first.clickPlus(face);
	}

	/**
	 * Perform the clicking of the plus operator.
	 * Put what is on the screen into the left variable,
	 * and make the operator a minusState.
	 * @param face The CalculatorFace of this calculator.
	 * @return The operator state.
	 */
	@Override
	public ClickState clickMinus(CalculatorFace face) {
		return organizer.first.clickMinus(face);
	}

	/**
	 * Perform the clicking of the plus operator.
	 * Put what is on the screen into the left variable,
	 * and make the operator a timesState.
	 * @param face The CalculatorFace of this calculator.
	 * @return The operator state.
	 */
	@Override
	public ClickState clickTimes(CalculatorFace face) {
		return organizer.first.clickTimes(face);
	}

	/**
	 * Perform the clicking of the plus operator.
	 * Put what is on the screen into the left variable,
	 * and make the operator a plusState.
	 * @param face The CalculatorFace of this calculator.
	 * @return The operator state.
	 */
	@Override
	public ClickState clickDiv(CalculatorFace face) {
		return organizer.first.clickDiv(face);
	}

	/**
	 * Perform the clicking of the equals button.
	 * Do nothing since no operation took place.
	 * @param face The CalculatorFace of this calculator.
	 * @return This: nothing changed.
	 */
	@Override
	public ClickState clickEquals(CalculatorFace face) {
		return this;
	}

	/**
	 * Perform the clicking of the PLUS/MINUS button.
	 * Reset the calculator and start inputting the first operand for the next operation.
	 * @param face The CalculatorFace of this calculator.
	 * @return A new Negative.
	 */
	@Override
	public ClickState clickPE(CalculatorFace face) {
		onScreen = "";
		return organizer.init.clickPE(face);
	}

}
