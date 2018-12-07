/**
 * Interpreter.java
 *
 * Class to generate ExprNode trees based on a given
 * input.
 *
 * @author ASher Bernardi
 * CSCI 235, Wheaton College, Fall 2016
 * Project 7
 * Date? 
 */

public class Interpreter {

    /**
     * Turn a string into an ExprNode tree.
     * If the nodes[] has a length of 1, then it is either a Number or a Variable node:
     *     It is a Variable node if the string is "x", and it is also a Variable
     *     node if the string is "y", otherwise it is a Number.
     *     The try-catch block is for the event that nodes[0] has a string that is
     *     neither "x" nor a number.
     * Otherwise, the nodes[] has a length of 3 and it is a Operation node:
     *     Parse each side of the node recursively.
     * @param input The string to parse
     * @return The root of the ExprNode tree
     */
    public static ExprNode parse(String input) {

        // Slice the input expression and put each element into an array.
        String[] nodes = ExprStringSlicer.slice(input);

        // The ExprNode to return.
        ExprNode out = null;

        if (nodes.length == 1) {
            if (nodes[0].equals("x"))
                out = new Variable(nodes[0]);
            else if (nodes[0].equals("y"))
                out = new Variable(nodes[0]);
            else if (nodes[0].equals("e"))
                out = new Exp();
            else
                out = new Number(Double.parseDouble(nodes[0]));
        }
        else if (nodes.length == 2) {
            out = new Trig(nodes[0], parse(nodes[1]));
        }
        else {
            out = new Operation(nodes[1], parse(nodes[0]), parse(nodes[2]));
        }

        return out;

    }

    public static void main(String[] args) {
        System.out.println(parse("sin(x)").evaluate(2,3));
    }

}
