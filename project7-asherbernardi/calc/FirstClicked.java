package calc;

/**
 * FirstClicked.java
 * 
 * The state during which you are entering the first operand.
 * 
 * @author ASher Bernardi
 * CS 245, Wheaton College
 * April 26, 2017
 */
public class FirstClicked extends ClickState {
	
	/**
	 * Constructor.
	 * @param organizer The Organizer that is keeping track of the data for this calculator.
	 */
	public FirstClicked(Organizer organizer) { super(organizer);}

	/**
	 * Perform the clicking of a digit on the number pad.
	 * Add the number to the screen.
	 * @param face The CalculatorFace of this calculator.
	 * @param num The number that is being clicked.
	 * @return This: you are still entering the first operand.
	 */
	@Override
	public ClickState clickNumber(CalculatorFace face, int num) {
		write(face, "" + num);
		return this;
	}

	/**
	 * Perform the clicking of the decimal.
	 * @param face The CalculatorFace of this calculator.
	 * @return A new DotClicked.
	 */
	@Override
	public ClickState clickDot(CalculatorFace face) {
		write(face, ".");
		return this.new DotClicked(onScreen, organizer);
	}

	/**
	 * Perform the clicking of the plus operator.
	 * Put what is on the screen into the left variable,
	 * and make the operator a PlusState.
	 * @param face The CalculatorFace of this calculator.
	 * @return The operator state.
	 */
	@Override
	public ClickState clickPlus(CalculatorFace face) {
		organizer.left = getVal(onScreen);
		operator = OpState.PlusState.getInstance();
		onScreen = "";
		return organizer.op;
	}

	/**
	 * Perform the clicking of the minus operator.
	 * Put what is on the screen into the left variable,
	 * and make the operator a MinusState.
	 * @param face The CalculatorFace of this calculator.
	 * @return The operator state.
	 */
	@Override
	public ClickState clickMinus(CalculatorFace face) {
		organizer.left = getVal(onScreen);
		operator = OpState.MinusState.getInstance();
		onScreen = "";
		return organizer.op;
	}

	/**
	 * Perform the clicking of the times operator.
	 * Put what is on the screen into the left variable,
	 * and make the operator a TimesState.
	 * @param face The CalculatorFace of this calculator.
	 * @return The operator state.
	 */
	@Override
	public ClickState clickTimes(CalculatorFace face) {
		organizer.left = getVal(onScreen);
		operator = OpState.TimesState.getInstance();
		onScreen = "";
		return organizer.op;
	}

	/**
	 * Perform the clicking of the divide operator.
	 * Put what is on the screen into the left variable,
	 * and make the operator a DivState.
	 * @param face The CalculatorFace of this calculator.
	 * @return The operator state.
	 */
	@Override
	public ClickState clickDiv(CalculatorFace face) {
		organizer.left = getVal(onScreen);
		operator = OpState.DivState.getInstance();
		onScreen = "";
		return organizer.op;
	}

	/**
	 * Perform the clicking of the equals button.
	 * Do nothing, since no operation has taken place.
	 * @param face The CalculatorFace of this calculator.
	 * @return The EqualsState.
	 */
	@Override
	public ClickState clickEquals(CalculatorFace face) {
		return organizer.equals;
	}

	/**
	 * Perform the clicking of the PLUS/MINUS button.
	 * In this state, what is on screen is positive, so this button makes it negative.
	 * @param face The CalculatorFace of this calculator.
	 * @return A new Negative.
	 */
	@Override
	public ClickState clickPE(CalculatorFace face) {
		makeNegative(face);
		return this.new Negative(this, onScreen, organizer);
	}
	
	/**
	 * FirstClicked.DotClicked
	 * 
	 * A state for when you entering data after a decimal point.
	 * It overrides clickDot and clickPE.
	 * 
	 * @author ASher Bernardi
	 * CS 245, Wheaton College
	 * April 26, 2017
	 */
	public class DotClicked extends FirstClicked {
		
		/**
		 * Constructor.
		 * @param s What is on the screen.
		 * @param organizer The Organizer that is keeping track of the data for this calculator.
		 */
		public DotClicked(String s, Organizer organizer) {
			super(organizer);
			onScreen = s;
		}
		
		/**
		 * Perform the clicking of the decimal.
		 * Now that a decimal has already been clicked, it does nothing.
		 * @param face The CalculatorFace of this calculator.
		 * @return This: nothing changed.
		 */
		@Override
		public ClickState clickDot(CalculatorFace face) {
			return this;
		}
		
		/**
		 * Perform the clicking of the PLUS/MINUS button.
		 * In this state, what is on screen is positive, so this button makes it negative.
		 * It returns a NegDot, because input is both negative and after a decimal.
		 * @param face The CalculatorFace of this calculator.
		 * @return A new NegDot.
		 */
		@Override
		public ClickState clickPE(CalculatorFace face) {
			makeNegative(face);
			return this.new NegDot(this, onScreen, organizer);
		}
	}
	
	/**
	 * FirstClicked.Negative
	 * 
	 * A state for when you've pressed PLUS/MINUS and are now entering
	 * negative values. It overrides some methods.
	 * 
	 * @author ASher Bernardi
	 * CS 245, Wheaton College
	 * April 26, 2017
	 */
	public class Negative extends FirstClicked {
		
		/**
		 * The instance of SecondClicked that created this instance.
		 */
		private FirstClicked above;
		
		/**
		 * Constructor.
		 * @param above The FirstClicked class that existed before this one.
		 * @param s What is on the screen.
		 * @param organizer The Organizer that is keeping track of the data for this calculator.
		 */
		public Negative(FirstClicked above, String s, Organizer organizer) {
			super(organizer);
			this.above = above;
			onScreen = s;
		}
		
		/**
		 * Add a string to what is already on the onScreen.
		 * It keeps everything well formatted, it makes the cutoff at 15 digits and
		 * adds the '-' symbol to the beginning.
		 * @param face The CalculatorFace of this calculator.
		 * @param s The string to be added to the screen.
		 */
		@Override
		public void write(CalculatorFace face, String s) {
			onScreen = String.format("%15s", "-" + onScreen.trim().replaceAll("-", "") + s).substring(0, 15).trim();
			face.writeToScreen(onScreen);
		}
		
		/**
		 * Perform the clicking of the decimal.
		 * It return a NegDot, because input will be both negative and after a decimal.
		 * @param face The CalculatorFace of this calculator.
		 * @return A new NegDot.
		 */
		@Override
		public ClickState clickDot(CalculatorFace face) {
			write(face, ".");
			return this.new NegDot(this, onScreen, organizer);
		}
		
		/**
		 * Perform the clicking of the PLUS/MINUS button.
		 * In this state, what is on screen is negative, so the button
		 * will return a positive. It will return the super class that it came from
		 * before it was made negative.
		 * @param face The CalculatorFace of this calculator.
		 * @return the super class.
		 */
		@Override
		public ClickState clickPE(CalculatorFace face) {
			makePositive(face);
			return above;
		}
	}
	
	/**
	 * FirstClicked.NegDot
	 * 
	 * A state for when you've pressed PLUS/MINUS and are now entering
	 * negative values and have already pressed the decimal button.
	 * It overrides some methods.
	 * 
	 * @author ASher Bernardi
	 * CS 245, Wheaton College
	 * April 26, 2017
	 */
	public class NegDot extends FirstClicked {
		
		/**
		 * Constructor.
		 * @param above The FirstClicked class that existed before this one.
		 * @param s What is on the screen.
		 * @param organizer The Organizer that is keeping track of the data for this calculator.
		 */
		public NegDot(FirstClicked above, String s, Organizer organizer) {
			super(organizer);
			onScreen = s;
		}
		
		/**
		 * Add a string to what is already on the onScreen.
		 * It keeps everything well formatted, it makes the cutoff at 15 digits and
		 * adds the '-' symbol to the beginning.
		 * @param face The CalculatorFace of this calculator.
		 * @param s The string to be added to the screen.
		 */
		@Override
		public void write(CalculatorFace face, String s) {
			onScreen = String.format("%15s", "-" + onScreen.trim().replaceAll("-", "") + s).substring(0, 15).trim();
			face.writeToScreen(onScreen);
		}
		
		/**
		 * Perform the clicking of the decimal.
		 * Now that a decimal has already been clicked, it does nothing.
		 * @param face The CalculatorFace of this calculator.
		 * @return This: nothing changed.
		 */
		@Override
		public ClickState clickDot(CalculatorFace face) {
			return this;
		}
		
		/**
		 * Perform the clicking of the PLUS/MINUS button.
		 * In this state, what is on screen is negative, so the button
		 * will return a positive. It will return a simple DotClicked,
		 * because we know that input is after a decimal.
		 * @param face The CalculatorFace of this calculator.
		 * @return A DotClicked.
		 */
		@Override
		public ClickState clickPE(CalculatorFace face) {
			makePositive(face);
			return this.new DotClicked(onScreen, organizer);
		}
	}

}
