/**
 * Trig.java
 *
 * An ExprNode which evaluates a trig function.
 *
 * @author ASher Bernardi
 *
 * CSCI 235, Wheaton College, Fall 2016
 * Project 7
 * March 6, 2017
 */

public class Trig implements ExprNode {

	/**
	 * The trig function (sin|cos|tan).
	 */
	private String trig;

	/**
	 * The expression inside the trig function.
	 */
	private ExprNode expr;

	/**
	 * Constructor
	 * @param op   the trig function.
	 * @param l    the expression.
	 */
	public Trig(String t, ExprNode e) {
		trig = t;
		expr = e;
	}

	/**
	 * Evaluate the expression.
	 * Evaluate the correct trig expression.
	 * @param x   The variable x.
	 * @param x   The variable y.
	 */
	public double evaluate(double x, double y) {
		switch (trig) {
			case "sin":
				return Math.sin(expr.evaluate(x,y));
			case "cos":
				return Math.cos(expr.evaluate(x,y));
			case "tan":
				return Math.tan(expr.evaluate(x,y));
			default:
				throw new RuntimeException("trig function unsupported");
		}
	}
}