package c15p4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * NeatPrint.java
 * 
 * Class to hold method for printing a long text neatly with a given
 * line length.
 * 
 * @author Thomas VanDrunen
 * CSCI 445
 * Sept 20, 2018
 */
public class NeatPrint {
    /**
     * Produce a version of a given string with new lines
     * placed so that no line is longer than the given 
     * line length and so that the penalty if minimized, 
     * where the penalty is the sum of the cubes of the
     * difference between the line length and each line
     * except the last.
     * @param text The given text
     * @param m The length of the line
     * @return A string with the given text in it plus
     * new lines
     */
    public static String neatPrint(String text, int m) {
        String[] words = text.split("\\s");
        int N = text.length();
        int n = words.length;
        // the cubes of the spaces after each line, such that
        // C[i] is the minimal cost of neatly printing words
        // i through n-1;
        int[] C = new int[n];
        // the places where the line breaks will be put.
        // -1 will be used to indicate that we do not need
        // a break since the words are shorter than the max line.
        int[] R = new int[n];
        // First find the costs and positions for the breaks.
        for (int i = n - 1; i >= 0; i--) {
        	int sum = 0;
        	for (int k = i; k < n; k++)
        		sum += words[k].length();
        	if (n - i - 1 + sum <= m) {
        		C[i] = 0;
        		R[i] = -1;
        	}
        	else {
        		int min = Integer.MAX_VALUE;
        		int minPos = 0;
        		int r;
        		for (r = i + 1; r < n; r++) {
        			sum = 0;
                	for (int k = i; k < r; k++)
                		sum += words[k].length();
        			int localCost = m - r + i + 1 - sum;
        			// once you've chosen an r that results in
        			// a line bigger than M, no r larger can be
        			// acceptable
        			if (localCost < 0) break;
        			if (cb(localCost) + C[r] < min) {
        				min = cb(localCost) + C[r];
        				minPos = r;
        			}
        		}
        		C[i] = min;
        		R[i] = minPos;
        	}
        }
        // construct the answer
        StringBuilder answer = new StringBuilder(text);
        int[] breakPos = new int[n];
        breakPos[0] = -1;
        System.out.println(m + ": " + C[0]);
        for (int i = 0, b = 1; i < n && R[i] != -1; b++) {
        	int sum = -1;
        	for (int j = i; j < R[i]; j++)
        		// the +1 is to count all the spaces
        		sum += words[j].length() + 1;
        	// make sure that you actually found a space.
        	assert(sum + breakPos[b-1] + 1 >= N || text.charAt(breakPos[b-1] + sum + 1) == ' ');
        	if (sum + breakPos[b-1] + 1 < N)
        		answer.setCharAt(sum + breakPos[b-1] + 1, '\n');
        	breakPos[b] = sum + breakPos[b-1] + 1;
        	i = R[i];
        }
        return answer.toString();
    }

    private static int cb(int i) {
        return i * i * i;
    }

    /**
     * Usage: java c15p4.NeatPrint (filename) (line length)
     * @param args
     */
    public static void main(String[] args) {
        try {
            int lineLength = Integer.parseInt(args[1]);
            Scanner file = new Scanner(new File(args[0]));
            String text = "";
            while (file.hasNext())
                text += file.nextLine();
            System.out.println(neatPrint(text, lineLength));
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}
