package main.java.ds.skipList;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Skiplist {

    private final List<SkipListNode> sentinels = new ArrayList<>();

    public Skiplist() {
        sentinels.add(new SkipListNode(Integer.MIN_VALUE));
    }

    public boolean search(int target) {
        SkipListNode smallerOrEquals = getSmallerOrEquals(target);
        return smallerOrEquals.val == target;
    }

    public void add(int num) {
        populateLevelUp(insertAndGet(num));
    }

    public boolean erase(int num) {
        if(!search(num)) return false;
        SkipListNode current = getSmallerOrEquals(num);
        while (current != null) {
            deleteCurrentNode(current.left, current.right);
            current = current.up;
        }
        return true;
    }

    private void deleteCurrentNode(SkipListNode left, SkipListNode right) {
        left.right = right;
        if(right == null) return;
        right.left = left;
    }

    private void populateLevelUp(final SkipListNode insertedNode) {
        SkipListNode prev = insertedNode.left, current = insertedNode;
        while (flipCoin()) {
            // Go as left as you can go else try to go up.
            while (prev.left != null && prev.up == null)
                prev = prev.left;
            prev = getAboveNode(prev);
            copyAndInsertAbove(current);
            current = current.up;
            insertNodeInBetween(prev, current, prev.right);
        }
    }
    private void copyAndInsertAbove(SkipListNode current) {
        SkipListNode newNode = new SkipListNode(current.val);
        current.up = newNode;
        newNode.down = current;
    }
    private void insertNodeInBetween(SkipListNode left, SkipListNode mid, SkipListNode right) {
        left.right = mid;
        mid.left = left;
        if(right != null)
        {
            mid.right = right;
            right.left = mid;
        }
    }
    private SkipListNode getAboveNode(SkipListNode node) {
        if (node.up == null) {
            SkipListNode newSentinel = new SkipListNode(Integer.MIN_VALUE);
            node.up = newSentinel;
            newSentinel.down = node;
            sentinels.add(node.up);
        }
        node = node.up;
        return node;
    }

    // Insert num and get the respective node.
    private SkipListNode insertAndGet(int num) {
        SkipListNode prev = getSmallerOrEquals(num);
        SkipListNode next = prev.right;
        SkipListNode newNode = new SkipListNode(num);
        insertNodeInBetween(prev, newNode, next);
        return newNode;
    }

    // Go as right as you can go else try to go down.
    private SkipListNode getSmallerOrEquals(int target) {
        SkipListNode cur = getSentinel();
        while (cur != null) {
            if(canGoRight(cur, target))
                cur = cur.right;
            else
            {
                if (cur.down == null) break;
                cur = cur.down;
            }
        }
        return cur;
    }
    private boolean canGoRight(SkipListNode current, int target) {
        return (current.right != null && current.right.val < target);
    }
    private final Random rand = new Random();
    private boolean flipCoin() {
        return rand.nextDouble() < 0.5;
    }
    private SkipListNode getSentinel() {
        return sentinels.get(sentinels.size() - 1);
    }
}

class SkipListNode {
    public int val;
    public SkipListNode left, right, up, down;

    public SkipListNode(int val) {
        this.val = val;
    }
}