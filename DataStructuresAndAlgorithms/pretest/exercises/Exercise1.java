package exercises;

import java.util.Arrays;

public class Exercise1 {

    /**
     * Find the position of an item in a given array, if it is anywhere.
     * PRECONDITION: The array is sorted and contains no null elements.
     * @param array An array sorted by its elements' natural ordering
     * (as expressed by compareTo()).
     * @param item The item for which to search.
     * @return A position in the array which contains the item, or -1 if
     * it does not occur in the array (including if the array is empty or null)
     */
	public static <T extends Comparable<T>> int binarySearch(T[] array, T item) {
         if (array == null) return -1;
         if (array.length == 0) return -1;
         return recBinarySearch(array, item, 0);
    }

	private static <T extends Comparable<T>> int recBinarySearch(T[] array, T item, int prevIndex) {
		int index = array.length/2;
		int comp = item.compareTo(array[index]);
		if (comp == 0)
			return index + prevIndex;
		if (comp < 0) {
			if (index == 0) return -1;
			return recBinarySearch(Arrays.copyOfRange(array, 0, index),item, prevIndex);
		}
		if (comp > 0) {
			if (index == 0) return -1;
			return recBinarySearch(Arrays.copyOfRange(array, index, array.length),item, index + prevIndex);
		}
		
		return -1;
	}
	
    
}
