/**
 * GoListener.java
 *
 * The action listener for the "Go" button.
 *
 * @author ASher Bernardi
 *
 * CSCI 235, Wheaton College, Fall 2016
 * Project 7
 * Dec 7, 2016
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GoListener implements ActionListener, Painter {
	
	/**
	 * The textFields where you input the parameters of the graph.
	 */
	private JTextField xminS, xmaxS, yminS, ymaxS, functionS, equa1S, equa2S;

	/**
	 * The parsed integers, as inputted input the textFields.
	 */
	private int xmin, xmax, ymin, ymax;

	/**
	 * The string of the function expression.
	 */
	private String function, equa1, equa2;

	/**
	 * An interpreter Tree to compute the function.
	 */
	private ExprNode funcTree, equaTree1, equaTree2;

	/**
	 * The radio buttons that determine whether to graph a function or equation.
	 */
	private JRadioButton chooseFunc, chooseEqua;

	/**
	 * The paintPanel on which the graph will be displayed.
	 */
	private PaintPanel p;

	/**
	 * Contructor
	 * @param xminS       The textField where xmin is inputted.
	 * @param xmaxS       The textField where xmax is inputted.
	 * @param yminS       The textField where ymin is inputted.
	 * @param ymaxS       The textField where ymax is inputted.
	 * @param chooseFunc  The RadioButton for function.
	 * @param chooseEqua  The RadioButton for equation.
	 * @param equa1S      The field to enter the left side of the equation.
	 * @param equa2S      The field to enter the right side of the equation.
	 * @param functionS   The textField where the function expression.
	 * @param p           The paintPanel on which the graph will be displayed.
	 */
	public GoListener(JTextField xminS, JTextField xmaxS, JTextField yminS, JTextField ymaxS,
					  JRadioButton chooseFunc, JRadioButton chooseEqua,
					  JTextField equa1S, JTextField equa2S,
					  JTextField functionS, PaintPanel p) {
		this.xminS = xminS;
		this.xmaxS = xmaxS;
		this.yminS = yminS;
		this.ymaxS = ymaxS;
		this.functionS = functionS;
		this.equa1S = equa1S;
		this.equa2S = equa2S;
		this.chooseFunc = chooseFunc;
		this.chooseEqua = chooseEqua;
		this.scanTextFields();
		this.p = p;
	}

	public void actionPerformed(ActionEvent ae) {
		p.setPainter(this);
		p.repaint();
	}

	public void paint(Graphics g) {
		// Rescan the textFeilds for new data.
		this.scanTextFields();

		// The center values in the x/y-directions are -xmin/-ymin from the left/bottom
		int centerx = xToCol(-xmin);
		int centery = p.height() - yToRow(-ymin);

		// Draw incremental notches.
		g.setColor(Color.LIGHT_GRAY);
		for (int i = 0; i < xmax-xmin; i++) {
			g.drawLine(xToCol(i), centery+1, xToCol(i), centery+3);
		}
		for (int i = 0; i < ymax-ymin; i++) {
			g.drawLine(centerx-1, yToRow(i), centerx-3, yToRow(i));
		}

		// Draw axes:
		g.setColor(Color.GRAY);
		g.drawLine(centerx, 0, centerx, p.height()); // x-axis
		g.drawLine(0, centery, p.width(), centery);  // y-axis


		// Draw the graph, it depends on whether function or equation is selected:
		g.setColor(Color.BLACK);
		// The coordinates of each pixel that draws the graph.
		int xdot;
		int ydot;
		if (chooseFunc.isSelected()) {
			for (double i = 0; i < xmax - xmin; i+=0.001) {
				xdot = xToCol(i);
				try {
					// yval is the value of the evaluated function expression for x.
					// + xmin to callibrate.
					double yval = funcTree.evaluate(i+xmin, 0.0);
					// Rows are counted from the top, so the minimum y must be
					// subtracted from the height.
					ydot = yToRow(ymax-yval);

					g.fillRect(xdot,ydot,1,1);
				}
				catch (Exception e) { };
			}
		}
		else {
			for (int x = 0; x < p.width(); x++) {
				for (int y = 0; y < p.height(); y++) {
					double xval = colToX(x) + xmin;
					double xval2 = colToX(x+1) + xmin;
					double yval = ymax - rowToY(y);
					double yval2 = ymax - rowToY(y+1);
					//System.out.println(xval + " " + yval + " " + yval2);

					if (equaTree1.evaluate(xval, yval) < equaTree2.evaluate(xval, yval) &&
						equaTree1.evaluate(xval, yval2) >= equaTree2.evaluate(xval, yval2))
						g.fillRect(x,y,1,1);
					if (equaTree1.evaluate(xval, yval) > equaTree2.evaluate(xval, yval) &&
						equaTree1.evaluate(xval, yval2) <= equaTree2.evaluate(xval, yval2))
						g.fillRect(x,y,1,1);
					if (equaTree1.evaluate(xval, yval) < equaTree2.evaluate(xval, yval) &&
						equaTree1.evaluate(xval2, yval) >= equaTree2.evaluate(xval2, yval))
						g.fillRect(x,y,1,1);
					if (equaTree1.evaluate(xval, yval) > equaTree2.evaluate(xval, yval) &&
						equaTree1.evaluate(xval2, yval) <= equaTree2.evaluate(xval2, yval))
						g.fillRect(x,y,1,1);
				}
			}
		}
	}

	/**
	 * A method to compute the pixel column of a particular x value distance
	 * from the left in the paintPanel.
	 * @param x   The x value.
	 */
	private int xToCol(double x) {
		return (int) (x*p.width()/(xmax-xmin));
	}

	/**
	 * A method to compute the pixel row of a particular y value distance
	 * from the top in the paintPanel.
	 * @param y   The y value.
	 */
	private int yToRow(double y) {
		return (int) (y*p.width()/(ymax-ymin));
	}

	/**
	 * Compute the x-value equivalent to each pixel in the x-direction.
	 * @param col   the column number.
	 */
	private double colToX(int col) {
		return (double) col*(xmax-xmin)/(double)p.width();
	}

	/**
	 * Compute the y-value equivalent to each pixel in the y-direction.
	 * @param col   the column number.
	 */
	private double rowToY(int row) {
		return (double) row*(ymax-ymin)/(double)p.height();
	}

	/**
	 * Scan the textFields for any new information and place it into the variables.
	 * If there's an incorrect input, display an error window.
	 */
	private void scanTextFields(){
		try {
			this.xmin = Integer.parseInt(xminS.getText());
			this.xmax = Integer.parseInt(xmaxS.getText());
			this.ymin = Integer.parseInt(yminS.getText());
			this.ymax = Integer.parseInt(ymaxS.getText());
			// this if/else block handles whether the function radio button or the
			// equation radio button is pressed.
			if (chooseFunc.isSelected()) {
				if (!functionS.getText().equals(""))
					this.funcTree = Interpreter.parse(functionS.getText());
				if (functionS.getText().contains("y")) {
					throw new RuntimeException();
				}
			}
			else {
				if (!equa1S.getText().equals(""))
					this.equaTree1 = Interpreter.parse(equa1S.getText());
				if (!equa2S.getText().equals(""))
					this.equaTree2 = Interpreter.parse(equa2S.getText());
			}
		}
		catch (Exception e) {
			JFrame error = new JFrame("Error");
			error.setSize(200,75);
			error.setLayout(new FlowLayout());
			error.setLocation(150,150);
			error.setVisible(true);
			error.add(new JLabel(e.toString()));
		}

	}
}