package game;

import java.util.ArrayList;

import java.util.HashMap;

/**
 * Room.java
 * 
 * Class to model a room in the game.
 *
 * @author Thomas VanDrunen, ASher Bernardi, Micah Kimel, Zach VanDyke.
 * Wheaton College, CS 245, Spring 2017
 * Lab 5
 */

public class Room extends Container {

    /**
     * Rooms adjacent to this one, to which there is a door.
     */
    private Room north, south, east, west;

    /**
     * Whether there is a fire blazing in the room or not.
     */
	private boolean isOnFire;

    /**
     * Constructor.
     * @param description A String describing this room to the user.
     */
    public Room(String name, boolean isOnFire) 
    {
    	super(name);
    	this.isOnFire = isOnFire;
    }
    
    /**
     * Methods for added "doors"-- directions connections to other rooms.
     */
    public void setNorth(Room north, Lock lock) {
    	this.north = north;
    	this.setDirection("north", north, lock);
    }
    public void setSouth(Room south, Lock lock) {
    	this.south = south;
    	this.setDirection("south", south, lock);
    }
    public void setEast(Room east, Lock lock) {
    	this.east = east;
    	this.setDirection("east", east, lock);
    }
    public void setWest(Room west, Lock lock) {
    	this.west = west;
    	this.setDirection("west", west, lock);
    }
    
    /**
     * Methods to determine the rooms to which various
     * doors-- if they exist-- lead.
     */
    public Room getNorth() { return north; }
    public Room getSouth() { return south; }
    public Room getEast() { return east; }
    public Room getWest() { return west; }
    
    public boolean isOnFire() { return isOnFire; }

    public void setOnFire() { isOnFire = true; }
    public void putOutFire() { isOnFire = false; }    
	
}
