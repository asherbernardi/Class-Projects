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
     * Act on the left button.
     */
    void left();
    
    /**
     * Act on the right button.
     */
    void right();
    
    /**
     * Act on the center button.
     */
    void center();
    
    /**
     * Give a pulse for time-keeping, expected to be called every
     * second.
     */
    void pulse();

}