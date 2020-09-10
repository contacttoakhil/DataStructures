package main.java.problems.trees;

import main.java.problems.trees.domain.TreeNode;

/***
 * Given a binary tree, you need to find the length of Longest Consecutive Path in Binary Tree.
 *
 * Especially, this path can be either increasing or decreasing. For example, [1,2,3,4] and [4,3,2,1] are both considered valid, but the path [1,2,4,3] is not valid. On the other hand, the path can be in the child-Parent-child order, where not necessarily be parent-child order.
 *
 * Example 1:
 *
 * Input:
 *         1
 *        / \
 *       2   3
 * Output: 2
 * Explanation: The longest consecutive path is [1, 2] or [2, 1].
 *
 *
 * Example 2:
 *
 * Input:
 *         2
 *        / \
 *       1   3
 * Output: 3
 * Explanation: The longest consecutive path is [1, 2, 3] or [3, 2, 1].
 *
 *
 * Note: All the values of tree nodes are in the range of [-1e7, 1e7].
 *
 *
 *
 *
 * Ref: https://github.com/YaokaiYang-assaultmaster/LeetCode/blob/master/LeetcodeAlgorithmQuestions/549.%20Binary%20Tree%20Longest%20Consecutive%20Sequence%20II.md
 */
public class LC549BinaryTreeLongestConsecutiveSeqII {
    int maxval = 0;
    public int longestConsecutive(TreeNode root) {
        getLongestConsecutive(root);
        return maxval;
    }
    public int[] getLongestConsecutive(TreeNode root) {
        if (root == null)
            return new int[] {0,0};
        int inr = 1, dcr = 1;
        if (root.left != null) {
            int[] l = getLongestConsecutive(root.left);
            if (root.data == root.left.data + 1)
                dcr = l[1] + 1;
            else if (root.data == root.left.data - 1)
                inr = l[0] + 1;
        }
        if (root.right != null) {
            int[] r = getLongestConsecutive(root.right);
            if (root.data == root.right.data + 1)
                dcr = Math.max(dcr, r[1] + 1);
            else if (root.data == root.right.data - 1)
                inr = Math.max(inr, r[0] + 1);
        }
        maxval = Math.max(maxval, dcr + inr - 1);
        return new int[] {inr, dcr};
    }


    int max = 0;
    public int longestConsecutive2(TreeNode root) {
        helper(root);
        return max;
    }

    /**
     * Recursively consider longest increasing and decreasing sequence,
     * @param root
     * @return
     */
    public int[] helper(TreeNode root){
        if(root == null) return new int[]{0,0};
        int[] left = helper(root.left);
        int[] right= helper(root.right);
        int inc = 1, des = 1;
        if(root.left != null){
            if(root.data - root.left.data == 1){
                des = left[1]+1;
            }else if(root.data - root.left.data == -1){
                inc = left[0]+1;
            }
        }
        if(root.right != null){
            if(root.data - root.right.data == 1){
                des = Math.max(des,right[1]+1);
            }else if(root.data - root.right.data == -1){
                inc = Math.max(inc,right[0]+1);
            }
        }
        max = Math.max(max,inc+des-1);
        return new int[]{inc,des};
    }

    public static void main(String[] args) {
        LC549BinaryTreeLongestConsecutiveSeqII binaryTreeLongestConsecutiveSeqII = new LC549BinaryTreeLongestConsecutiveSeqII();

    }
}
