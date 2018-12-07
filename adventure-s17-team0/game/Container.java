package game;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Container.java
 * 
 * A container is an object that contains items. A container must be associated
 * with another item. For example, a dresser might have 4 containers to model
 * the four drawers of the dresser.
 *
 * @author ASher Bernardi
 * Wheaton College, CS 245, Spring 2017
 * Project 4
 */
public class Container {

	protected HashMap<String, Container> directionsMap = new HashMap<String, Container>();
	protected HashMap<Container, Lock> locks = new HashMap<Container, Lock>();
	    
	protected ArrayList<String> directions = new ArrayList<String>();
	
	protected String description;
	
	protected String name;
	
	protected ArrayList<Item> items = new ArrayList<Item>();
	
	public Container(String name) {
		this.name = name;
	}
	
	public String name() {
		return name;
	}
	
	public String listPickUpItems() {
		String output = "";
		for (Item i: items)
			if (i.canBePickedUp())
				output += "pick up " + i.getName() + "\n";
		return output;
	}
	
	public String listContainerItem() {
		String output = "";
		for (Item i: items) {
			if (i instanceof ContainerItem) {
				output += "open " + i.getName() + "\n";
			}
		}
		return output;
	}
	
	public void addItem(Item item) {
    	items.add(item);
    	if (item instanceof ContainerItem) {
    		directionsMap.put(item.getName(), ((ContainerItem)item).getContainer());
    		directions.add(item.getName());
    	}
    }
	
	public void addLockedItem(Item item, Lock lock) {
    	items.add(item);
    	if (item instanceof ContainerItem) {
    		directionsMap.put(item.getName(), ((ContainerItem)item).getContainer());
    		directions.add(item.getName());
    		locks.put(((ContainerItem)item).getContainer(), lock);
    		lock.setContainer(((ContainerItem)item).getContainer());
    	}
    }
    
    public void removeItem(String name) {
    	// keep track of the item to remove, so as to avoid a ConcurrentModificationException.
    	Item itemToRemove = null;
    	for (Item i: items)
    		if (name.equals(i.getName()))
    			itemToRemove = i;
    	items.remove(itemToRemove);
    }
    
    public boolean hasItem(String name) {
		for (Item i: items) {
			if (name.equals(i.getName())) return true;
		}
		return false;
	}
    
    public Item getItem(String name) {
		for (Item i: items) {
			if (name.equals(i.getName())) return i;
		}
		return null;
	}
    
    public ContainerItem getContainerItem(String name) {
		for (Item i: items) {
			if (i instanceof ContainerItem)
				if (name.equals(i.getName())) {
					return (ContainerItem)i;
				}
		}
		return null;
	}
    
    public void setDescription(String s){
    	description = s;
    }
    
    /**
     * Retrieve a description of this room (to the user).
     */
    public String getDescription() { return description; }
    
    public String listContainers() {
    	String output = "";
    	for (Item i: items){
    		if (i instanceof ContainerItem) {
    			output += i.listCommands(null);
    		}
    	}
    	return output;
    }
    
    /**
     * Connects a container to another container by way of a command and sets a lock on the connection.
     * @param command The command that takes you to the next room.
     * @param room The room to go to.
     * @param lock The lock between this room and the next room.
     */
    public void setDirection(String command, Room room, Lock lock) {
    	directionsMap.put(command, room);
    	directions.add(command);
    	locks.put(room, lock);
    	lock.setContainer(room);
    }
    
    public boolean hasDirection(String command) {
    	return directions.contains(command);
    }
    
    public boolean hasLock(Lock lock) {
    	return locks.containsValue(lock);
    }
    
    public Container getContainerOfLock(Lock lock) {
    	for (Container c: locks.keySet()) {
    		if (locks.get(c) == lock)
    			return c;
    	}
    	return null;
    }
    
    public boolean hasDirection(Container container) {
    	return directionsMap.containsValue(container);
    }
    
    public Container getDirection(String s) { return directionsMap.get(s); }
    
    public String getCommandFor(Container c) {
    	for (String s: directions) {
    		if (directionsMap.get(s) == c)
    			return s;
    	}
    	return "";
    }
    
    public Lock getLock(Container c){ return locks.get(c); }
    
    public boolean nextIsLocked(String command) {
    	// keeps track of the next room.
    	Container nextContainer = directionsMap.get(command);
    	assert(nextContainer != null);
    	
    	if (locks.containsKey(nextContainer))
    		return locks.get(nextContainer).isLocked();
    	else return false;
    }
    
    public String listCommands() {
    	String output = "";
    	for (String s: directions) {
    		if (directionsMap.get(s) instanceof Room) output += s + "\n";
    	}
    	return output;	
    }
	
}
