package calc;

/**
 * OpState.java
 * 
 * The state mechanism to keep track of which operation to perform.
 * 
 * @author ASher Bernardi
 * CS 245, Wheaton College
 * April 26, 2017
 */
public abstract class OpState {
	
	/**
	 * Perform a certain operation on two doubles.
	 * @param left The first operand.
	 * @param right The second operand.
	 * @return The result of the operation.
	 */
	public abstract double performOperation(double left, double right);
	
	/**
	 * OpState.PlusState
	 * 
	 * The state during which you would like to perform an addition.
	 * 
	 * @author ASher Bernardi
	 * CS 245, Wheaton College
	 * April 26, 2017
	 */
	public static class PlusState extends OpState {
		//Singleton
		private static OpState.PlusState instance = new PlusState();
		private PlusState() {}
		public static OpState.PlusState getInstance() {
			return instance;
		}
		
		/**
		 * Add left and right.
		 */
		public double performOperation(double left, double right) {
			return left + right;
		}
	}
	
	/**
	 * OpState.PlusState
	 * 
	 * The state during which you would like to perform a subtraction.
	 * 
	 * @author ASher Bernardi
	 * CS 245, Wheaton College
	 * April 26, 2017
	 */
	public static class MinusState extends OpState {
		//Singleton
		private static OpState.MinusState instance = new MinusState();
		private MinusState() {}
		public static OpState.MinusState getInstance() {
			return instance;
		}
		
		/**
		 * Subtract right from left.
		 */
		public double performOperation(double left, double right) {
			return left - right;
		}
	}
	
	/**
	 * OpState.PlusState
	 * 
	 * The state during which you would like to perform a multiplication.
	 * 
	 * @author ASher Bernardi
	 * CS 245, Wheaton College
	 * April 26, 2017
	 */
	public static class TimesState extends OpState {
		//Singleton
		private static OpState.TimesState instance = new TimesState();
		private TimesState() {}
		public static OpState.TimesState getInstance() {
			return instance;
		}
		
		/**
		 * Multiply left and right.
		 */
		public double performOperation(double left, double right) {
			return left * right;
		}
	}
	
	/**
	 * OpState.PlusState
	 * 
	 * The state during which you would like to perform a division.
	 * 
	 * @author ASher Bernardi
	 * CS 245, Wheaton College
	 * April 26, 2017
	 */
	public static class DivState extends OpState {
		//Singleton
		private static OpState.DivState instance = new DivState();
		private DivState() {}
		public static OpState.DivState getInstance() {
			return instance;
		}
		
		/**
		 * Divide left into right.
		 */
		public double performOperation(double left, double right) {
			return left / right;
		}
	}
}
