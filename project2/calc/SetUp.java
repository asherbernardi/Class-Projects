package calc;

/**
 * SetUp
 * 
 * Class to set up and start the calculator, plus
 * facilities for test-driving the calculator.
 *
 * @author Thomas VanDrunen
 * CS 245, Wheaton College
 * June 27, 2014
*/
public class SetUp {

	/**
	 * Method for initializing the calculator internals and
	 * connecting them to the calculator face.
	 * @param face The component representing the user interface of 
	 * the calculator. 
	 */
	public static void setUpCalculator(CalculatorFace face) {
		
		// The internal calculator that will do the processes.
		Calculator calc = new Calculator(face);
		
		for (int i = 0; i < 10; i++) {
			face.addNumberActionListener(i, new NumListener(calc, i));
		}
		
		face.addActionListener('.', new DotListener(calc));
		
		face.addActionListener('+', new PlusListener(calc));
		face.addActionListener('-', new MinusListener(calc));
		face.addActionListener('*', new TimesListener(calc));
		face.addActionListener('/', new DivideListener(calc));
		face.addActionListener(CalculatorFace.PLUS_MINUS, new signListener(calc));
		
		face.addActionListener('=', new equalsListener(calc));
		
		face.addActionListener('C', new CListener(calc));
		
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
