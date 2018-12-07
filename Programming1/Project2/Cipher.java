/**
 * Cipher.java
 * 
 * Program to decipher messages encoded using a Caesar cipher.
 * 
 * @author TVD, CGG, and ASher Bernardi
 * CSCI 235, Wheaton College, Fall 2016
 * Project 2
 * Sep. 25, 2016
 */

import java.util.Scanner;
import java.io.*;

public class Cipher {

    public static void main(String[] args) {

        // encrypted text
        String ciphertext;  

        // input from keyboard
        Scanner keyboard = new Scanner(System.in);

        // -----------------------------------------------------------------
        // this section contains stuff we haven't covered yet. 
        // -----------------------------------------------------------------
        if (args.length > 0) {
            ciphertext = "";
            try {
                Scanner inputFile = new Scanner(new File(args[0]));
                while(inputFile.hasNext()) 
                    ciphertext += inputFile.nextLine();
            } catch(IOException ioe) {
                System.out.println("File not found: " + args[0]);
                System.exit(-1);
            }
        }
        else {
            System.out.print("Please enter text--> ");
            ciphertext = keyboard.nextLine(); 
        }
        // -----------------------------------------------------------------
        


		int distance = 0;  // how far the ciphertext should be shifted
		String next = "";  // user input after viewing
		/*
		 * Depending on how you solve the problem, you might need
	     * to declare plaintext here instead of inside the while loop.
		 */
		String plaintext = ciphertext;
		// String plaintext = something; // the (possibly) decrypted message
	        while(!next.equals("quit")) {
		    	distance += 1;
		    	// reset distance every time it cycles through every letter.
		    	if (distance >= 26) distance -= 26;

				// Your code to shift the ciphertext goes here;
				// make plaintext ciphertext shifted by distance.
				// Do not change ciphertext

				// For loop to go through each character in plaintext.
				for (int i = 0; i < ciphertext.length(); i++) {
					// Check if the character is a letter.
					if (Character.isLetter(plaintext.charAt(i))) {
						switch ((int)(plaintext.charAt(i))) {
							// if the letter is Z, replace with A.
							case 90:
								plaintext = plaintext.substring(0, i) + (char)65 + plaintext.substring(i+1, plaintext.length());
								break;
							// if the letter is z, replace with a.
							case 122:
								plaintext = plaintext.substring(0, i) + (char)97 + plaintext.substring(i+1, plaintext.length());
								break;
							// if the letter is neither, replace with the letter after it.
							default:
								plaintext = plaintext.substring(0, i) + (char)((int)(plaintext.charAt(i)) + 1) + plaintext.substring(i+1, plaintext.length());
								break;
						}
					}
				}


		    	// At this point, plaintext is the shifted ciphertext.
		    	// Nothing below this should need to change.
		    	System.out.println("distance "+distance);
	            System.out.println(plaintext);
	            System.out.println("Press enter to see the next option, "
				       +"or type 'quit' to quit.");
	            next = keyboard.nextLine().trim();
	        }
		System.out.println("Final shift distance was "+distance+" places");
    }

}
