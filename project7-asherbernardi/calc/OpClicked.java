package calc;

/**
 * OpClicked.java
 * 
 * The state during which you are entering the operator (+|-|*|/).
 * 
 * @author ASher Bernardi
 * CS 245, Wheaton College
 * April 26, 2017
 */
public class OpClicked extends ClickState {
	
	/**
	 * Constructor.
	 * @param organizer The Organizer that is keeping track of the data for this calculator.
	 */
	public OpClicked(Organizer organizer) { super(organizer);}

	/**
	 * Perform the clicking of a digit on the number pad.
	 * Signifies that the second operand is going to be inputed.
	 * @param face The CalculatorFace of this calculator.
	 * @param num The number that is being clicked.
	 * @return The SecondClicked state.
	 */
	@Override
	public ClickState clickNumber(CalculatorFace face, int num) {
		return organizer.second.clickNumber(face, num);
	}

	/**
	 * Perform the clicking of the decimal.
	 * Signifies that the second operand is going to be inputed.
	 * @param face The CalculatorFace of this calculator.
	 * @return A new Second.DotClicked.
	 */
	@Override
	public ClickState clickDot(CalculatorFace face) {
		return organizer.second.clickDot(face);
	}

	/**
	 * Perform the clicking of the plus operator.
	 * Simply replaces the operator with a plus operator.
	 * @param face The CalculatorFace of this calculator.
	 * @return This: we're still in the OpClicked state.
	 */
	@Override
	public ClickState clickPlus(CalculatorFace face) {
		operator = OpState.PlusState.getInstance();
		return this;
	}

	/**
	 * Perform the clicking of the minus operator.
	 * Simply replaces the operator with a minus operator.
	 * @param face The CalculatorFace of this calculator.
	 * @return This: we're still in the OpClicked state.
	 */
	@Override
	public ClickState clickMinus(CalculatorFace face) {
		operator = OpState.MinusState.getInstance();
		return this;
	}

	/**
	 * Perform the clicking of the times operator.
	 * Simply replaces the operator with a times operator.
	 * @param face The CalculatorFace of this calculator.
	 * @return This: we're still in the OpClicked state.
	 */
	@Override
	public ClickState clickTimes(CalculatorFace face) {
		operator = OpState.TimesState.getInstance();
		return this;
	}

	/**
	 * Perform the clicking of the divides operator.
	 * Simply replaces the operator with a divides operator.
	 * @param face The CalculatorFace of this calculator.
	 * @return This: we're still in the OpClicked state.
	 */
	@Override
	public ClickState clickDiv(CalculatorFace face) {
		operator = OpState.DivState.getInstance();
		return this;
	}

	/**
	 * Perform the clicking of the equals button.
	 * This just resets the calculator since no operation took place.
	 * @param face The CalculatorFace of this calculator.
	 * @return The InitState.
	 */
	@Override
	public ClickState clickEquals(CalculatorFace face) {
		return organizer.first.clickEquals(face);
	}

	/**
	 * Perform the clicking of the decimal.
	 * Signifies that the second operand is going to be inputed.
	 * @param face The CalculatorFace of this calculator.
	 * @return A new Second.Negative.
	 */
	@Override
	public ClickState clickPE(CalculatorFace face) {
		return organizer.second.clickPE(face);
	}

}
