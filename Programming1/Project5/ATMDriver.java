/**
 * ATMDriver.java
 *
 * A driver for the ATM class.
 *
 * @author ASher Bernardi
 * Wheaton College, CSCI 235, Fall 2016
 * Project 5
 * November 6
 */

import java.util.*;

public class ATMDriver {

	public static void main(String[] args) {
		
		// For keyboard input.
		Scanner keyboard = new Scanner(System.in);

		// First create an ATM object called atm.
		ATM atm = new ATM();

		// Declare a string choice to hold the choice of the user.
		String choice = "";

		// iterate until Q is entered. If Q is entered, break the for loop.
		for (;;) {
			// Print the options.
			System.out.println("Please select an option:\n" + 
							   "     W: withdraw money from the ATM.\n" + 
							   "     T: Deposit twenty-dollar bills into the ATM.\n" +
							   "     F: Deposit five-dollar bills into the ATM.\n" +
							   "     Q: Quit.");

			// Prompt the user for his/her choice.
			choice = keyboard.nextLine();

			// Each case:
			if (choice.equalsIgnoreCase("T")) {
				System.out.println("How many twenties would you like to deposit?");
				// the try block is to check if the input is indeed an integer.
				try {
					int nTwenties = keyboard.nextInt();
					atm.addTwenties(nTwenties);
				}
				catch (InputMismatchException ime) {
					System.out.println("Please input a positive integer.\n");
				}
				String extra = keyboard.nextLine(); // This variable keeps hold of the enter after the integer inputted by the user.
				System.out.println(atm);
			}
			else if (choice.equalsIgnoreCase("F")) {
				System.out.println("How many fives would you like to deposit?");
				// the try block is to check if the input is indeed an integer.
				try {
					int nFives = keyboard.nextInt();
					atm.addFives(nFives);
				}
				catch (InputMismatchException ime) {
					System.out.println("Please input a positive integer.\n");
				}
				String extra = keyboard.nextLine(); // This variable keeps hold of the enter after the integer inputted by the user.
				System.out.println(atm);
			}
			else if (choice.equalsIgnoreCase("W")) {
				System.out.println("How much money do you want to withdraw? Please enter a multiple of five.");
				// the try block is to check if the input is indeed an integer.
				try {
					int nWithdraw = keyboard.nextInt();
					atm.withdraw(nWithdraw);
				}
				catch (InputMismatchException ime) {
					System.out.println("Please input a positive integer.\n");
				}
				String extra = keyboard.nextLine(); // This variable keeps hold of the enter after the integer inputted by the user.
				System.out.println(atm);
			}
			else if (choice.equalsIgnoreCase("Q")) { // Break the loop, if the user picks "Q".
				break;
			}
			else {
				// If the user inputs something other than W, T, F, or Q, it is invalid.
				System.out.println("Invalid input.\n");
			}
		}

	}
}