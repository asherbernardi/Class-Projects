/**
 * Sort.java
 * 
 * This program exercises various sorting algorithms.
 * There are things in main() that 235 students do not
 * need to understand; it suffices to know that it
 *    1) creates a randomly filled array and prints it
 *    2) calls the sort() method
 *    3) prints the array again
 *
 * Note that this does include useful static methods
 *    int[] createRandomArray(int size)
 *    void printArray(String label, int[] a)
 *
 * @author Thomas VanDrunen
 * Wheaton College, CSCI 235, Fall 2016
 * Project 3
 * Sep. 30, 2016
 */

import java.lang.reflect.*;

public class Sort {

    public static void main(String[] args) {
        
        try {
            String sortName = args[0];               // the name of the sort class
            Class[] sortMethodFormals = new Class[1];     // just "int[]"
            sortMethodFormals[0] = (new int[0]).getClass();
	    // the sorting method
            Method sortMethod =  ClassLoader.getSystemClassLoader()  
                .loadClass(sortName).getMethod("sort", sortMethodFormals);
            int[] sortArray = createRandomArray(10);   // the array to sort
            
            printArray("Before sorting: ", sortArray);
            
            Object[] sortMethodActuals = new Object[1];  // just sortArray
            sortMethodActuals[0] = sortArray;
            
            sortMethod.invoke(null, sortMethodActuals);
            
            printArray("After sorting:", sortArray);
        }
        catch (ArrayIndexOutOfBoundsException aioobe) {
            System.out.println("Please give the name of a sorting"
			       +" class on the command line.");
        } 
        catch (ClassNotFoundException cnfe) {
            System.out.println("Class " + args[0] + " not found.");
        }
        catch (NoSuchMethodException nsme) {
            System.out.println("Class " + args[0] + 
                               " does not have a public"
			       + " method void sort(int[]).");
        }
        catch (IllegalAccessException iae) {
            System.out.println("Please make method "
			       +"void sort(int[]) in " + args[0] + " public.");
        }
        catch (InvocationTargetException ite) {
            Throwable innerException = ite.getCause();
            if (innerException == null)
                System.out.println("Please tell the instructor "
				   +"that you received this error message.");
            else {
                System.out.println(innerException);
                innerException.printStackTrace();
            }
        }
    }
        

    /**
     * Create an array filled with random values from 0 to 99.
     * Math.random() returns a random double in [0, 1). Multiply by 100
     * and cast to int to get an integer from 0 to 99.
     * @param  size The size of the array to create.
     * @return The randomly filled array.
     */
    static int[] createRandomArray(int size) {
        int[] toReturn = new int[size];
        for (int i = 0; i < toReturn.length; i++)
            toReturn[i] = (int) (Math.random() * 100);
        return toReturn;
    }

    /**
     * Print an array, with an initial label.
     * @param label A message to print before the array.
     * @parma a     The array to print.
     * POSTCONDITION: label and the array are printed on the display
     */
    static void printArray(String label, int[] a) {
	if (!label.isEmpty()) {
	    System.out.println(label);
	}
	for (int i = 0; i < a.length; i++) {
	    System.out.print(a[i] + "   ");
	}
	System.out.println("");
    }
}
