/**
 * Number.java
 *
 * An ExprNode that is just a number.
 *
 * @author ASher Bernardi
 *
 * CSCI 235, Wheaton College, Fall 2016
 * Project 7
 * Dec 3, 2016
 */

public class Number implements ExprNode {

	/**
	 * The value of the number.
	 */
	private double value;

	/**
	 * Constructor
	 * @param value   The value of the Number.
	 */
	public Number(double value) {
		this.value = value;
	}

	/**
	 * Evaluate the expression. Since there's no operator, just return the value.
	 * @param x   The variable x - Not used.
	 * @param y   The variable y - Not used.
	 */
	public double evaluate(double x, double y) {
		return value;
	}
}