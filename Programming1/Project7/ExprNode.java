/**
 * ExprNode.java
 *
 * An interface to define the operation evaluate() on
 * nodes in an expression tree.
 * 
 * @author Thomas VanDrunen
 * CSCI 235, Wheaton College, Fall 2016
 * Project 7
 * Nov. 30, 2016
 */
public interface ExprNode {

    double evaluate(double x, double y);

}