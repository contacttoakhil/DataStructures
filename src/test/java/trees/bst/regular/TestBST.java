package test.java.trees.bst.regular;

import main.java.ds.trees.bst.regular.BinarySearchTree;
import org.junit.Test;
import test.java.DataGenerator;

import static org.junit.Assert.assertTrue;

public class TestBST {
    @Test
    public void testBST()
    {
        Integer[] sampleData = DataGenerator.generateIntegersWithoutDuplicates(50, 1000);
        testBST(new BinarySearchTree<>(),sampleData);
    }

    @Test
    public void testBSTBasic() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.insert(50);bst.insert(30);bst.insert(70);bst.insert(20);bst.insert(40);bst.insert(60);bst.insert(80);bst.insert(10);bst.insert(25);bst.insert(35);bst.insert(45);bst.insert(55);bst.insert(65);bst.insert(75);bst.insert(90);
        bst.printTree();
        bst.print();
        System.out.println("Tree Details:");
        System.out.print("Pre order traversal of the tree: "); bst.preorder();
        System.out.println();
        System.out.print("Post order traversal of the tree: "); bst.postorder();
        System.out.println();
        System.out.print("Inorder traversal of the tree: "); bst.inorder();
        System.out.println();
        System.out.print("Level Order traversal of the tree: "); bst.levelorder();
        System.out.println();
        System.out.println("Height of the tree:" + bst.height());
        System.out.println("Size of the tree:" + bst.size());
        System.out.println("Smallest node in the tree:" + bst.getSmallestNode());
        System.out.println("Largest node in the tree:" + bst.getLargestNode());

    }

    private <T extends Comparable<T>> void testBST(BinarySearchTree<T> bst, T[] sampleData) {
        assertTrue(ensureInsert(bst, sampleData));
        assertTrue(ensureDelete(bst, sampleData));
    }

    private <T extends Comparable<T>> boolean ensureInsert(BinarySearchTree<T> bst, T[] sampleData) {
        for(T e : sampleData) {
            bst.insert(e);
            if(!bst.contains(e)) {
                System.err.println("Something went wrong as element: " + e + " does not exist in tree!");
                return false;
            }
            if(!bst.validate()) {
                System.err.println("BST validation failed post insertion of element:" + e);
                return false;
            }
        }
        return true;
    }

    private <T extends Comparable<T>> boolean ensureDelete(BinarySearchTree<T> bst, T[] sampleData) {
        for(T e : sampleData) {
            if(!bst.contains(e)) {
                System.err.println("Something went wrong as element: " + e + " does not exist in tree!");
                return false;
            }
            bst.delete(e);
            if(bst.contains(e)) {
                System.err.println("Something went wrong as element " + e + " exist even post deletion in tree!");
                return false;
            }
            if(!bst.validate()) {
                System.err.println("BST validation failed post deletion of element: " + e);
                return false;
            }
        }
        return true;
    }
}
