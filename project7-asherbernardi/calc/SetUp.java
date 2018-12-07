package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * SetUp
 * 
 * Class to set up and start the calculator, plus
 * facilities for test-driving the calculator.
 *
 * @author Thomas VanDrunen & ASher Bernardi
 * CS 245, Wheaton College
 * April 11, 2017
*/
public class SetUp {

	/**
	 * Method for initializing the calculator internals and
	 * connecting them to the calculator face.
	 * @param face The component representing the user interface of 
	 * the calculator. 
	 */
	public static void setUpCalculator(CalculatorFace face) {

		Organizer organizer = new Organizer(face);
		
		// To set up all the number buttons.
		for (int i = 0; i <= 9; i++) {
			// A value must be final to be used in an anonymous class.
			final int num = i;
			face.addNumberActionListener(i, new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					organizer.clickNumber(num);
				}
			});
		}
		
		face.addActionListener('.', new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				organizer.clickDot();
			}
		});
		
		face.addActionListener('+', new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				organizer.clickPlus();
			}
		});
		
		face.addActionListener('-', new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				organizer.clickMinus();
			}
		});
		
		face.addActionListener('*', new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				organizer.clickTimes();
			}
		});
		
		face.addActionListener('/', new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				organizer.clickDiv();
			}
		});
		
		face.addActionListener('C', new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				organizer.clickC();
			}
		});
		
		face.addActionListener('=', new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				organizer.clickEquals();
			}
		});
		
		face.addActionListener(CalculatorFace.PLUS_MINUS, new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				organizer.clickPE();
			}
		});
		
	}
	
	
	/**
	 * This main method is for your testing of your calculator.
	 * It will *not* be used during grading. Any changes you make
	 * to this method will be ignored at grading.
	 */
	public static void main(String[] args) {
		setUpCalculator(new PlainCalculatorFace());
	}

}
