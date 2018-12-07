/**
 * PigLatin.java
 *
 * A program to transform a phrase into pig latin.
 *
 * @author: ASher Bernardi
 * Wheaton College, CSCI 235, Fall 2016
 * Project 1
 * September 9, 2016
 */

import java.util.Scanner;

public class PigLatin
{
	public static void main(String[] args)
	{

		// For inputting the phrase to be tested:
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Please enter a phrase to be translated into pig latin: ");
		String phrase = keyboard.nextLine();

		// Determine the length of the phrase:
		int length = phrase.length();

		// Initialize the new phrase as lowercase:
		String newPhrase = phrase.toLowerCase();

		// Translate the phrase into PigLatin:
		int pos = 0;
		char firstLetter = 'X';
		int indexOfFirstLetter = 0;
		while (pos < length)
		{
			if (Character.isLetter(newPhrase.charAt(pos)))
			{
				firstLetter = newPhrase.charAt(pos);
				indexOfFirstLetter = pos;
				while (Character.isLetter(newPhrase.charAt(pos)))
				{
					pos++;
					if (pos == length)
					{
						break;
					}
				}
				if (pos == length)
				{
					// for the last letter:
					newPhrase = newPhrase.substring(0, indexOfFirstLetter) + newPhrase.substring(indexOfFirstLetter + 1, pos) + firstLetter + "ay";
				}
				else
				{
					// for all other letters:
					newPhrase = newPhrase.substring(0, indexOfFirstLetter) + newPhrase.substring(indexOfFirstLetter + 1, pos) + firstLetter + "ay" + newPhrase.substring(pos, length);
				}
				pos += 2; // adjust for the addition of the "ay".
				length += 2; // adjust for the addition of the "ay".
			}
			pos += 1;
		}

		// Capitalize the first letter of the string:
		pos = 0;
		while (pos < length)
		{
			if (Character.isLetter(newPhrase.charAt(pos)))
			{
				newPhrase = newPhrase.substring(0, pos) + Character.toUpperCase(newPhrase.charAt(pos)) + newPhrase.substring(pos + 1, length);
				break;
			}
			pos++;
		}
		
		// Capitalize all letters after periods:
		pos = newPhrase.indexOf(".");
		while (pos < length && pos != -1)
		{
			if (Character.isLetter(newPhrase.charAt(pos)))
			{
				newPhrase = newPhrase.substring(0, pos) + Character.toUpperCase(newPhrase.charAt(pos)) + newPhrase.substring(pos + 1, length);
				pos = newPhrase.indexOf(".", pos + 1);
			}
			else
			{
				pos++;
			}
		}

		// Capitalize the first letter in a pair of double quotes:
		pos = newPhrase.indexOf("\"");
		int evenOdd = 0;
		while (pos < length && pos != -1)
		{
			if (evenOdd % 2 == 0)
			{
				if (Character.isLetter(newPhrase.charAt(pos)) && (newPhrase.indexOf("\"", pos + 1) != -1))
				{
					newPhrase = newPhrase.substring(0, pos) + Character.toUpperCase(newPhrase.charAt(pos)) + newPhrase.substring(pos + 1, length);
					pos = newPhrase.indexOf("\"", pos + 1);
					evenOdd++;
				}
				else
				{
					pos++;
				}
			}
			else
			{
				pos = newPhrase.indexOf("\"", pos + 1);
				evenOdd++;
			}
		}

		// Print result:
		System.out.println("Your phrase in pig latin is:");
		System.out.println(newPhrase);

	}
}