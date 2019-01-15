package calc;

import java.awt.event.*;

public class DotListener implements ActionListener {
	/**
	 * The Calculator that will perform the operation.
	 */
	private Calculator calc;
	
	/**
	 * Constructor
	 * @param c The Calculator that will perform the operation.
	 */
	public DotListener(Calculator c) {
		calc = c;
	}

	/**
	 * Press the decimal point button.
	 */
	public void actionPerformed(ActionEvent e) {
		calc.printDot();
	}

}
