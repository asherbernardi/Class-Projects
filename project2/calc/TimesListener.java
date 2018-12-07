package calc;

import java.awt.event.*;

public class TimesListener implements ActionListener {
	/**
	 * The Calculator that will perform the operation.
	 */
	private Calculator calc;
	
	/**
	 * Constructor
	 * @param c The Calculator that will perform the operation.
	 */
	public TimesListener(Calculator c) {
		calc = c;
	}
	
	/**
	 * Press the times button.
	 */
	public void actionPerformed(ActionEvent ae) {
		calc.times();
	}
}
