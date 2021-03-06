package q5string;

/**
 * TrieSet
 * 
 * Implementation of the Set ADT for strings using a trie.
 * 
 * @author Thomas VanDrunen
 * CSCI 345, Wheaton College
 * April 22, 2015, revise April 19, 2016
 */
public class TrieSet implements Set<String> {

    /**
     * Children nodes in this trie
     */
    private Set<String>[] children;

    /**
     * Is the string which would end at this node (not
     * descend into any child node) in this set?
     */
    private boolean terminal;

    /**
     * Constructor for a node initially empty
     * (which, apart from the root, would break the invariant)
     */
    @SuppressWarnings("unchecked")
	TrieSet() {
        children = (Set<String>[]) new Set[26];
        terminal = false;
    }

    /**
     * Constructor for node with given set of strings
     * @param items The strings initially put in the set, sorted
     * @param start The inclusive start of the range of the array for strings in the
     * subtrie rooted at the node being made
     * @param stop The exclusive end of the range of the array for strings in the
     * subtrie rooted at the node being made
     * @param c The number of levels the node bing made is away from the 
     * root of the entire trie, or the character position in the strings
     * that are relevant for this node. 
     */
    TrieSet(String[] items, int start, int stop, int c) {
    	 // keep this -- it initializes the children array
    	 // (note all positions are initially null)
        this();
        
        //begin solution, replace with: //add code here
        if (items[start].length() == c) {
            terminal = true;
            start++;
        }
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            int end = start;
            while (end < stop && items[end].charAt(c) == letter) end++;
            if (start != end)
                children[c2i(letter)] = new TrieSet(items, start, end, c+1);
            start = end;
        }
        //end solution
    }
    
    /**
     * Count the number of string (suffixes) in the
     * subtrie rooted at this node.
     * @return
     */
    public int size() {
        int count = terminal ? 1 : 0;
        for (Set<String> child : children)
            if (child != null) count += child.size();
        return count;
    }



    // --- Exception classes ---

    public static class BadCharException extends RuntimeException {
        public BadCharException(char c) {
            super("Bad character: " + c);
        }

        private static final long serialVersionUID = -3495608442105421490L;
    }

    public class BadModeException extends RuntimeException {
        private static final long serialVersionUID = -7783643567574205891L;

        public BadModeException(int mode) {
            super("Unknown Trie mode: " + mode);
        }

    }


    /**
     * Convert a character to an index, according to the mode.
     */
    private int c2i(char c) {
        if (c >= 'A' && c <= 'Z')
            return c - 'A';
        else
            throw new BadCharException(c);
    }

    /**
     * Add an item to this set.
     */
    public void add(String item) {
        if (item.length() == 0)
            terminal = true;
        else {
            int index = c2i(item.charAt(0));
            if (children[index] == null)
                children[index] = new TrieSet();
            children[index].add(item.substring(1));
        }
    }

    /**
     * Does this set contain the given item?
     */
    public boolean contains(String item) {
        if (item.length() == 0)
            return terminal;
        else {
            int index = c2i(item.charAt(0));
            if (children[index] == null) return false;
            else return children[index].contains(item.substring(1));
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }


}
