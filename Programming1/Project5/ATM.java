/**
 * ATM.java
 *
 * A class to define the ATM object.
 *
 * @author ASher Bernardi
 * Wheaton College, CSCI 235, Fall 2016
 * Project 5
 * November 6
 */

public class ATM {

	private int twenties;
	private int fives;

	/**
	 * Constructor
	 */
	public ATM() {
		twenties = 0;
		fives = 0;
	}

	/**
	 * Add however many twenties are given.
	 * @param number   the number of twenties to add.
	 */
	public void addTwenties(int number) {
		// if the number is less than zero, show an error.
		if (number < 0) System.out.println("Cannot deposit negative money.\n");
		// Every iteration, add 1 to the number of twenties until you reach number.
		for (int i = 0; i < number; i++) {
			twenties += 1;
		}
	}

	/**
	 * Add however many fives are given.
	 * @param number   the number of fives to add.
	 */
	public void addFives(int number) {
		// if the number is less than zero, show an error.
		if (number < 0) System.out.println("Cannot deposit negative money.\n");
		// Every iteration, add 1 to the number of fives until you reach number.
		for (int i = 0; i < number; i++){
			fives += 1;
		}
	}

	/**
	 * Withdraw a multiple of five from the ATM.
	 * If the amount is not a multiple of five, show an error.
	 * If the amount is less than zero, show an error.
	 * If the amount cannot be withdrawn, show an error.
	 * Confirm before withdrawing.
	 * @param amount   amont to withdraw.
	 */
	public void withdraw(int amount) {
		// if the amount is less than zero, show an error.
		if (amount < 0) System.out.println("Cannot withdraw negative money.\n");
		// if the amount is not a multiple of five, show an error.
		else if (amount % 5 != 0) System.out.println("Please withdraw a multiple of 5.\n");
		// otherwise, check if the amount can be withdrawn in fives and twenties.
		else {
			// Split the amount into twenties and fives.
			int tNeeded = amount / 20;
			int fNeeded = (amount % 20) / 5;
			// If there aren't enough twenties in the ATM, use fives instead.
			if (tNeeded > twenties) {
				fNeeded += (tNeeded - twenties) * 4;
				tNeeded = twenties;
			}
			// If there aren't enough fives in the ATM, then the amount cannot be withdrawn.
			if (fNeeded > fives) System.out.println("You do not have enough fives to withdraw $" + amount + ".");

			// If you have enough fives, withdraw the amount.
			else {
				twenties -= tNeeded;
				fives -= fNeeded;

				// Then, display the number of fives and twenties withdrawn.
				System.out.print("You withdrew ");
				// If you only withdrew one twenty, print "twenty" instead of "twenties".
				switch (tNeeded) {
					case 1: System.out.print(tNeeded + " twenty and ");
						break;
					default: System.out.print(tNeeded + " twenties and ");
						break;
				}
				// If you only withdrew one five, print "five" instead of "fives".
				switch (fNeeded) {
					case 1: System.out.print(fNeeded + " five.");
						break;
					default: System.out.print(fNeeded + " fives.");
						break;
				}
				System.out.println("\n");
			}
		}
	}

	/**
	 * Print the contents of the ATM in an organized format.
	 */
	public String toString() {
		return "The ATM now has: $" + (20*twenties + 5*fives) +
			"\n     " + twenties + " twenty-dollar bills." +
			"\n     " + fives + " five-dollar bills." +
			"\n";
	}
}