package game;

/**
 * Parser.java
 * 
 * Class to interpret the user's commands
 *
 * @author Thomas VanDrunen
 * Wheaton College, CS 245, Spring 2007
 * Lab 5
 * Feb 8, 2007
 */

import java.util.Scanner;

public class Parser {
	//quit fight
	boolean fight = true;
	//quit random events due to time
	boolean mouse = true;
	boolean leak = true;
	boolean scream = true;
	boolean howl = true;
	boolean crying = true;
	boolean slip = true;
    /**
     * For user input from the keyboard.
     */
    private Scanner keyboard;

    /**
     * Plain constructor
     */
    public Parser() {
        keyboard = new Scanner(System.in);
    }

    /**
     * Let the user make one "turn" at this game.
     * Give the user a description of the room, prompt for
     * a command, and interpret the command.
     * @param game A reference to the object representing the game.
     * 
     */
    public void executeTurn(Game game) {
    	// Return line when a new turn is begun.
    	System.out.println();
    	
        // The room that the user is in.
        Room room = game.getCurrentRoom();
        // The Container the user may be looking at.
        Container container = game.getCurrentBox();
        // The player the user is playing.
        Player player = game.getFirstPlayer();

        game.increaseTime();
        //easter egg if time makes it to midnight
        if (game.getHour() == 12 && fight){
        	System.out.println("It is midnight...........\n" + "Your kidnaper, Wheaton College President Philip Ryken, comes out!"
        			+ "\nHe wants to Fight! He throws a punch, and hits you. You stumble back, dazed."
        			+ "\nYou're angery because he took your Bojangles Sinomen Biscuts.  You fight back hard!! \n "
        			+ "You punch, and punch, and punch, and punch... you wake up, it was all a dream.");
        	fight = false;
        }
        
        if(game.getMin() >= 1 && mouse){
        	System.out.println("A mouse runs out across the room to who knows where...\n"); mouse = false;
        }
        if(game.getMin() >= 3 && scream){
        	System.out.println("You hear a loud scream in the distance.\n"); scream = false;
        }
        if(game.getMin() >= 5 && leak){
        	System.out.println("A leak from above drips on you head...\n"); leak = false;
        }
        if(game.getMin() >= 8 && howl){
        	System.out.println("You hear a loud howling in the distance.\n"); howl = false;
        }
        if(game.getMin() >= 12 && slip){
        	System.out.println("You slip and fall, but quickly get up determined to find your way out.\n"); slip = false;
        }
        if(game.getMin() >= 15 && crying){
        	System.out.println("You hear crying of in the distance.\n"); crying = false;
        }

        //System.out.println("Time: " + game.getTime());
        
        if (container == null) System.out.println(room.getDescription());
        else System.out.println(container.getDescription());

        //System.out.println("/n" + "commands:" room.listCommands());
        System.out.print("Enter command--> ");
        String command = keyboard.nextLine().toLowerCase();  // user's command

        System.out.println();

        if (container == null && (command.equals("north") || command.equals("south") 
            || command.equals("west") || command.equals("east"))) {
            Room nextRoom;   // the room we're moving to
            if (command.equals("north"))
                nextRoom = room.getNorth();
            else if (command.equals("south"))
                nextRoom = room.getSouth();
            else if (command.equals("west"))
                nextRoom = room.getWest();
            else
                nextRoom = room.getEast();
            if (nextRoom == null) 
                System.out.println("There is no door in that direction.");
            else if (room.nextIsLocked(command)) {
        		System.out.println("The door is locked.");
        	}
            else if (nextRoom.isOnFire()) {
            	System.out.println("AAAH!!! The room you are trying to get into is on fire!\n"
            			+ "You'd better put the fire out before entering the room.");
            	// When the user first sees fire, the extinguisher becomes "pick-up-able"
            	if (!game.getExtinguisher().canBePickedUp()) game.getExtinguisher().makePickUp();
            }

        	else {
        		System.out.println("You went " + command + ".");
        		game.setCurrentRoom(nextRoom);
        	}

        }
        else if (room.hasDirection(command)) {
        	if (container == null) {
        		if (room.getDirection(command) instanceof Room) {
		        	if (room.nextIsLocked(command)) {
		        		System.out.println("The door is locked.");
		        	}
		        	else {
		        		System.out.println("You went " + command + ".");
		        		game.setCurrentRoom((Room)room.getDirection(command));
		        	}
        		}
        		else System.out.println("You have to open " + command);
        	}
        	else {
        		System.out.println("close the " + container.name() + " first.");
        	}
        }
        else if (command.equals("help")){
        	System.out.println("Here is what you can do:\n");
        	System.out.println("help");
        	System.out.println("inventory");
        	if (container == null) {
            	System.out.print(player.listCommands(room));
	        	System.out.print(room.listCommands());
	        	System.out.print(room.listPickUpItems());
	        	System.out.print(room.listContainerItem());
        	}
        	else {
	        	System.out.print(container.listPickUpItems());
	            System.out.print(container.listContainerItem());
	        	System.out.print("close\n");
        	}
        }
        else if (command.equals("inventory")) {
        	System.out.println("Here is what is in your inventory:");
        	System.out.print(player.listInventory());
        }
        else if (command.length() >= 7 && command.substring(0,7).equals("unlock ")) {
        	// keep track of the name of the room to unlock.
        	String containerToUnlock = command.substring(7, command.length());
        	if (room.hasDirection(containerToUnlock)) {
        		if (player.hasKeyOf(room.getLock(room.getDirection(containerToUnlock)))) {
        			room.getLock(room.getDirection(containerToUnlock)).setLocked(false);
        			player.removeItem(player.keyOf(room.getLock(room.getDirection(containerToUnlock))));
        			System.out.println("You unlocked " + containerToUnlock + ".");
        		}
        		else System.out.println("You do not have the right key.");
        	}
        	else {
        		System.out.println("Cannot unlock " + containerToUnlock + ".");
        	}
        }
        else if (command.length() >= 8 && command.substring(0,8).equals("pick up ")) {
        	// Keep track of the name of the item to pick up.
        	String itemToPickUp = command.substring(8, command.length());
        	if (container == null) {
        		if (room.hasItem(itemToPickUp)) {
	        		if (room.getItem(itemToPickUp).canBePickedUp()) {
		        		player.addItem(room.getItem(itemToPickUp));
		        		room.removeItem(itemToPickUp);
		        		System.out.println("You picked up the " + itemToPickUp + ".");
	        		}
	        		else
	        			System.out.println("The " + itemToPickUp + " cannot be picked up.");
        		}
        		else
        			System.out.println("There is no " + itemToPickUp + " to pick up.");
        	}
        	else if (container.hasItem(itemToPickUp)) {
        		if (container.getItem(itemToPickUp).canBePickedUp()) {
	        		player.addItem(container.getItem(itemToPickUp));
	        		container.removeItem(itemToPickUp);
	        		System.out.println("You picked up the " + itemToPickUp + ".");
	        		// This is to set the foyer on fire when the user picks up the key
	        		// to the main entrance.
	        		if (itemToPickUp.equals("main entrance key")) {
	        			game.getFoyer().setOnFire();
	        		}
        		}
        		else
        			System.out.println("The " + itemToPickUp + " cannot be picked up.");
        	}
        	else {
        		System.out.println("There is no " + itemToPickUp + " to pick up.");
        	}
        }
        else if (command.length() >= 5 && command.substring(0,5).equals("open ")) {
        	// Keep track of the name of the item to pick up.
        	String containerToOpen = command.substring(5, command.length());
        	if (room.hasDirection(containerToOpen) && !(room.getDirection(containerToOpen) instanceof Room)) {
        		if (room.nextIsLocked(containerToOpen)) {
	        		System.out.println("The " + room.getItem(containerToOpen).getName() + " is locked.");
	        	}
	        	else {
	        		game.setCurrentBox(room.getContainerItem(containerToOpen).getContainer());
	        		System.out.println("You looked inside the " + containerToOpen);
	        	}
        	}
        	else if (container != null && container.hasDirection(containerToOpen)) {
        		if (container.nextIsLocked(containerToOpen)) {
	        		System.out.println("The " + container.getItem(containerToOpen).getName() + " is locked.");
	        	}
	        	else {
	        		game.setCurrentBox(container.getContainerItem(containerToOpen).getContainer());
	        		System.out.println("You looked inside the " + containerToOpen);
	        	}
        	}
        	else {
        		System.out.println("You cannot open " + containerToOpen + ".");
        	}
        }
        else if (command.equals("close")) {
        	if (container == null) System.out.println("You have not opened anything.");
        	else {
        		System.out.println("You closed the " + game.getCurrentBox().name());
        		game.setCurrentBox(null);
        	}
        }
        else if (command.length() >= 13 && command.substring(0,13).equals("put out fire ")) {
        	String directionToExtinguish = command.substring(13, command.length());
        	System.out.println("You put the fire.");
        	Extinguisher.putOutFire((Room)room.getDirection(directionToExtinguish));
        	room.getDirection(directionToExtinguish).setDescription(room.getDirection(directionToExtinguish).getDescription() + "\nThis room is no on longer on fire, but everything is charred.");
        }
        else
            System.out.println("I do not know how to " + command + ".");
        
        if (game.winCondition())
        	game.finishGame();

    }

}
