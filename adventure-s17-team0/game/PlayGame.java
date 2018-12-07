package game;

import java.util.*;

/**
 * PlayGame.java
 * 
 * Program to control the playing of this game.
 *
 * @author Thomas VanDrunen
 * Wheaton College, CS 245, Spring 2007
 * Lab 5
 * Feb 8, 2007
 */

public class PlayGame {
    
    public static void main(String[] args) {
        System.out.println("Welcome to the Adventure Game made by Asher Bernardi, Zach Van Dyke, Micah Kimel"
        		+ "\nYou wake up in a strange place...."
        		+ "\nYou seem to be in an unknown bedroom."
        		+ "\nHow will you get out??? Have you been kidnapped?"
        		+ "\nYou try opening the door to the bedroom, but it is locked."
        		+ "\nYou will need a key to escape, but how many doors will you"
        		+ "\nneed to unlock? You realize that you will have unlock every"
        		+ "\ndoor in the entire house to escape!"
        		+ "\n\nHmmm... Is that smoke you smell? You wonder what that is..."
        		+ "\n\n     (Type in \"help\" for help.)");
        Game game = new Game();   // reference to the game object
        Parser parser = new Parser();
        Timer timer = new Timer();
        timer.schedule( new TimerTask() {
            public void run() {
            game.increaseTime();
            }
         }, 0, 1000);

        while (! game.isOver()) 
            parser.executeTurn(game);
        int hour = game.getHour();
        int min = game.getMin();
        
        System.out.println("You took " + (hour - 11) + " hour(s) and " + min + " minute(s) to beat the game!\n");
        System.out.println("Congrats! You've escaped from your prison!\n"
        		+ "After all that escaping, you are so relieved to be finally be free.\n"
        		+ "Now... How do you get home? You don't realize where you are.\n"
        		+ "hmmm.......... that is unfortunate.\n");

        System.out.println("Game over.");
    }
    
}
