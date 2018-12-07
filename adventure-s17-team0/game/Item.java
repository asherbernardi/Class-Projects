package game;

import java.util.ArrayList;

public class Item {
	private boolean canBePickedUp;

	
	private ArrayList<String> commands = new ArrayList<String>();
	
	private String name;
	
	public Item(String n, boolean pickUp) {
		name = n;
		canBePickedUp = pickUp;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean canBePickedUp() {
		return canBePickedUp;
	}
	
	public void makePickUp() {
		canBePickedUp = true;
	}
	
	public void pickUp(Player p) {
		if (canBePickedUp) {
			p.addItem(this);
		}
		else System.out.println(name + " cannot be picked up.");
	}
	
	public String listCommands(Room room) {
		String output = "";
		for (String s: commands) {
			output += s + "\n";
		}
		return output;
	}
	
	public void addCommand(String command) {
		commands.add(command);
	}
	
	public boolean hasCommand(String command) {
		for (String s: commands)
    		if (s.equals(command))
    			return true;
    	return false;
	}
}
