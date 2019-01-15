package calc;

import java.awt.event.*;

public class CListener implements ActionListener {
	/**
	 * The Calculator that will perform the operation.
	 */
	private Calculator calc;
	
	/**
	 * Constructor
	 * @param c The Calculator that will perform the operation.
	 */
	public CListener(Calculator c) {
		calc = c;
	}
	
	/**
	 * Press the C button.
	 */
	public void actionPerformed(ActionEvent ae) {
		calc.clear();
	}
}
