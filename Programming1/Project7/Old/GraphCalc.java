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

public class GraphCalc {

    public GraphCalc() {

        JFrame window = new JFrame("Graphing calculator");
    	window.setLayout(new FlowLayout());
        window.setBackground(Color.WHITE);
    	window.setSize(400, 600);
        
        PaintPanel graphPanel = new PaintPanel(350, 350);

        JTextField funcField = new JTextField(25);
        JTextField xminField = new JTextField(5);
        JTextField yminField = new JTextField(5);
        JTextField xmaxField = new JTextField(5);
        JTextField ymaxField = new JTextField(5);

        xminField.setText("-10");
        yminField.setText("-10");
        xmaxField.setText("10");
        ymaxField.setText("10");

        JButton go = new JButton("Go");

    	window.add(graphPanel);
    	JPanel panel2 = new JPanel();
    	panel2.setLayout(new GridLayout(4,1));

        JPanel panel2_1 = new JPanel(new FlowLayout());
        panel2_1.add(new JLabel("f(x) = "));
        panel2_1.add(funcField);
        panel2.add(panel2_1);
        
        JPanel panel2_2 = new JPanel(new FlowLayout());
        panel2_2.add(new JLabel("x min: "));
        panel2_2.add(xminField);
        panel2_2.add(new JLabel("x max: "));
        panel2_2.add(xmaxField);
        panel2.add(panel2_2);

        JPanel panel2_3 = new JPanel(new FlowLayout());
        panel2_3.add(new JLabel("y min: "));
        panel2_3.add(yminField);
        panel2_3.add(new JLabel("y max: "));
        panel2_3.add(ymaxField);
        panel2.add(panel2_3);

    	panel2.add(go);
        GoListener listener = new GoListener(xminField,
                                             xmaxField,
                                             yminField,
                                             ymaxField,
                                             funcField,
                                             graphPanel);
        go.addActionListener(listener);

        window.add(panel2);
    	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	window.setVisible(true);
    }

    public static void main(String[] args) {
        GraphCalc theWindow = new GraphCalc();
    }
}
