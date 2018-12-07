/**
 * Model2.java
 * 
 * A better implementation of the clock model.
 * 
 * @author Thomas VanDrunen
 * CS 335, Wheaton College, Fall 2007
 * State pattern example
 * Sept 20, 2007
 */

public class Model4 implements ClockModel {

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
    
    private State current;
    
    /**
     * Constructor. Set initially for midnight, runnign clock mode.
     * @param view The clock display.
     */
    public Model4(ClockView view) {
        this.view = view;
        hours = 12;
        minutes = 0;
        seconds = 0;
        isAM = true;
        current = new RunningTime();
    }
    
    
    /**
     * Act on the left button. 
     */
    public void left() { current.left(); }
    
    /**
     * Act on the right button.
     */
    public void right() { current.right(); }
    
    /**
     * Act on the center button
     */
    public void center() { current = current.nextState(); }
    
    /**
     */
    public void pulse() { current.pulse(); }
    
    private abstract class State {
        
        public abstract void left();
        public abstract void right();
        public abstract State nextState();
        public void pulse() { }  // do nothing, by default
        
        protected void increaseSeconds() {
            seconds++;
            if (seconds == 60) {
                seconds = 0;
                increaseMinutes();
            }
        }
        
        protected void increaseMinutes() {
            minutes++;
            if (minutes == 60) {
                minutes = 0;
                increaseHours();
            }
        }
        
        protected void increaseHours() {
            hours++;
            if (hours == 12) isAM = !isAM;
            if (hours == 13) hours = 1;
        }
        protected void decreaseSeconds() {
            seconds--;
            if (seconds < 0) {
                seconds = 59;
                decreaseMinutes();
            }
        }
        
        protected void decreaseMinutes() {
            minutes--;
            if (minutes < 0) {
                minutes = 59;
                decreaseHours();
            }
        }
        
        protected void decreaseHours() {
            hours--;
            if (hours == 11) isAM = !isAM;
            if (hours == 0) hours = 12;
        }
        
        /**
         * Update what's on the screen.
         */
        protected void updateDisplay() {
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
    
    private class RunningTime extends State {
        public void left() { }
        public void right() { }
        public void pulse() { increaseSeconds(); updateDisplay(); }
        public State nextState() { return new ChangeHours(); }
    }
    
    private class ChangeHours extends State {
        public void left() { decreaseHours(); updateDisplay(); }
        public void right() { increaseHours(); updateDisplay(); }
        public State nextState() { return new ChangeMinutes(); }
    }
    
    private class ChangeMinutes extends State {
        public void left() { decreaseMinutes(); updateDisplay(); }
        public void right() { increaseMinutes(); updateDisplay(); }
        public State nextState() { return new ChangeSeconds(); }
    }
    
    private class ChangeSeconds extends State {
        public void left() { decreaseSeconds(); updateDisplay(); }
        public void right() { increaseSeconds(); updateDisplay(); }
        public State nextState() { return new ChangeAMPM(); }
    }
    
    private class ChangeAMPM extends State {
        public void left() { isAM = !isAM; updateDisplay(); }
        public void right() { isAM = !isAM; updateDisplay(); }
        public State nextState() { return new RunningTime(); }
    }
}