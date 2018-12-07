package calc;

import java.awt.event.*;

public class DivideListener implements ActionListener {
	/**
	 * The Calculator that will perform the operation.
	 */
	private Calculator calc;
	
	/**
	 * Constructor
	 * @param c The Calculator that will perform the operation.
	 */
	public DivideListener(Calculator c) {
		calc = c;
	}
	
	/**
	 * Press the divide button.
	 */
	public void actionPerformed(ActionEvent ae) {
		calc.divide();
	}
}
