package main.java.problems.trees;

import jdk.nashorn.api.tree.Tree;
import main.java.ds.queue.ArrayQueue;
import main.java.ds.queue.IQueue;
import main.java.ds.stack.ArrayStack;
import main.java.ds.stack.IStack;
import main.java.problems.trees.domain.BinaryTree;
import main.java.problems.trees.domain.TreeNode;

public class ReverseLevelOrderTraversal {
    public static void main(String[] args) {
        ReverseLevelOrderTraversal rlot = new ReverseLevelOrderTraversal();
        TreeNode root = getBinaryTree();
        rlot.levelOrder(root);
        System.out.println();
        rlot.reverseLevelOrder(root);
    }
    public void levelOrder(TreeNode node) {
        IQueue<TreeNode> queue = new ArrayQueue<>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            System.out.print(temp.data);
            if(temp.left != null)
                queue.offer(temp.left);
            if(temp.right != null)
                queue.offer(temp.right);
        }
    }
    public void reverseLevelOrder(TreeNode node) {
        IQueue<TreeNode> queue = new ArrayQueue<>();
        IStack<TreeNode> stack = new ArrayStack<>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            stack.push(temp);
            if(temp.right != null)
                queue.offer(temp.right);
            if(temp.left != null)
                queue.offer(temp.left);
        }
        while (!stack.isEmpty()) {
            System.out.print(stack.pop().data);
        }
    }
    private static TreeNode getBinaryTree() {
        BinaryTree tree = new BinaryTree();
        tree.root = new TreeNode(1);
        tree.root.left = new TreeNode(2);
        tree.root.right = new TreeNode(3);
        tree.root.left.left = new TreeNode(4);
        tree.root.left.right = new TreeNode(5);
        return tree.root;
    }
    static int height(TreeNode node) {
        if(node == null) return 0;
        int lh = height(node.left);
        int rh = height(node.right);
        return 1 + Math.max(lh, rh);
    }
}
