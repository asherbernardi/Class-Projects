package calc;

/**
 * Organizer.java
 * 
 * The context of the calculator states.
 * It organizes all the data but does no calculations.
 * 
 * @author ASher Bernardi
 * CS 245, Wheaton College
 * April 23, 2017
 */
public class Organizer {
	
	/**
	 * The value of the first operand in a simple operation.
	 */
	protected double left;
	
	/**
	 * The value of the second operand in a simple operation.
	 */
	protected double right;
	
	/**
	 * Keeps track of an instance of InitState.
	 * Protected so that the ClickStates can use it.
	 */
	protected InitState init = new InitState(this);
	
	/**
	 * Keeps track of an instance of FirstClicked.
	 * Protected so that the ClickStates can use it.
	 */
	protected FirstClicked first = new FirstClicked(this);

	/**
	 * Keeps track of an instance of OpClicked.
	 * Protected so that the ClickStates can use it.
	 */
	protected OpClicked op = new OpClicked(this);

	/**
	 * Keeps track of an instance of SecondClicked.
	 * Protected so that the ClickStates can use it.
	 */
	protected SecondClicked second = new SecondClicked(this);

	/**
	 * Keeps track of an instance of EqualsClicked.
	 * Protected so that the ClickStates can use it.
	 */
	protected EqualsClicked equals = new EqualsClicked(this);
	
	/**
	 * The state of the calculator.
	 */
	private ClickState state;
	
	/**
	 * The CalculatorFace on which all operations will be done.
	 */
	protected CalculatorFace face;
	
	/**
	 * Constructor to pass in the correct CalculatorFace.
	 * @param face
	 */
	public Organizer(CalculatorFace face) {
		this.face = face;
		// Initialize the Calculator, clearing it for good measure.
		state = init.clickC(face);
	}
	
	/**
	 * The procedure of clicking the C button.
	 */
	public void clickC() {
		state = state.clickC(face);
	}

	/**
	 * The procedure of clicking a digit on the number pad.
	 * @param num The number that is being clicked.
	 */
	public void clickNumber(int num) {
		state = state.clickNumber(face, num);
	}

	/**
	 * The procedure of clicking the decimal button.
	 */
	public void clickDot() {
		state = state.clickDot(face);
	}

	/**
	 * The procedure of clicking the plus button.
	 */
	public void clickPlus() {
		state = state.clickPlus(face);
	}

	/**
	 * The procedure of clicking the minus button.
	 */
	public void clickMinus() {
		state = state.clickMinus(face);
	}

	/**
	 * The procedure of clicking the multiplication button.
	 */
	public void clickTimes() {
		state = state.clickTimes(face);
	}

	/**
	 * The procedure of clicking the division button.
	 */
	public void clickDiv() {
		state = state.clickDiv(face);
	}
	
	/**
	 * The procedure of clicking the equals button.
	 */
	public void clickEquals() {
		state = state.clickEquals(face);
	}
	
	/**
	 * The procedure of clicking the plus/minus button.
	 */
	public void clickPE() {
		state = state.clickPE(face);
	}
}
