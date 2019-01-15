/**
 * Model3.java
 * 
 * A better implementation of the clock model.
  */

public class Model3 implements ClockModel {

    private ClockView view;
    private int hours, minutes, seconds;
    private boolean isAM;
    
    private int current;
    
    private State[] states;
    
    /**
     * Constructor. Set initially for midnight, running clock mode.
     * @param view The clock display.
     */
    public Model3(ClockView view) {
        this.view = view;
        hours = 12; minutes = 0; seconds = 0; isAM = true;
        states = new State[5];
        states[0] = new RunningTime();
        states[1] = new ChangeHours();
        states[2] = new ChangeMinutes();
        states[3] = new ChangeSeconds();
        states[4] = new ChangeAMPM();
        current = 0;
    }
    
    public void menuSelection(int code) {
        current = code;
    }
    public void left() { states[current].left(); }
    public void right() { states[current].right(); }
    public void pulse() { states[current].pulse(); }

    
    private abstract class State {
        
        public abstract void left();
        public abstract void right();
        public void pulse() { }  // do nothing, by default
        
        protected void increaseSeconds() {
            seconds++;
            if (seconds == 60) { seconds = 0; increaseMinutes(); }
        }
        
        protected void increaseMinutes() {
            minutes++;
            if (minutes == 60) { minutes = 0; increaseHours(); }
        }
        
        protected void increaseHours() {
            hours++;
            if (hours == 12) isAM = !isAM;
            if (hours == 13) hours = 1;
        }
        protected void decreaseSeconds() {
            seconds--;
            if (seconds < 0) { seconds = 59; decreaseMinutes(); }
        }
        
        protected void decreaseMinutes() {
            minutes--;
            if (minutes < 0) { minutes = 59; decreaseHours(); }
        }
        
        protected void decreaseHours() {
            hours--;
            if (hours == 11) isAM = !isAM;
            if (hours == 0) hours = 12;
        }
        
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
    }
    
    private class ChangeHours extends State {
        public void left() { decreaseHours(); updateDisplay(); }
        public void right() { increaseHours(); updateDisplay(); }
    }
    
    private class ChangeMinutes extends State {
        public void left() { decreaseMinutes(); updateDisplay(); }
        public void right() { increaseMinutes(); updateDisplay(); }
    }
    
    private class ChangeSeconds extends State {
        public void left() { decreaseSeconds(); updateDisplay(); }
        public void right() { increaseSeconds(); updateDisplay(); }
    }
    
    private class ChangeAMPM extends State {
        public void left() { isAM = !isAM; updateDisplay(); }
        public void right() { isAM = !isAM; updateDisplay(); }
    }
}
