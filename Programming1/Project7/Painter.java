/**
 * Painter.java
 *
 * Interface for an object that will handle the painting
 * of a paintable GUI component.
 *
 * @author Thomas VanDrunen
 * CSCI 235, Wheaton College, Fall 2016
 * Nov. 30, 2016
 */
import java.awt.Graphics;

public interface Painter {

    /**
     * Update the display using the given graphics
     * object.
     * @param g The graphics object to manipulate
     */
    public void paint(Graphics g);

}
