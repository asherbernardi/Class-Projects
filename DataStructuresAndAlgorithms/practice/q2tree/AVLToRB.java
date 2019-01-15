package q2tree;

/**
 * AVLToRB
 * 
 * Placeholder class for the static method avl2rb() which converts
 * an AVL tree to a red-black tree.
 * 
 * CSCI 345
 * Test 2 Practice Problem 1.
 */
public class AVLToRB {

    /**
     * Convert an AVL tree represented by its root to an equivalent
     * red-black tree, also represented by its root. If the given
     * tree is null, the result tree also should be null.
     * Assume that the height and balance as stored in each AVL node
     * is correct for that node.
     * @param root The root of the AVL (sub-)tree
     * @return The root of an equivalent red-black tree.
     */
    public static RBNode avl2rb(AVLNode root) {
        if (root == null) return null;
        else {
            RBNode newRoot;
            RBNode left;
            RBNode right;
            if (root.left == null) {
                if (root.right == null) {
                    return new RBNode(null, root.key, null);
                }
                else {
                    right = avl2rb(root.right);
                    right.redden();
                    return new RBNode(null, root.key, right);
                }
            }
            else if (root.right == null) {
                if (root.left == null) {
                    return new RBNode(null, root.key, null);
                }
                else {
                    left = avl2rb(root.left);
                    left.redden();
                    return new RBNode(left, root.key, null);
                }
            }
            else if (root.left.height == root.right.height) {
                return new RBNode(avl2rb(root.left), root.key, avl2rb(root.right));
            }
            else if (root.left.height > root.right.height) {
                left = avl2rb(root.left);
                left.redden();
                return new RBNode(left, root.key, null);
            }
            else {
                right = avl2rb(root.right);
                right.redden();
                return new RBNode(null, root.key, right);
            }
        }
    }
    
}
