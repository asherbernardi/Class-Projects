package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.List;

/**
 * ArrayList
 * 
 * An array implementation of the List interface; a 
 * homemade, simplified version of Java's ArrayList.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * July 9, 2014
 * @param <E> The base-type of the list
 */


public class ArrayList<E> implements List<E> {

    /**
     * The internal array representation.
     */
    private E[] internal;
    
    /**
     * The portion of the array that is in use.
     */
    private int size;
    
    /**
     * Double the size of the internal array. Allocate
     * a larger array, copy the contents (and, implicitly,
     * deallocate the old array).
     */
    private void grow() {
        @SuppressWarnings("unchecked")
        E[] temp = (E[]) new Object[internal.length * 2];
        for (int i = 0; i < internal.length; i++)
            temp[i] = internal[i];
        internal = temp;
    }
    
    @SuppressWarnings("unchecked")
    public ArrayList() {
        internal = (E[]) new Object[100];
        size = 0;
    }
    
    /**
     * Append the specified element to the end of this list.
     * This increases the size by one.
     * @param element The element to be appended
     */
    public void add(E element) {
        if (size >= internal.length)
            grow();
        internal[size++] = element;
    }

    /**
     * Replace the element at the specified position in this list
     * with the specified element. If the index is invalid, an 
     * IndexOutOfBoundsException is thrown.
     * @param index The index of the element to return
     * @param element The element at the specified position
     */
    public void set(int index, E element) {
        if (index >= 0 && index < size)
            internal[index] = element;
        else
            throw new IndexOutOfBoundsException("" + index);
    }
    
    /**
     * Retrieve the element at the specified position in this list.
     * If the index is invalid, an IndexOutOfBoundsException is thrown.
     * @param index The index of the element to return
     * @return The element at the specified position
     */
    public E get(int index) {
        if (index >= 0 && index < size)
            return internal[index];
        else
            throw new IndexOutOfBoundsException("" + index);
    }

    /**
     * Remove (and return) the element at the specified position.
     * This reduces the size of the list by one and, if necessary,
     * shifts other elements over. If the index is invalid, an 
     * IndexOutOfBoundsException is thrown.
     * @param index The index of the element to remove
     * @return The item removed
     */
    public E remove(int index) {
        if (index >= 0 && index < size) {
            E removed = internal[index];
            size--;
            for (int i = index; i < size; i++)
                internal[i] = internal[i+1];
            return removed;
        }
        else
            throw new IndexOutOfBoundsException("" + index);

    }

    /**
     * Return the number of elements in this list.
     * @return The number of elements in this list.
     */
    public int size() {
        return size;
    }

    /**
     * Insert a new item at the specified position, shifting the
     * item already at the position and everything after it over
     * one position. If the index is equal to the length of the list,
     * then this is equivalent to the add method. If the index is 
     * negative or is greater than the length, an IndexOutOfBoundsException 
     * is thrown.
     * @param index The index into which to insert the element
     * @param element The element which to insert
     */
    public void insert(int index, E element) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("" + index);
        if (size == internal.length)
            grow();
        for (int i = size; i > index; i--)
            internal[i] = internal[i-1];
        internal[index] = element;
        size++;
    }

    
    /**
     * Return an iterator over this collection (remove() is
     * unsupported, nor is concurrent modification checked).
     */
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            int i = 0;
            
            public boolean hasNext() {
                return i < size;
            }

            public E next() {
                if (i < size)
                    return internal[i++];
                else
                    throw new NoSuchElementException();
            }
            
        };
    }
    
    @Override
    public String toString() {
    	String toReturn = "[";
    	boolean prefix = false;
    	for (E item : this) {
    		if (prefix)
    			toReturn += ", ";
    		toReturn += item;
    		prefix = true;
    	}
    	return toReturn +"]";
    }
    



}
