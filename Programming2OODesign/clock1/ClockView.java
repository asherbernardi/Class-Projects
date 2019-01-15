/**
 * ClockView.java
 * 
 * Interface to specify how other parts of the clock system
 * interact witht the view.
 * 
 * @author Thomas VanDrunen
 * CS 335, Wheaton College, Fall 2007
 * State pattern example
 * Sept 20, 2007
 */

public interface ClockView {

    /**
     * Display a time, in (H|HH):MM:SS:(AM/PM) format (for examples, 
     * "11:47:22:AM" or "5:05:00:PM").
     * @param time The time, with parts of the time separated by colons.
     */
    void display(String time);

}