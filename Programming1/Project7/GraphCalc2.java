/**
 * GraphCalc.java
 *
 * Graphing calculator object.
 *
 * @author
 * CSCI 235, Wheaton College, Fall 2016
 * Project 7
 * Date?
 */


import javax.swing.*;  
import java.awt.*;
import java.awt.event.*;

public class GraphCalc2 {

    /**
     * Contructor.
     */
    public GraphCalc2() {

        JFrame window = new JFrame("Graphing calculator");
    	window.setLayout(new FlowLayout());
        window.setBackground(Color.WHITE);
    	window.setSize(500, 650);
        
        // Create and add the panel in which the graph will be drawn.
        PaintPanel graphPanel = new PaintPanel(350, 350);
        window.add(graphPanel);

        // All the text fields for input:
        JTextField funcField = new JTextField(25);
        JTextField equaField1 = new JTextField(15);
        JTextField equaField2 = new JTextField(15);
        JTextField xminField = new JTextField(5);
        JTextField yminField = new JTextField(5);
        JTextField xmaxField = new JTextField(5);
        JTextField ymaxField = new JTextField(5);

        // initialize the min and max fields.
        xminField.setText("-10");
        yminField.setText("-10");
        xmaxField.setText("10");
        ymaxField.setText("10");

        // The panel in which the text fields and buttons will be.
    	JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(400,200));
    	panel2.setLayout(new GridLayout(5,1));

        // The panel in which the radio buttons will go.
        JPanel panel2_1 = new JPanel(new FlowLayout());
        // The radio buttons.
        JRadioButton chooseFunction = new JRadioButton("Function");
        panel2_1.add(chooseFunction);
        chooseFunction.setSelected(true);
        JRadioButton chooseEquation = new JRadioButton("Equation");        
        panel2_1.add(chooseEquation);
        // Group the two radio buttons together.
        ButtonGroup feGroup = new ButtonGroup();
        feGroup.add(chooseFunction);
        feGroup.add(chooseEquation);
        panel2.add(panel2_1);

        // The panel in which the function or equation fields are displayed
        JPanel funcPanel = new JPanel(new FlowLayout());
        funcPanel.add(new JLabel("f(x) = "));
        funcPanel.add(funcField);
        // Add RadioListener to the two radio buttons.
        chooseFunction.addActionListener(new RadioListener(funcField,equaField1,
                                                    equaField2,funcPanel, true));
        chooseEquation.addActionListener(new RadioListener(funcField,equaField1,
                                                    equaField2,funcPanel, false));

        panel2.add(funcPanel);

        // for xmin and xmax values.
        JPanel panel2_2 = new JPanel(new FlowLayout());
        panel2_2.add(new JLabel("x min: "));
        panel2_2.add(xminField);
        panel2_2.add(new JLabel("x max: "));
        panel2_2.add(xmaxField);
        panel2.add(panel2_2);

        // for ymin and ymax values.
        JPanel panel2_3 = new JPanel(new FlowLayout());
        panel2_3.add(new JLabel("y min: "));
        panel2_3.add(yminField);
        panel2_3.add(new JLabel("y max: "));
        panel2_3.add(ymaxField);
        panel2.add(panel2_3);

        // add the "Go" button
        JButton go = new JButton("Go");
    	panel2.add(go);
        GoListener listener = new GoListener(xminField,
                                             xmaxField,
                                             yminField,
                                             ymaxField,
                                             chooseFunction,
                                             chooseEquation,
                                             equaField1,
                                             equaField2,
                                             funcField,
                                             graphPanel);
        go.addActionListener(listener);

        // To initiate the graph as painted.
        Painter listener2 = new GoListener(xminField,
                                             xmaxField,
                                             yminField,
                                             ymaxField,
                                             chooseFunction,
                                             chooseEquation,
                                             equaField1,
                                             equaField2,
                                             funcField,
                                             graphPanel);
        graphPanel.setPainter(listener2);
        //graphPanel.repaint();

        window.add(panel2);
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.setVisible(true);
    }

    /**
     * Main
     */
    public static void main(String[] args) {
        GraphCalc2 theWindow = new GraphCalc2();
    }

}
