/**
 * ClockModel.java
 * 
 * Interface to specify how the rest of the system interacts with
 * the time-keeping model.
 * 
 * @author Thomas VanDrunen
 * CS 335, Wheaton College, Fall 2007
 * State pattern example
 * Sept 20, 2007
 */

public interface ClockModel {

    /**
     * Indicate what item is selected from the drop-menu.
     * @param code A numeric code for the menu item: 0 - running clock,
     * 1 - setting hours, 2 - setting minutes, 3 - setting seconds,
     * 4 - toggling am/pm.
     */
    void menuSelection(int code);
    
    /**
     * Act on the left button.
     */
    void left();
    
    /**
     * Act on the right button.
     */
    void right();
    
    /**
     * Give a pulse for time-keeping, expected to be called every
     * second.
     */
    void pulse();
    
}