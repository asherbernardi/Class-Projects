package calc;

import java.awt.event.*;

public class signListener implements ActionListener {
	/**
	 * The Calculator that will perform the operation.
	 */
	private Calculator calc;
	
	/**
	 * Constructor
	 * @param c The Calculator that will perform the operation.
	 */
	public signListener(Calculator c) {
		calc = c;
	}	
	
	/**
	 * Press the +/- button.
	 */
	public void actionPerformed(ActionEvent e) {
		calc.signChange();
	}

}
