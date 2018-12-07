package game;

/**
 * Key.java
 * 
 * Class to model a key in the game. Subclass of Item.
 *
 * @author ASher Bernardi
 * Wheaton College, CS 245, Spring 2017
 * Lab 5
 */

public class Key extends Item {
	private Lock unlocks;
	
	public Key(String name, Lock unlocks) {
		// Keys can always be picked up.
		super(name, true);
		this.unlocks = unlocks;
		//super.addCommand("unlock " + unlocks.getNameOfDoor());
	}
	
	public boolean unlocks(Lock lock) {
		return unlocks == lock;
	}
	
	@Override
	public String listCommands(Room room) {
		if (room.hasLock(unlocks)) {
			return "unlock " + room.getCommandFor(room.getContainerOfLock(unlocks)) + "\n";
		}
		return "";
	}
}
