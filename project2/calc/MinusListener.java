package calc;

import java.awt.event.*;

public class MinusListener implements ActionListener {
	/**
	 * The Calculator that will perform the operation.
	 */
	private Calculator calc;
	
	/**
	 * Constructor
	 * @param c The Calculator that will perform the operation.
	 */
	public MinusListener(Calculator c) {
		calc = c;
	}
	
	/**
	 * Press the minus button.
	 */
	public void actionPerformed(ActionEvent ae) {
		calc.minus();
	}
}
