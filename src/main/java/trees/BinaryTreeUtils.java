package main.java.trees;

public class BinaryTreeUtils {
    /**
     * Compares two trees for being isomorphic means same structure. Time: O(n) Space: O(n)
     * @param treeOne root of tree one
     * @param treeTwo root of tree two
     * @return true if two trees are isomorphic
     */
    public static <T extends Comparable<T>> boolean isomorphicTrees(BinaryTreeNode<T> treeOne, BinaryTreeNode<T> treeTwo) {
        if(treeOne == null && treeTwo ==null)return true;     //Both empty?
        if(treeOne == null || treeTwo == null)return false;   //Only one is empty?

        //Both non-empty then compare them.
        return (
                isomorphicTrees (treeOne.getLeft(), treeTwo.getLeft()) &&
                        isomorphicTrees (treeOne.getRight(), treeTwo.getRight())
        );
    }

    /**
     * Compares two trees for being quasi- isomorphic means one can be transformed to other by swapping left and right children. Time: O(n) Space: O(n)
     * @param treeOne root of tree one
     * @param treeTwo root of tree two
     * @return true if two trees are isomorphic
     */
    public static <T extends Comparable<T>> boolean quasiIsomorphicTrees(BinaryTreeNode<T> treeOne, BinaryTreeNode<T> treeTwo) {
        if(treeOne == null && treeTwo ==null)return true;     //Both empty?
        if(treeOne == null || treeTwo == null)return false;   //Only one is empty?

        //Both non-empty then compare them.
        return (
                quasiIsomorphicTrees (treeOne.getLeft(), treeTwo.getLeft()) && quasiIsomorphicTrees (treeOne.getRight(), treeTwo.getRight()) ||
                        quasiIsomorphicTrees (treeOne.getLeft(), treeTwo.getRight()) && quasiIsomorphicTrees (treeOne.getRight(), treeTwo.getLeft())
        );

    }

    /**
     * Compares two trees for being same. Compares structure along with values. Time: O(n) Space: O(n)
     * @param treeOne
     * @param treeTwo
     * @return true if two trees are same
     */
    public static <T extends Comparable<T>> boolean identicalTrees(BinaryTreeNode<T> treeOne, BinaryTreeNode<T> treeTwo) {
        if(treeOne == null && treeTwo ==null)return true;     //Both empty?
        if(treeOne == null || treeTwo == null)return false;   //Only one is empty?

        //Both non-empty then compare them.
        return (
                treeOne.getData().equals(treeTwo.getData()) &&
                        identicalTrees (treeOne.getLeft(), treeTwo.getLeft()) &&
                        identicalTrees (treeOne.getRight(), treeTwo.getRight())
        );
    }

    /**
     * Compares two trees for being mirror of each other. Time: O(n) Space: O(n)
     * @param treeOne
     * @param treeTwo
     * @return true if two trees are mirrors of each other
     */
    public static <T extends Comparable<T>> boolean mirrorTrees(BinaryTreeNode<T> treeOne, BinaryTreeNode<T> treeTwo) {
        if(treeOne == null && treeTwo ==null)return true;     //Both empty?
        if(treeOne == null || treeTwo == null)return false;   //Only one is empty?
        if( !treeOne.getData().equals(treeTwo.getData()) ) return false;
        //Both non-empty then compare them.
        return (
                mirrorTrees (treeOne.getLeft(), treeTwo. getRight()) &&
                        mirrorTrees (treeOne.getRight(), treeTwo.getLeft())
        );
    }

    /**
     * Returns the lowest common ancestor for nodes nodeOne and nodeTwo
     * @param root root of the tree
     * @param nodeOne
     * @param nodeTwo
     * @param <T>
     * @return lca of two nodes
     */
    public static <T extends Comparable<T>> BinaryTreeNode<T> lowestCommonAncestor(BinaryTreeNode<T> root , BinaryTreeNode<T> nodeOne, BinaryTreeNode<T> nodeTwo) {
        if(root == null) return null;
        if(root.equals(nodeOne) || root.equals(nodeTwo)) {
            // At least one match? no need to continue as this is LCA
            return root;
        }
        BinaryTreeNode<T> left = lowestCommonAncestor(root.getLeft(), nodeOne, nodeTwo);
        BinaryTreeNode<T> right = lowestCommonAncestor(root.getRight(), nodeOne, nodeTwo);
        if (left != null && right != null) {
            return root;  // nodes are each on a separate branch
        }
        // either one node is on one branch or none found in any of branches
        return left != null ? left : right;

    }
}
