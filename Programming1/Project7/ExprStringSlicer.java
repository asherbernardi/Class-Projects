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
		checkValidInput(s);

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

		// Next, we check for trig.
		/*int firstTrig = getTrig(s, parenIndexes);
		if (firstTrig != -1) {
			String[] ret = {s.substring(firstTrig, firstTrig+3),
							s.substring(firstTrig+4, s.)};
			return ret;
		}*/
		if (isTrig(s, parenIndexes)) {
			String[] ret = {s.substring(0, 3),
							s.substring(4, s.length() - 1)};
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
	private static int getFirstOp(String s, String op1, String op2, ArrayList<int[]> parenIndexes) {
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
	 * //Return the earliest index of the first letter of a trig operator or -1.
	 * Returns whether or not the expression is trigonomic. We know that due to
	 * orders of operations, we will only evaluate trig function if it is the
	 * outer-most function in the expression (i.e. s = "sin(3+4)").
	 * 
	 */
	private static Boolean isTrig(String s, ArrayList<int[]> parenIndexes) {
		// To keep track of which outer parenthesis pair were in.
		int parenPair = 0;
		/*for(int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				i = parenIndexes.get(parenPair)[1];
				parenPair++;
				continue;
			}
			if (s.charAt(i) == 's' ||
				s.charAt(i) == 'c' ||
				s.charAt(i) == 't') {
				return i;
			}
		}*/
		if (s.charAt(0) == 's' ||
			s.charAt(0) == 'c' ||
			s.charAt(0) == 't') {
			return true;
		}

		return false;
	}
 
	/**
	 * Find the first instance of a multiplication without the * operator.
	 * MIGHT BE A PROBLEM!
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
						s.charAt(place[0]-1) == 'y' ||
						s.charAt(place[0]-1) == 'e') {
						if (place[0] < r || r == -1) {
							r = place[0];
						}
					}
				}
			}
			// Any multiplication with a parenthesis and a variable, such as (4)x
			// or any multiplication such as (5)e or (2)cos(7).
			for (it = parenIndexes.iterator(); it.hasNext(); ) {
				place = it.next();
				if (place[1] != s.length()-1) {
					if (s.charAt(place[1]+1) == 'x' ||
						s.charAt(place[1]+1) == 'y' ||
						s.charAt(place[1]+1) == 'e' ||
						s.charAt(place[1]+1) == 's' || 
						s.charAt(place[1]+1) == 'c' ||
						s.charAt(place[1]+1) == 't') {
						if (place[1]+1 < r || r == -1) {
							r = place[1]+1;
						}
					}
				}
			}
		}

		// Any multiplication from two variables, such as xy.
		int[] vars = findVars(s, parenIndexes);
		for (int i = 1; i < vars.length; i++) {
			if (vars[i] == 1 + vars[i-1])
				if (vars[i] < r || r == -1)
					r = vars[i];
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
		// Any multiplication from a variable or number and a trig function, such
		// as 2sin(5) or xcos(x).
		int[] trigs = findTrig(s, parenIndexes);
		for (int i = 0; i < trigs.length; i++) {
			if (trigs[i] != 0) {
				if (Character.isDigit(s.charAt(trigs[i] - 1)) ||
					s.charAt(trigs[i] - 1) == 'x'                      ||
					s.charAt(trigs[i] - 1) == 'y'                      ||
					s.charAt(trigs[i] - 1) == 'e') {
					if (trigs[i] < r || r == -1) {
						r = trigs[i];
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
	private static void checkValidInput(String s) {

		// First test for any unsupported characters (Only opperators, parentheses,
		// points, spaces, "x", "y", trig operators, e, and digits are allowed).
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
				  // For exp
				  s.charAt(i) == 'e' ||
				  // For trigonometry
				  s.charAt(i) == 's' ||
				  s.charAt(i) == 'i' ||
				  s.charAt(i) == 'n' ||
				  s.charAt(i) == 'c' ||
				  s.charAt(i) == 'o' ||
				  s.charAt(i) == 't' ||
				  s.charAt(i) == 'a' ||
				  Character.isDigit(s.charAt(i)))) {
				throw new RuntimeException("Unsupported Characters");
			}
			// Expression cannot start with these operators.
			if (i == 0) {
				if (s.charAt(i) == '+' ||
				    s.charAt(i) == '*' ||
				    s.charAt(i) == '/' ||
				    s.charAt(i) == '^' ||
				    s.charAt(i) == '.' ||
				    s.charAt(i) == ')' ||
				    // Can start with trig, but not i, n, o, or a
				    s.charAt(i) == 'i' ||
				    s.charAt(i) == 'n' ||
				    s.charAt(i) == 'o' ||
				    s.charAt(i) == 'a') {
					throw new RuntimeException("First Character invalid");
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
				    s.charAt(i) == '(' ||
				    // For Trig
				    s.charAt(i) == 's' ||
				    s.charAt(i) == 'i' ||
				    s.charAt(i) == 'n' ||
				    s.charAt(i) == 'c' ||
				    s.charAt(i) == 'o' ||
				    s.charAt(i) == 't' ||
				    s.charAt(i) == 'a') {
					throw new RuntimeException("Last Character Invalid");
				}
			}
			else {
				// These Operators must have a digit or parenthesis
				// or variable on either side of them
				if (s.charAt(i) == '+' ||
				    s.charAt(i) == '*' ||
				    s.charAt(i) == '/' ||
				    s.charAt(i) == '^' ||
				    s.charAt(i) == '.' ) {
					if (!(Character.isDigit(s.charAt(i-1)) ||
						   s.charAt(i-1) == ')'            ||
						   s.charAt(i-1) == 'x'            ||
						   s.charAt(i-1) == 'y'            ||
						   s.charAt(i-1) == 'e')) {
						throw new RuntimeException("Wrong Character Before an Operator");
					}
					if (!(Character.isDigit(s.charAt(i+1)) ||
						   s.charAt(i+1) == '('            ||
						   s.charAt(i+1) == 'x'            ||
						   s.charAt(i+1) == 'y'            ||
						   s.charAt(i+1) == 's'            ||
						   s.charAt(i+1) == 'c'            ||
						   s.charAt(i+1) == 't'            ||
						   s.charAt(i+1) == 'e')) {
						throw new RuntimeException("Wrong Character After an Operator");
					}
				}
			}
			// numbers cannot have variables or closed parentheses before them
			if (i != 0) {
				if (Character.isDigit(s.charAt(i))) {
					if (s.charAt(i-1) == ')' ||
						s.charAt(i-1) == 'x' ||
						s.charAt(i-1) == 'y' ||
				        // For Trig
				    	s.charAt(i-1) == 's' ||
				    	s.charAt(i-1) == 'i' ||
				    	s.charAt(i-1) == 'n' ||
				    	s.charAt(i-1) == 'c' ||
				    	s.charAt(i-1) == 'o' ||
				    	s.charAt(i-1) == 't' ||
				    	s.charAt(i-1) == 'a')   {
						throw new RuntimeException("Wrong Character Before a Digit");
					}
				}
			}
			// Test trig functions
			if (s.charAt(i) == 's') {
				if (!(s.charAt(i+1) == 'i' &&
					  s.charAt(i+2) == 'n' &&
					  s.charAt(i+3) == '(')) {
					throw new RuntimeException("Sin Operator Invalid");
				}
				i += 2;
				continue;
			}
			if (s.charAt(i) == 'c') {
				if (!(s.charAt(i+1) == 'o' &&
					  s.charAt(i+2) == 's' &&
					  s.charAt(i+3) == '(')) {
					throw new RuntimeException("Cosin Operator Invalid");
				}
				i += 2;
				continue;
			}
			if (s.charAt(i) == 't') {
				if (!(s.charAt(i+1) == 'a' &&
					  s.charAt(i+2) == 'n' &&
					  s.charAt(i+3) == '(')) {
					throw new RuntimeException("Tangent Operator Invalid");
				}
				i += 2;
				continue;
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
			throw new RuntimeException("Incorrect Parentheses");
		}

	}

	/**
	 * Find where all the variables are in the string. Treat e as a variable.
	 * Don't count variables in parentheses.
	 * @param s   The string the test.
	 * @param parenIndexes an array list that has the indexes of all parentheses.
	 * @return an int[] with the locations of all the variables.
	 */
	private static int[] findVars(String s, ArrayList<int[]> parenIndexes) {
		ArrayList<Integer> varsList = new ArrayList<Integer>();
		// First go through to add the vars to the list.
		// Keep track of which pair of parentheses you are in.
		int parenPair = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				i = parenIndexes.get(parenPair)[1];
				parenPair++;
				continue;
			}
			if (s.charAt(i) == 'x' || s.charAt(i) == 'y' || s.charAt(i) == 'e') {
				varsList.add(i);
			}
		}
		// Then convert it into an array.
		int[] vars = new int[varsList.size()];
		for (int i = 0; i < varsList.size(); i++) {
			vars[i] = varsList.get(i);
		}		
		return vars;
	}

	/**
	 * Find all instances of trig functions outside of parentheses.
	 * @param s   The string the test.
	 * @param parenIndexes an array list that has the indexes of all parentheses.
	 * @return an int[] with the locations of all the trig functions.
	 */
	private static int[] findTrig(String s, ArrayList<int[]> parenIndexes) {
		ArrayList<Integer> trigList = new ArrayList<Integer>();
		// First go through to add the vars to the list.
		// Keep track of which pair of parentheses you are in.
		int parenPair = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				i = parenIndexes.get(parenPair)[1];
				parenPair++;
				continue;
			}
			if ((s.charAt(i) == 's' && s.charAt(i+1) == 'i')|| 
				(s.charAt(i) == 'c' && s.charAt(i+1) == 'o')|| 
				(s.charAt(i) == 't' && s.charAt(i+1) == 'a')) {
				trigList.add(i);
			}
		}
		// Then convert it into an array.
		int[] trigs = new int[trigList.size()];
		for (int i = 0; i < trigList.size(); i++) {
			trigs[i] = trigList.get(i);
		}		
		return trigs;
	}

	public static void main(String[] args) {
		String[] a = slice("cos(x)");
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

}