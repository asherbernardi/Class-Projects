/**
 * Operation.java
 *
 * An ExprNode which takes two values and evaluates them with an operator.
 *
 * @author ASher Bernardi
 *
 * CSCI 235, Wheaton College, Fall 2016
 * Project 7
 * Dec 3, 2016
 */

public class Operation implements ExprNode {

	/**
	 * The operator (+|-|*|/|^).
	 */
	private String operator;

	/**
	 * The value on the left of the operation.
	 */
	private ExprNode left;

	/**
	 * The value on the right of the operation.
	 */
	private ExprNode right;

	/**
	 * Constructor
	 * @param op   the operator.
	 * @param l    the left value.
	 * @param r    the right value.
	 */
	public Operation(String op, ExprNode l, ExprNode r) {
		operator = op;
		left = l;
		right = r;
	}

	/**
	 * Evaluate the expression.
	 * For each operator, recursively evaluate each side of the operation, then
	 * return the evaluated values operated by the operator.
	 * @param x   The variable.
	 */
	public double evaluate(double x) {
		switch (operator) {
			case "+":
				return left.evaluate(x) + right.evaluate(x);
			case "-":
				return left.evaluate(x) - right.evaluate(x);
			case "*":
				return left.evaluate(x) * right.evaluate(x);
			case "/":
				return left.evaluate(x) / right.evaluate(x);
			case "^":
				return Math.pow(left.evaluate(x), right.evaluate(x));
			default:
				throw new RuntimeException("operator unsupported");
		}
	}
}