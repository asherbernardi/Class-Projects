/**
 * Model1.java
 * 
 * A first attempt to implement the clock model.
 */

public class Model1 implements ClockModel {

    /**
     * The clock display.
     */
    private ClockView view;
    
    /**
     * Internal representation (as ints) of the current
     * number of hours, minutes, and seconds.
     */
    private int hours, minutes, seconds;
    
    /**
     * Internal representation of whether it's AM or PM.
     */
    private boolean isAM;
    
    /**
     * Code standing for what drop-menu item is selected.
     * 0 - running clock,  1 - setting hours, 2 - setting minutes, 
     * 3 - setting seconds, 4 - toggling am/pm.
     */
    private int code;
    
    /**
     * Constructor. Set initially for midnight, runnign clock mode.
     * @param view The clock display.
     */
    public Model1(ClockView view) {
        this.view = view;
        hours = 12; minutes = 0; seconds = 0; isAM = true;
        code = 0;
    }
    
    /**
     * Update the menu selection code.
     * @param code The new code.
     */
    public void menuSelection(int code) {
        this.code = code;
    }
    
    /**
     * Act on the left button. There's way too much going on here
     * to possibly describe in a comment. (We'll split an infinitive
     * instead.)
     */
    public void left() {
        switch(code) {
        case 4: isAM = !isAM; updateDisplay(); break;
        case 3:
            seconds--;
            if (seconds < 0) seconds = 59; // and fall through
            else { updateDisplay(); break; }
        case 2:
            minutes--;
            if (minutes < 0) minutes = 59;  // and fall through
            else { updateDisplay(); break; }
        case 1:
            hours--;
            if (hours == 11) isAM = !isAM;
            if (hours == 0) hours = 12;
            updateDisplay();
            break;
        case 0:   // do nothing
        }
    }
    
    /**
     * Act on the right button.
     */
    public void right() {
        switch(code) {
        case 4:  isAM = !isAM;  updateDisplay(); break;
        case 3:
            seconds++;
            if (seconds == 60) seconds = 0; // and fall through
            else { updateDisplay(); break; }
        case 2:
            minutes++;
            if (minutes == 60) minutes = 0;  // and fall through
            else { updateDisplay(); break; }
        case 1:
            hours++;
            if (hours == 12) isAM = !isAM;
            if (hours == 13) hours = 1;
            updateDisplay();
            break;
        case 0:   // do nothing
        }
        
    }
    
    /**
     * Receive a pulse for time-keeping. Update the
     * seconds count, with possible cascading updates.
     */
    public void pulse() { 
        switch(code) {
        case 3: case 2: case 1: break; // do nothing
        case 0:
            seconds++;
            if (seconds == 60) {
                seconds = 0;
                minutes++;
                if (minutes == 60) {
                    minutes = 0;
                    hours++;
                    if (hours == 12) isAM = !isAM;
                    else if (hours == 13) hours = 1;
                }
            }
            updateDisplay();
        }
    }
    
    /**
     * Update what's on the screen.
     */
    private void updateDisplay() {
        String toSend = hours + ":";   // the string we'll send to view
        if (minutes < 10) toSend += "0" + minutes + ":";
        else toSend += minutes + ":";
        if (seconds < 10) toSend += "0" + seconds + ":";
        else toSend += seconds + ":";
        if (isAM) toSend += "AM";
        else toSend += "PM";
        view.display(toSend);
     }
}