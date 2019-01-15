/**
 * INode.java
 *
 * Nodes for linked lists. (iterative version)
 *
 * @author TVD/CGG
 * in-class example
 * Wheaton College, CSCI 235, Fall 2016
 * Oct. 26, 2016
 */

public class INode {

    /**
     * The payload for this node.  (Note that it is immutable.)
     */
    private final int datum;
    /**
     * The node following this one.
     */
    private INode next;

    /**
     * Constructor.
     * @param datum The payload for this INode.
     * @param next  The node that follows this one.
     */
    public INode(int datum, INode next) {
        this.datum = datum;
        this.next = next;
    }

    /* accessor methods */
    public int datum() { return datum; }
    public INode next() { return next; }
    
    /* mutator method */
    public void setNext(INode next) { this.next = next; }
}

