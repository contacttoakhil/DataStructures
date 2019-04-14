package main.java.problems.trees;

import main.java.problems.trees.domain.TreeNode;

/***
 *
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 *
 * Note:
 * You may assume that duplicates do not exist in the tree.
 *
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 */
public class LC105ConstructBinaryTreeFromPreAndInOrder {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0)
            return null;

        int len = preorder.length;

        return buildTree(preorder, 0, len - 1, inorder, 0, len - 1);
    }

    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }

        int rootVal = preorder[preStart];
        int rootIdx = 0;

        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i] == rootVal) {
                rootIdx = i;
                break;
            }
        }
        int len = rootIdx - inStart;
        TreeNode root = new TreeNode(rootVal);

        // recursively call its left and right subtree
        root.left = buildTree(preorder, preStart + 1, preStart + len, inorder, inStart, rootIdx - 1);
        root.right = buildTree(preorder, preStart + len + 1, preEnd, inorder, rootIdx + 1, inEnd);

        return root;
    }

}
