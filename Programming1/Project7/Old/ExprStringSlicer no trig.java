/**
 * ExprStringSlicer.java
 *
 * A program to slice a string into a string array, with the middle string being a
 * mathematical operator and the two other strings being substrings of the original.
 * The program should follow orders of operation and recognize errors in user input.
 * 
 * @author Thomas VanDrunen
 * CSCI 235, Wheaton College, Fall 2016
 * Project 7
 * Nov. 30, 2016
 */

import java.util.*;

public class ExprStringSlicer {

	/**
	 * Slice the string.
	 * @param s   The string to slice.
	 */
	public static String[] slice(String s) {
		// To begin, take out all the spaces.
		StringTokenizer tok = new StringTokenizer(s);
		s = "";
		while (tok.hasMoreTokens()) {
			s = s + tok.nextToken();
		}

		// Then we check to see the unput is valid
		if (!isValidInput(s)) {
			throw new RuntimeException("Input invalid");
		}

		// separate the parenthetical expressions.
		ArrayList<int[]> parenIndexes = getParen(s);

		// then remove parentheses if they bound the whole expression and get the new
		// parentheses.
		if (parenIndexes != null) {
			if (parenIndexes.get(0)[0] == 0 && parenIndexes.get(0)[1] == s.length() - 1) {
				s = s.substring(1, s.length() - 1);
				parenIndexes = getParen(s);
			}
		}

		// We now know where all the parentheses are.

		// The index of the operator chosen for the slice.
		int chosenOp = -1;

		// To maintain orders of operation, we start by finding + or -
		// If the operation (+|-) is inside a parenthetical expression, ignore it. 
		// the index of the first + or - operator.
		int firstPlus = -1;
		firstPlus = getFirstOp(s, "+", "-", parenIndexes);
		if (firstPlus == 0) {
			String[] ret = {"0",
							s.substring(0,1),
							s.substring(1,s.length())};
			return ret;
		}
		if (firstPlus != -1) {
			String[] ret = {s.substring(0,firstPlus),
							s.substring(firstPlus,firstPlus+1),
							s.substring(firstPlus+1,s.length())};
			return ret;
		}

		// Next we check for * or /, if there were no + or -
		int firstTimes = -1;
		firstTimes = getFirstOp(s, "*", "/", parenIndexes);
		int noOpTimes = noOpTimes(s, parenIndexes);
		if (firstTimes == -1) {
			if (noOpTimes != -1) {
				String[] ret = {s.substring(0,noOpTimes),
								"*",
								s.substring(noOpTimes,s.length())};
				return ret;
			}
		}
		else if (noOpTimes == -1) {
			if (firstTimes != -1) {
				String[] ret = {s.substring(0,firstTimes),
								s.substring(firstTimes,firstTimes+1),
								s.substring(firstTimes+1,s.length())};
				return ret;
			}
		}
		else {
			if (firstTimes < noOpTimes) {
				String[] ret = {s.substring(0,firstTimes),
								s.substring(firstTimes,firstTimes+1),
								s.substring(firstTimes+1,s.length())};
				return ret;
			}
			else {
				String[] ret = {s.substring(0,noOpTimes),
								"*",
								s.substring(noOpTimes,s.length())};
				return ret;
			}
		}

		// Next, we check for ^.
		int firstExp = getFirstOp(s, "^", "^", parenIndexes);
		if (firstExp != -1) {
			String[] ret = {s.substring(0,firstExp),
							s.substring(firstExp,firstExp+1),
							s.substring(firstExp+1,s.length())};
			return ret;
		}

		// If there are no operators in the expression, simply return the number.
		if (chosenOp == -1) {
			String[] ret = {s};
			return ret;
		}

		return null;
	}

	/**
	 * Find where the outer-most parentheses in a String expression are.
	 * place1 and 2 are the indexes where the parenthetical expression starts
	 * and ends. First look for the first "(", if there is a "(", find the next ")",
	 * However, if you encounter another "(" before reaching the first ")", skip the
	 * next ")" and find the one after that. This ignores parenthetical expressions
	 * within parenthetical expressions, because we do not need to care about those.
	 * @param s   The string the use.
	 * @return An array list with values for the first and last index of each outer
	 * parenthetical pair.
	 */
	private static ArrayList<int[]> getParen(String s) {
		ArrayList<int[]> parenIndexes = new ArrayList<int[]>(0);
		// Two placeholders for the indexes of the "(" and the ")"
		int place1 = 0;
		int place2 = 0;
		for (int i = 0; i < s.length(); ) {
			place1 = s.indexOf("(", place1);
			if (place1 != -1) {
				for (int j = place1 + 1; j < s.length(); ) {
					if (s.charAt(j) == ')') {
						place2 = j;
						parenIndexes.add(new int[2]);
						parenIndexes.get(parenIndexes.size() - 1)[0] = place1;
						parenIndexes.get(parenIndexes.size() - 1)[1] = place2;
						break;
					}
					if (s.charAt(j) == '(') {
						j = s.indexOf(")", j);
					}
					j++;
				}
				place1 = place2;
			}
			else
				break;
		}

		if (parenIndexes.size() == 0) return null;
		return parenIndexes;
	}

	/**
	 * Find the first instance of two operators with the same order of operation.
	 * Do not count operators inside of parentheses.
	 * @param s   The string.
	 * @param op1   The first operator, it has the same order of operation as op2.
	 * @param op2   The second operator, it has the same order of operation as op1.
	 * @param parenIndexes   an array list that has the indexes of all parentheses.
	 * @return the index of the first instance of the operator, or -1.
	 */
	private static int getFirstOp(String s, String op1, String op2,
										   ArrayList<int[]> parenIndexes) {
		int nextOp = -1;
		int r = -1;
		while (nextOp < s.lastIndexOf(op1) || nextOp < s.lastIndexOf(op2)) {
			// Every case of whether or not op1 or op2 exist is taken into account.
			if (s.indexOf(op1, nextOp+1) == -1) {
				if (s.indexOf(op2, nextOp+1) != -1)
					nextOp = s.indexOf(op2, nextOp+1);
				else
					break;
			}
			else if (s.indexOf(op2, nextOp+1) == -1) {
				if (s.indexOf(op1, nextOp+1) != -1)
					nextOp = s.indexOf(op1, nextOp+1);
				else
					break;
			}
			else {
				if (s.indexOf(op1, nextOp+1) < s.indexOf(op2, nextOp+1))
					nextOp = s.indexOf(op1, nextOp+1);
				else
					nextOp = s.indexOf(op2, nextOp+1);
			}

			Boolean inParen = false;
			if (parenIndexes != null) {
				for (Iterator<int[]> it = parenIndexes.iterator(); it.hasNext(); ) {
					int[] place = it.next();
					if (nextOp > place[0] && nextOp < place[1]) {
						inParen = true;
					}
				}
			}

			// If the first operator found is inside of parentheses, ignore it
			// and move on the next operator.
			if (!inParen) {
				r = nextOp;
				break;
			}
		}

		return r;
	}

	/**
	 * Find the first instance of a multiplication without the * operator.
	 * @param s   the string.
	 * @param parenIndexes   an array list that has the indexes of all parentheses.
	 * @return the index of the first no-operator multiplication, or -1.
	 */
	private static int noOpTimes(String s, ArrayList<int[]> parenIndexes) {
		int r = -1;

		if (parenIndexes != null) {
			Iterator<int[]> it = parenIndexes.iterator();
			int[] place = it.next();
			// Any multiplication with parentheses, such as (4)(5).
			for ( ; it.hasNext(); ) {
				int[] before = place;
				place = it.next();
				if (before[1] == place[0]-1) {
					r = place[0];
				}
			}

			// Any multiplication with a number or variable and parentheses, such as
			// 4(5) or x(5).
			for (it = parenIndexes.iterator(); it.hasNext(); ) {
				place = it.next();
				if (place[0] != 0) {
					if (Character.isDigit(s.charAt(place[0]-1)) ||
						s.charAt(place[0]-1) == 'x' ||
						s.charAt(place[0]-1) == 'y') {
						if (place[0] < r || r == -1) {
							r = place[0];
						}
					}
				}
			}
			// Any multiplication with a parenthesis and a variable, such as (4)x
			for (it = parenIndexes.iterator(); it.hasNext(); ) {
				place = it.next();
				if (place[1] != s.length()-1) {
					if (s.charAt(place[1]+1) == 'x' ||
						s.charAt(place[1]+1) == 'y') {
						if (place[1]+1 < r || r == -1) {
							r = place[1]+1;
						}
					}
				}
			}
		}

		// Any multiplication from two variables, such as xy.
		int[] vars = findVars(s);
		for (int i = 1; i < vars.length; i++) {
			if (vars[i] == 1 + vars[i-1])
				if (vars[i] < r || r == -1) r = vars[i];
		}
		// Any multiplication from a variable and a number, such as 4x.
		for (int i = 0; i < vars.length; i++) {
			if (vars[i] != 0) {
				if (Character.isDigit(s.charAt(vars[i] - 1))) {
					if (vars[i] < r || r == -1) {
						r = vars[i];
					}
				}
			}
		}

		return r;
	}

	/**
	 * Test if the string is a valid input.
	 * @param s   The string.
	 * @return whether or not the string is valid.
	 */
	private static Boolean isValidInput(String s) {
		// Initialize the string as a valid input.
		boolean isValid = true;

		// First test for any unsupported characters (Only opperators, parentheses,
		// points, spaces, "x", "y", and digits are allowed).
		// Test for other things as well:
		for (int i = 0; i < s.length(); i++) {
			// unsupported characters:
			if (!(s.charAt(i) == '+' ||
				  s.charAt(i) == '-' ||
				  s.charAt(i) == '*' ||
				  s.charAt(i) == '/' ||
				  s.charAt(i) == '^' ||
				  s.charAt(i) == '(' ||
				  s.charAt(i) == ')' ||
				  s.charAt(i) == '.' ||
				  s.charAt(i) == ' ' ||
				  s.charAt(i) == 'x' ||
				  s.charAt(i) == 'y' ||
				  Character.isDigit(s.charAt(i)))) {
				isValid = false;
			}
			// Expression cannot start with these operators.
			if (i == 0) {
				if (s.charAt(i) == '+' ||
				    s.charAt(i) == '*' ||
				    s.charAt(i) == '/' ||
				    s.charAt(i) == '^' ||
				    s.charAt(i) == '.' ||
				    s.charAt(i) == ')') {
					isValid = false;
				}
			}
			// Expression cannot end with these operators.
			else if (i == s.length() - 1) {
				if (s.charAt(i) == '+' ||
					s.charAt(i) == '-' ||
				    s.charAt(i) == '*' ||
				    s.charAt(i) == '/' ||
				    s.charAt(i) == '^' ||
				    s.charAt(i) == '.' ||
				    s.charAt(i) == '(') {
					isValid = false;
				}
			}
			else {
				// These Operators must have a digit or parenthesis
				// or variable on eiter side of them
				if (s.charAt(i) == '+' ||
				    s.charAt(i) == '*' ||
				    s.charAt(i) == '/' ||
				    s.charAt(i) == '^' ||
				    s.charAt(i) == '.' ) {
					if (!((Character.isDigit(s.charAt(i-1)) ||
							s.charAt(i-1) == ')'            ||
							s.charAt(i-1) == 'x'            ||
							s.charAt(i-1) == 'y')
								&&
						  (Character.isDigit(s.charAt(i+1)) ||
						  	s.charAt(i+1) == '('            ||
							s.charAt(i+1) == 'x'            ||
							s.charAt(i+1) == 'y'))) {
						isValid = false;
					}
				}
			}
			// numbers cannot have variables or closed parentheses before them
			if (i != 0) {
				if (Character.isDigit(s.charAt(i))) {
					if (s.charAt(i-1) == ')' ||
						s.charAt(i-1) == 'x' ||
						s.charAt(i-1) == 'y') {
						isValid = false;
					}
				}
			}

		}

		// Next check that the number of "(" is equal to the number of ")".
		int nopen = 0, nclosed = 0;
		for (int i = s.indexOf("("); i != -1; i = s.indexOf("(", i+1)) {
			nopen++;
		}
		for (int i = s.indexOf(")"); i != -1; i = s.indexOf(")", i+1)) {
			nclosed++;
		}
		if (nopen != nclosed) {
			isValid = false;
		}

		return isValid;
	}

	/**
	 * Find where all the variables are in the string.
	 * @param s   The string the test.
	 * @return an int[] with the locations of all the variables.
	 */
	private static int[] findVars(String s) {
		int nVars = 0;
		// First go through to count the vars.
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'x' || s.charAt(i) == 'y') {
				nVars++;
			}
		}
		// Then go through to find the vars.
		int[] vars = new int[nVars];
		for (int i = 0, j = 0; i < s.length(); i++) {
			if (s.charAt(i) == 'x' || s.charAt(i) == 'y') {
				vars[j] = i;
				j++;
			}
		}		
		return vars;
	}

}