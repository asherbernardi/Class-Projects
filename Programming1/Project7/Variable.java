/**
 * Variable.java
 *
 * An ExprNode that holds a variable x.
 *
 * @author ASher Bernardi
 *
 * CSCI 235, Wheaton College, Fall 2016
 * Project 7
 * Dec 3, 2016
 */

public class Variable implements ExprNode {

	/**
	 * A string to represent the variable (It will be "x" or "y").
	 */
	private String var;

	/**
	 * Constructor
	 * @param var   The variable.
	 */
	public Variable(String var) {
		this.var = var;
	}

	/**
	 * Evaluate the expression. Since there's no operator, just return the variable
	 * value according to whether it is "x" or "y".
	 * @param x   The value of the variable x.
	 * @param y   The value of the variable y.
	 */
	public double evaluate(double x, double y) {
		if (var.equals("x")) return x;
		else return y;
	}
}