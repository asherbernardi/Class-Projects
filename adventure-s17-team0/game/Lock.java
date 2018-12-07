package game;

/**
 * Key.java
 * 
 * Every door has a Lock, the lock must be unlocked for the player to go through the door.
 *
 * @author ASher Bernardi
 * Wheaton College, CS 245, Spring 2017
 * Lab 5
 */

public class Lock {
	private boolean locked;
	private Container container;
	
	public Lock(boolean l) {
		locked = l;
	}
	
	public void setContainer(Container c) {
		container = c;
	}
	
	public Container getContainer() {
		return container;
	}
	
	public boolean isLocked() {
		return locked;
	}
	
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
