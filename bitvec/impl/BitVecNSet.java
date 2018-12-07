package impl;

import java.util.Iterator;
import java.util.NoSuchElementException;

import adt.BadNSetParameterException;
import adt.NSet;

/**
 * BArrayNSet
 * 
 * Implementation of NSet that uses bit vectors
 * to represent the set.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * June 15, 2015
 */
public class BitVecNSet implements NSet {

    /**
     * The array of bytes, used as a bit vector.
     */
    private byte[] internal;

    /**
     * One greater than the largest number than can be stored
     * in this set.
     */
    private int range;

    /**
     * Plain constructor
     * @param range One greater than the largest number than 
     * can be stored in this set.
     */
    public BitVecNSet(int range) {
        this.range = range;
        internal = new byte[range / 8 + 1];
    }

    /**
     * Check to see if a value could possibly be in this set,
     * and throw an exception if it is out of range.
     * @param x The value in question, interpreted as an index
     * into the array.
     */
    private void checkIndex(int x) {
        if (x < 0 || x >= range)
            throw new BadNSetParameterException(x + "");
    }
    
    /**
     * Make sure the other NSet has the same class and range as
     * this one, throw an exception otherwise.
     * @param other The other NSet, to be checked.
     */
    private void checkParameter(NSet other) {
    if (! (other instanceof BitVecNSet) || other.range() != range)
        throw new BadNSetParameterException(this.getClass() + "," + range + " / " +
                other.getClass() + "," + other.range());
    }
    
    /**
     * Add an item to the set. (No problem if it's already there.)
     * @param item The item to add
     */
    public void add(Integer item) {
        checkIndex(item);
        internal[item / 8] |= 1 << (item % 8);
    }

    /**
     * Does this set contain the item?
     * @param item The item to check
     * @return True if the item is in the set, false otherwise
     */ 
    public boolean contains(Integer item) {
        checkIndex(item);
        return (internal[item / 8] & (1 << (item % 8))) != 0;
    }

    /**
     * Remove an item from the set, if it's there
     * (ignore otherwise).
     * @param item The item to remove
     */ 
    public void remove(Integer item) {
        checkIndex(item);
        internal[item / 8] &= ~(1 << (item % 8));
    }


    /**
     * Is the set empty?
     * @return True if the set is empty, false otherwise.
     */
    public boolean isEmpty() {
        boolean ret;
        for (int i = 0; i < internal.length; i++)
            if (internal[i] != 0)
                return false;
        return true;
    }


    /**
     * The range of this set, that is, one greater
     * than the largest number than can be stored
     * in this set.
     * @return n such that the elements of this set are
     * drawn from the range [0, n).
     */
    public int range() {
        return range;
    }

    /**
     * Compute the complement of of this set.
     * @return A set containing all the elements that
     * aren't in this one and none of the elements that
     * are.
     */
    public NSet complement() {
        BitVecNSet toReturn = new BitVecNSet(range);
        for (int i = 0; i < internal.length - 1; i++)
            // strangely, the negation of a byte is an integer...
            toReturn.internal[i] = (byte) ~(internal[i]);
        byte mask = 0;
        for (int i = 0; i < range % 8; i++) {
            mask <<= 1;
            mask |= 1;
        }
        toReturn.internal[internal.length - 1] = (byte)
                (mask ^ internal[internal.length - 1]);
        return toReturn;
    }

    /**
     * Compute the union of this and the given set.
     * @param other Another set of the same class and
     * range.
     * @return A set containing all the elements that are
     * in either this or the other set.
     */
    public NSet union(NSet other) {
        checkParameter(other);
        BitVecNSet newOther = (BitVecNSet) other;
        BitVecNSet ret = new BitVecNSet(range());
        for (int i = 0; i < internal.length; i++) {
            ret.internal[i] = (byte) (internal[i] | newOther.internal[i]);
        }
        return ret;
    }

    /**
     * Compute the intersection of this and the given set.
     * @param other Another set of the same class and 
     * range.
     * @return A set containing all the elements that are
     * in both this and the other set.
     */
    public NSet intersection(NSet other) {
        checkParameter(other);
        BitVecNSet newOther = (BitVecNSet) other;
        BitVecNSet ret = new BitVecNSet(range());
        for (int i = 0; i < internal.length; i++) {
            ret.internal[i] = (byte) (internal[i] & newOther.internal[i]);
        }
        return ret;
    }

    /**
     * Compute the difference between this and the given
     * set. 
     * @param other Another set of the same class and 
     * range.
     * @return A set containing all the elements that
     * are in this set but not in the other set.
     */
    public NSet difference(NSet other) {
        checkParameter(other);
        BitVecNSet newOther = (BitVecNSet) other;
        BitVecNSet ret = new BitVecNSet(range());
        for (int i = 0; i < internal.length; i++) {
            ret.internal[i] = (byte) (internal[i] & ~newOther.internal[i]);
        }
        return ret;
    }

    /**
     * The number of items in the set
     * @return The number of items.
     */
    public int size() {
        int size = 0;
        for (int i = 0; i < internal.length; i++) {
            if (internal[i] == 0) continue;
            byte piece = internal[i];
            for (int j = 0; j < 8; j++) {
                size += piece & 1;
                piece >>= 1;
            }
        }
        return size;
    }

    private byte byteLogRightShift(byte b) {
        return (byte) ((b >> 1) & 127);
    }

    /**
     * Iterate through this set.
     */
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            int arrayPos = 0;
            byte bytePos = 0;
            byte piece = internal[0];
            @Override
            public boolean hasNext() {
                // for current byte
                if (piece != 0)
                    return true;
                // for subsequent bytes
                for (int i = arrayPos + 1; i < internal.length; i++)
                    if (internal[i] != 0)
                        return true;

                return false;
            }

            @Override
            public Integer next() {
                if (hasNext()) {
                    for (; arrayPos < internal.length; arrayPos++, bytePos = 0) {
                        for (; bytePos < 8; bytePos++, piece = byteLogRightShift(piece)) {
                            if ((piece & 1) == 1) {
                                piece = byteLogRightShift(piece);
                                return arrayPos * 8 + (bytePos++);
                            }
                        }
                        if (arrayPos < internal.length - 1)
                            piece = internal[arrayPos + 1];
                    }
                }
                throw new NoSuchElementException();
            }
        };
    }

    public String toString() {
        String toReturn = "[";
        for (int i = 0; i < internal.length - 1; i++)
            for (int j = 0; j < 8; j++)
                if ((internal[i] & (1 << j)) == 0)  toReturn += " ";
                else toReturn += ".";
        for (int j = 0; j < range % 8; j++)
            if ((internal[internal.length - 1] & (1 << j)) == 0)  toReturn += " ";
            else toReturn += ".";
        for (int j = range % 8; j < 8; j++)
            toReturn += "x";
        toReturn += "]";
        return toReturn;
    }

}
