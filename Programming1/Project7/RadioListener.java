/**
 * RadioListener.java
 *
 * The Listener for the function/equation toggling radio buttons.
 * When function is pressed, it will display the funcField, and when equation is
 * pressed, it will display the equation fields.
 *
 * @author ASher Bernardi
 *
 * CSCI 235, Wheaton College, Fall 2016
 * Project 7
 * Dec 3, 2016
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RadioListener implements ActionListener {

	/**
	 * The JPanel which will change.
	 */
	private JPanel funcPanel;

	/**
	 * The JTextFields that will displayed or not displayed in funcPanel.
	 */
	private JTextField funcField, equaField1, equaField2;

	/**
	 * The boolean that determines whether to display function or equation.
	 */
	private boolean isFunction;

	/**
	 * Contructor.
	 * @param funcField   The field in which a function will be typed.
	 * @param equaField1  The field in which the left side of an equation is put.
	 * @param equaField2  The field in which the right side of an equation is put.
	 * @param funcPanel   the JPanel in which either field is placed.
	 * @param isFunction  if the function field should be displayed, otherwise it
	 *                    would be the equation fields.
	 */
	public RadioListener(JTextField funcField,
						 JTextField equaField1,
						 JTextField equaField2,
						 JPanel funcPanel,
						 boolean isFunction) {
		this.funcPanel = funcPanel;
		this.funcField = funcField;
		this.equaField1 = equaField1;
		this.equaField2 = equaField2;
		this.isFunction = isFunction;
	}

	/**
	 * Toggle between displaying the function field or the equation fields.
	 */
	public void actionPerformed(ActionEvent ae) {
	    funcPanel.removeAll();	    
	    if (isFunction) {
	        funcPanel.add(new JLabel("f(x) = "));
	        funcPanel.add(funcField);
	    }
	    else {
	        funcPanel.add(equaField1);
	        funcPanel.add(new JLabel(" = "));
	        funcPanel.add(equaField2);
	    }
	    funcPanel.validate();
	    funcPanel.repaint();

    }
}