/**
 * AddListener.java
 * 
 * Class to implement an action listener which will
 * add a new Sprite to the system.
 *
 * @author CGG and Asher Bernardi and Carrie Bai
 * CSCI 235, Wheaton College, Fall 2016
 * Lab 14
 * Dec. 1, 2016
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddListener implements ActionListener {
    
    /**
     * The simulation to which this Sprite will be added.
     */
    private Sprites sim;

    /**
     * The window for the dialog
     */
    private JFrame dialog;


    //for Sprite's position, velocity, direction, and rotational velocity
    private JTextField xPos, yPos, vel, dir, rot;

    //for an object of JColorChooser
    private JColorChooser colorChooser;



    
    /**
     * Simple constructor.
     * @param sim The simulation for the Sprites.
     */
    public AddListener(Sprites sim) {
	this.sim = sim;

	// create the window for the dialog here,
	//   including laying out all of its components
	//   and associating an action listener with its button
	dialog = new JFrame ("Add a Sprite");
	dialog.setSize(636, 500);

	dialog.setLayout(new FlowLayout());
	JPanel top = new JPanel(); //JPanel for top row
	dialog.add(top); //add it to the JFrame dialog
	top.setLayout(new FlowLayout()); //set the layout of this JPanel to 10 columns.

	//Components of "top"
        top.add(new JLabel("x"));
        xPos = new JTextField(6);
	top.add(xPos);
	top.add(new JLabel("y"));
        yPos = new JTextField(6);
	top.add(yPos);
	top.add(new JLabel("speed"));
        vel = new JTextField(6);
	top.add(vel);
	top.add(new JLabel("direction"));
	dir = new JTextField(6);
	top.add(dir);
	top.add(new JLabel("rotation"));
        rot = new JTextField(6);
	top.add(rot);


	//Middle of JFrame
        colorChooser = new JColorChooser();
	dialog.add(colorChooser);

	//Bottom of JFrame
	JPanel bottom = new JPanel();
	dialog.add(bottom); //add it to the JFrame
	bottom.setLayout(new FlowLayout());

	// create a cancel button and add it to a GUI component
	JButton cancelButton = new JButton("Cancel");
	cancelButton.addActionListener(new CancelListener(this));

	// create an OK button and add it to a GUI component
	JButton okButton = new JButton("OK");
	okButton.addActionListener(new OkListener(this));

	bottom.add(cancelButton); //add these buttons onto the bottom
	bottom.add(okButton); 
	
	dialog.setVisible(false);
	dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }

    /**
     * React to the button being pressed...
     * @param ae unused
     */
    public void actionPerformed(ActionEvent ae) {
	// pause the simulation
	sim.stop();

	// make the dialog visible
	dialog.setVisible(true);
	// the action listener for the dialog will call
	//   reset() when it is done
    }


    /**
     * End the dialog, hiding the window and resuming the simulation.
     */
    public void reset() {
	// reset anything, including the visibility of the dialog
	dialog.setVisible(false);
	// resume the simulation
	sim.start();
    }

    /**
     * Create a new Sprite to add to the simulation.
     */

    public void addNewSprite() {
	int xPosi = Integer.parseInt(xPos.getText());
	int yPosi = Integer.parseInt(yPos.getText());
	double dire = Double.parseDouble(dir.getText());
	double rota = Double.parseDouble(rot.getText());
	double velo = Double.parseDouble(vel.getText());
	Color col = colorChooser.getColor();

        sim.addSprite(xPosi, yPosi, dire, rota, velo, col); //add new sprite to simulation window

    }

    // write a method for generating a Sprite.
    // this method will be called after the OK button is pressed.
    // read information from the text fields and the color,
    // add a new Sprite to the grid, and
    // call reset() to hide the dialog window.
    


    
    /**
     * Static main method:  display the window, exit when closed.
     */
    public static void main(String args[]) {
	AddListener al = new AddListener(null);
        al.dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	al.dialog.setVisible(true);
    }

}
