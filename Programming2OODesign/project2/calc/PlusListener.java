package calc;

import java.awt.event.*;

public class PlusListener implements ActionListener {
	/**
	 * The Calculator that will perform the operation.
	 */
	private Calculator calc;
	
	/**
	 * Constructor
	 * @param c The Calculator that will perform the operation.
	 */
	public PlusListener(Calculator c) {
		calc = c;
	}
	
	/**
	 * Press the plus button.
	 */
	public void actionPerformed(ActionEvent ae) {
		calc.plus();
	}
}
