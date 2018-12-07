package calc;

/**
 * InitState.java
 * 
 * The first state of the calculator, before you've clicked anything.
 * 
 * @author ASher Bernardi
 * CS 245, Wheaton College
 * April 26, 2017
 */
public class InitState extends ClickState {
	
	/**
	 * Constructor.
	 * @param organizer The Organizer that is keeping track of the data for this calculator.
	 */
	public InitState(Organizer organizer) { super(organizer);}

	/**
	 * Perform the clicking of a digit on the number pad.
	 * Add the number to the screen.
	 * @param face The CalculatorFace of this calculator.
	 * @param num The number that is being clicked.
	 * @return The FirstClicked Operator.
	 */
	@Override
	public ClickState clickNumber(CalculatorFace face, int num) {
		return organizer.first.clickNumber(face, num);
	}

	/**
	 * Perform the clicking of the decimal.
	 * @param face The CalculatorFace of this calculator.
	 * @return A new FirstClicked.DotClicked.
	 */
	@Override
	public ClickState clickDot(CalculatorFace face) {
		return organizer.first.clickDot(face);
	}

	/**
	 * Perform the clicking of the plus operator.
	 * If nothing has been entered into the calculator first, the plus
	 * operator will do nothing.
	 * @param face The CalculatorFace of this calculator.
	 * @return The InitState, because nothing changed.
	 */
	@Override
	public ClickState clickPlus(CalculatorFace face) {
		// do nothing
		return this;
	}

	/**
	 * Perform the clicking of the minus operator.
	 * If nothing has been entered into the calculator first, the minus
	 * operator will do nothing.
	 * @param face The CalculatorFace of this calculator.
	 * @return The InitState, because nothing changed.
	 */
	@Override
	public ClickState clickMinus(CalculatorFace face) {
		// do nothing
		return this;
	}

	/**
	 * Perform the clicking of the times operator.
	 * If nothing has been entered into the calculator first, the times
	 * operator will do nothing.
	 * @param face The CalculatorFace of this calculator.
	 * @return The InitState, because nothing changed.
	 */
	@Override
	public ClickState clickTimes(CalculatorFace face) {
		// do nothing
		return this;
	}

	/**
	 * Perform the clicking of the divide operator.
	 * If nothing has been entered into the calculator first, the divide
	 * operator will do nothing.
	 * @param face The CalculatorFace of this calculator.
	 * @return The InitState, because nothing changed.
	 */
	@Override
	public ClickState clickDiv(CalculatorFace face) {
		// do nothing
		return this;
	}

	/**
	 * Perform the clicking of the equals button.
	 * If nothing has been entered into the calculator first, the equals
	 * button will do nothing.
	 * @param face The CalculatorFace of this calculator.
	 * @return The InitState, because nothing changed.
	 */
	@Override
	public ClickState clickEquals(CalculatorFace face) {
		// do nothing.
		return this;
	}

	/**
	 * Perform the clicking of the PLUS/MINUS button.
	 * Makes the value on screen negative.
	 * @param face The CalculatorFace of this calculator.
	 * @return A new FirstClicked.Negative.
	 */
	@Override
	public ClickState clickPE(CalculatorFace face) {
		return organizer.first.clickPE(face);
	}

}
