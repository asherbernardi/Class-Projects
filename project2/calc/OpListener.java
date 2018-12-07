package calc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpListener implements ActionListener {
	private char operator;
	
	private CalculatorFace face;
	
	private static double op1, op2;
	
	private static String onScreen = "";
	
	private static boolean plus, minus, times, divide;
	
	public OpListener(CalculatorFace f, char op) {
		operator = op;
		face = f;
	}

	public void actionPerformed(ActionEvent ae) {
		
		switch(operator) {
		case '1':
			if (onScreen.length() != 15) {
				face.writeToScreen(onScreen + "1");
				onScreen = onScreen + "1";
			}
			break;
		case '2':
			if (onScreen.length() != 15) {
				face.writeToScreen(onScreen + "2");
				onScreen = onScreen + "2";
			}
			break;
		case '3':
			if (onScreen.length() != 15) {
				face.writeToScreen(onScreen + "3");
				onScreen = onScreen + "3";
			}
			break;
		case '4':
			if (onScreen.length() != 15) {
				face.writeToScreen(onScreen + "4");
				onScreen = onScreen + "4";
			}
			break;
		case '5':
			if (onScreen.length() != 15) {
				face.writeToScreen(onScreen + "5");
				onScreen = onScreen + "5";
			}
			break;
		case '6':
			if (onScreen.length() != 15) {
				face.writeToScreen(onScreen + "6");
				onScreen = onScreen + "6";
			}
			break;
		case '7':
			if (onScreen.length() != 15) {
				face.writeToScreen(onScreen + "7");
				onScreen = onScreen + "7";
			}
			break;
		case '8':
			if (onScreen.length() != 15) {
				face.writeToScreen(onScreen + "8");
				onScreen = onScreen + "8";
			}
			break;
		case '9':
			if (onScreen.length() != 15) {
				face.writeToScreen(onScreen + "9");
				onScreen = onScreen + "9";
			}
			break;
		case '0':
			if (onScreen.length() != 15) {
				face.writeToScreen(onScreen + "0");
				onScreen = onScreen + "0";
			}
			break;
		case '.':
			// make sure there is no decimal already on the screen.
			if (onScreen.length() != 15 && onScreen.indexOf(".") == -1) {
				face.writeToScreen(onScreen + ".");
				onScreen = onScreen + ".";
			}
			break;
		case '+':
			if (anyOps()) {
				op2 = val();
				// The operation
				face.writeToScreen("" + result());
				op1 = result();
				onScreen = "";
				plus = true;
			}
			else {
				plus = true;
				op1 = val();
				onScreen = "";
			}
			break;
		case '-':
			if (anyOps()) {
				op2 = val();
				// The operation
				face.writeToScreen("" + result());
				op1 = result();
				onScreen = "";
				minus = true;
			}
			else {
				minus = true;
				op1 = val();
				onScreen = "";
			}
			break;
		case '*':
			if (anyOps()) {
				op2 = val();
				// The operation
				face.writeToScreen("" + result());
				op1 = result();
				onScreen = "";
				times = true;
			}
			else {
				times = true;
				op1 = val();
				onScreen = "";
			}
			break;
		case '/':
			if (anyOps()) {
				op2 = val();
				// The operation
				face.writeToScreen("" + result());
				op1 = result();
				onScreen = "";
				divide = true;
			}
			else {
				divide = true;
				op1 = val();
				onScreen = "";
			}
			break;
		case CalculatorFace.PLUS_MINUS:
			face.writeToScreen("-" + onScreen);
			onScreen = "-" + onScreen;
			break;
		case '=':
			op2 = val();
			face.writeToScreen("" + result());
			op1 = result();
			onScreen = "";
			break;
		case 'C':
			face.writeToScreen("");
			onScreen = "";
			noOps();
			break;	

		}
		
	}
	
	private static double val() {
		return Double.parseDouble(onScreen);
	}
	
	private static boolean anyOps() {
		return plus || minus || times || divide;
	}
	
	private static void noOps() {
		plus = false;
		minus = false;
		times = false;
		divide = false;
	}
	
	private static double result() {
		if (plus) return op1 + op2;
		if (minus) return op1 - op2;
		if (times) return op1 * op2;
		if (divide) return op1 / op2;
		return 0.0;
	}
}
