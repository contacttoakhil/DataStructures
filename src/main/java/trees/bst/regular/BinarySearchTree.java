package main.java.trees.bst.regular;

import main.java.trees.bst.INodeCreator;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.Objects;

public class BinarySearchTree<T extends Comparable<T>> implements IBinarySearchTree<T>{
    private BinarySearchTreeNode<T> root;
    protected INodeCreator<T> creator;

    public BinarySearchTree() {
        this.creator = null;
    }

    public BinarySearchTree(INodeCreator<T> creator) {
        this.creator = creator;
    }

    @Override
    public BinarySearchTreeNode<T> getRootOfTree() {
        return this.root;
    }

    @Override
    public void setRootOfTree(BinarySearchTreeNode<T> root) {
        this.root = root;
    }

    @Override
    public void insert(T value) {
        Objects.requireNonNull(value);
        setRootOfTree(insert(getRootOfTree(),value));
    }

    @Override
    public void delete(T value) {
        Objects.requireNonNull(value);
        setRootOfTree(delete(getRootOfTree(),value));
    }

    public void printTree() {
        System.out.println(printTree(this));
    }

    /**
     * insert the value in the tree rooted at node.
     * @param node root of the tree
     * @param value to insert in tree rooted at  node.
     * @return root node of the tree post insertion of value.
     */
    protected BinarySearchTreeNode<T> insert(BinarySearchTreeNode<T> node, T value) {
        if(node == null) return getNewNode(value);
        int cmp = value.compareTo(node.getData());
        if      (cmp < 0) node.setLeft(insert((BinarySearchTreeNode<T>) node.getLeft(),  value));
        else if (cmp > 0) node.setRight(insert((BinarySearchTreeNode<T>) node.getRight(), value));
        else node.setData(value);
        updateSize(node);
        return node;
    }

    /**
     * removes the specified value from the tree rooted at node. It handles all three cases:
     *
     * Case 1: Node with zero child (leaf)
     *
     *
     *                    60                                                                    60
     *                  /   \                                                                  /  \
     *                20    80      - deleting 90 is straight forward as its leaf ->         20   80
     *               /  \    \                                                              /  \
     *             10   50   90                                                           10   50
     *                 /                                                                      /
     *               30                                                                      30
     *
     * Case 2: Node with one child
     *                    60                                                                    60
     *                  /   \                                                                  /  \
     *                20    80      - deleting 50  needs adjusting parent ->                  20   80
     *               /  \    \                                                              /  \
     *             10   50   90                                                           10   30
     *                 /                                                                        \
     *               30                                                                         40
     *                \
     *                40
     *
     * Case 3: Node with two child (20 say)
     * When a node has two child then it needs to find a replacement node which can be smallest node in right subtree (inorder successor) or largest node in left subtree (inorder predecessor).
     * Once the replacement node is selected (we used inorder successor i.e. smallest node in right subtree of node 20 which is 30) we need to do two steps:
     * 1) We need to replace this node (20) with the replacement node (30).
     *              60
     *            /   \
     *          30    80
     *         /  \    \
     *        10  50   90
     *            /
     *           30
     *            \
     *            40
     * 2) Now as we can see the node 30 has got duplicate and needs to be deleted from right subtree for node 20. And deleting node 30 will lead to the following tree:
     *
     *              60
     *            /   \
     *          30    80
     *         /  \    \
     *        10  50   90
     *            /
     *           40
     *
     * Now we can optimize these two steps. The method getSmallestNode will find the replacement node and the method deleteSmallest will delete the duplicate (30) and provide us the root of that subtree (50).
     *
     * @param node the root of tree which needs deletion of node having value value.
     * @param value to be deleted
     * @return the root of updated subtree post deletion of node (value) from it. If node deleted is a leaf then method may return empty subtree (null).
     */
    protected BinarySearchTreeNode<T> delete(BinarySearchTreeNode<T> node, T value) {
        Objects.requireNonNull(node);
        int cmp = value.compareTo(node.getData());
        if      (cmp < 0) node.setLeft(delete((BinarySearchTreeNode<T>) node.getLeft(),  value));
        else if (cmp > 0) node.setRight(delete((BinarySearchTreeNode<T>) node.getRight(), value));
        else {
            if (node.getRight() == null) return (BinarySearchTreeNode<T>) node.getLeft();
            else if (node.getLeft()  == null) return (BinarySearchTreeNode<T>) node.getRight();
            else {
                BinarySearchTreeNode<T> t = node;
                node = getSmallestNode((BinarySearchTreeNode<T>) t.getRight());
                node.setRight(deleteSmallest((BinarySearchTreeNode<T>) t.getRight()));
                node.setLeft(t.getLeft());
            }
        }
        updateSize(node);
        return node;
    }

    protected BinarySearchTreeNode<T> getNewNode(T value) {
        return (creator != null) ? (creator.createNewNode(value)) : (new BinarySearchTreeNode<>(value));
    }

    protected void updateSize(BinarySearchTreeNode<T> node) {
        node.setSize(size(node.getLeft()) + size(node.getRight()) + 1);
    }
    @Override
    public Iterator<T> iterator() {
        return new BinarySearchTreeIterator<>(this);
    }

    class BinarySearchTreeIterator<T extends Comparable<T>> implements Iterator<T> {

        private BinarySearchTree<T> tree = null;
        private BinarySearchTreeNode<T> last = null;
        private Deque<BinarySearchTreeNode<T>> toVisit = new ArrayDeque <>();

        BinarySearchTreeIterator(BinarySearchTree<T> tree) {
            this.tree = tree;
            if (getRootOfTree()!=null) toVisit.add((BinarySearchTreeNode<T>) getRootOfTree());
        }

        @Override
        public boolean hasNext() {
            return toVisit.size() > 0;
        }

        @Override
        public T next() {
            while (true) {
                if (!(hasNext())) break;
                // Go thru the current nodes
                BinarySearchTreeNode<T> n = toVisit.pop();

                // Add non-null children
                if (n.getLeft() != null) toVisit.add((BinarySearchTreeNode<T>) n.getLeft());
                if (n.getRight() != null) toVisit.add((BinarySearchTreeNode<T>) n.getRight());

                // Update last node (used in remove method)
                last = n;
                return n.getData();
            }
            return null;
        }

        @Override
        public void remove() {
            tree.delete(last.getData());
        }
    }
}