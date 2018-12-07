package calc;

import java.awt.event.*;

public class NumListener implements ActionListener {
	private int digit;
	
	/**
	 * The Calculator on which to print the numbers.
	 */
	private Calculator calc;
	
	/**
	 * Constructor
	 * @param c The Calculator on which to print the numbers.
	 * @param dig The number to print.
	 */
	public NumListener(Calculator c, int dig) {
		digit = dig;
		calc = c;
	}
	
	/**
	 * Press a number button.
	 */
	public void actionPerformed(ActionEvent ae) {
		calc.printNum(digit);
	}
}
