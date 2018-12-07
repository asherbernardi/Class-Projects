package game;

import java.util.ArrayList;

public class Player {
	private ArrayList<Item> inventory = new ArrayList<Item>();
	private ArrayList<String> commands = new ArrayList<String>();
	
	public Player() {
		
	}
	
	public void addItem(Item item) {
		inventory.add(item);
	}
	
	public void removeItem(Item item) {
		inventory.remove(item);
	}
	
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	public boolean hasItem(Item item) {
		for (Item i: inventory) {
			if (i == item) return true;
		}
		return false;
	}
	
	public String listCommands(Room room) {
		String output = "";
		for (String s: commands)
			output += s + "\n";
		for (Item i: inventory)
			output += i.listCommands(room);
		return output;
	}
	
	public String listInventory() {
		String output = "";
		if (inventory.isEmpty())
			return "nothing\n";
		for (Item i: inventory)
			output += i.getName() + "\n";
		return output;
	}
	
	public boolean hasCommand(String command) {
    	for (String s: commands)
    		if (s.equals(command))
    			return true;
    	for (Item i: inventory)
    		if (i.hasCommand(command))
    			return true;
    	return false;
    }
	
	public boolean hasKeyOf(Lock lock) {
		for (Item item: inventory) {
			if (item instanceof Key)
				return ((Key)item).unlocks(lock);
		}
		return false;
	}

	public Key keyOf(Lock lock) {
		for (Item item: inventory) {
			if (item instanceof Key)
				if (((Key)item).unlocks(lock))
					return (Key)item;
		}
		return null;
	}
	
}
