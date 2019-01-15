/**
 * Exp.java
 *
 * An ExprNode for the number e.
 *
 * @author ASher Bernardi
 *
 * CSCI 235, Wheaton College, Fall 2016
 * Project 7
 * Dec 3, 2016
 */

public class Exp implements ExprNode {

	/**
	 * Constructor
	 */
	public Exp() {

	}

	/**
	 * Evaluate the expression. Cimply return e.
	 * @param x   The variable x - Not used.
	 * @param y   The variable y - Not used.
	 */
	public double evaluate(double x, double y) {
		return Math.E;
	}
}