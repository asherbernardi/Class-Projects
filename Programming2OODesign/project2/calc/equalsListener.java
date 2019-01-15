package calc;

import java.awt.event.*;

public class equalsListener implements ActionListener {
	/**
	 * The Calculator that will perform the operation.
	 */
	private Calculator calc;
	
	/**
	 * Constructor
	 * @param c The Calculator that will perform the operation.
	 */
	public equalsListener(Calculator c) {
		calc = c;
	}
	
	/**
	 * Press the equals button.
	 */
	public void actionPerformed(ActionEvent ae) {
		calc.equals();
	}
}
