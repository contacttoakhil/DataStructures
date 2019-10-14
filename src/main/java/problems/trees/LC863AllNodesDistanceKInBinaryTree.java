package main.java.problems.trees;

import main.java.problems.trees.domain.TreeNode;

import java.util.*;

public class LC863AllNodesDistanceKInBinaryTree {

    Map<TreeNode, TreeNode> parent = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {

        dfs(root, null);

        Queue<TreeNode> queue = new LinkedList();   // Will use BFS or level order traversal.
        queue.add(null);                            // level zero would have nothing aka null.
        queue.add(target);

        Set<TreeNode> seen = new HashSet();         // will traverse left, right and up for target node. Need to ensure we keep track of visited nodes to avoid infinite loop.
        seen.add(target);
        seen.add(null);

        int dist = 0;                               // starting level is zero.
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                if (dist == K) {
                    List<Integer> ans = new ArrayList();
                    for (TreeNode n: queue)
                        ans.add(n.data);
                    return ans;
                }
                queue.offer(null);
                dist++;
            }
            else                                        // BFS for left, right and up (parent), add to queue and set.
            {
                if (!seen.contains(node.left)) {
                    seen.add(node.left);
                    queue.offer(node.left);
                }
                if (!seen.contains(node.right)) {
                    seen.add(node.right);
                    queue.offer(node.right);
                }
                TreeNode par = parent.get(node);
                if (!seen.contains(par)) {
                    seen.add(par);
                    queue.offer(par);
                }
            }
        }

        return new ArrayList<Integer>();
    }

    /**
     * This method uses backtrack to populate the parent map which contains the parent for all the nodes in the tree.
     * @param node
     * @param par
     */
    public void dfs(TreeNode node, TreeNode par) {
        if (node != null) {
            parent.put(node, par);
            dfs(node.left, node);
            dfs(node.right, node);
        }
    }
}
