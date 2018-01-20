package test.java.trees.bst.balanced;

import main.java.ds.trees.bst.balanced.AVLTree;
import org.junit.Test;
import test.java.DataGenerator;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestAVL {
    @Test
    public void testAVL()
    {
        Integer[] sampleData = DataGenerator.generateIntegersWithoutDuplicates(50, 1000);
        testAVL(new AVLTree<>(),sampleData);
    }

    @Test
    public void testAVLBasic() {
        AVLTree<Integer> bst = new AVLTree<>();
        List<Integer> integers = Arrays.asList(481,386,706,515,772,614,166,937,811,524,429,910,494,532,982,663,890,764,574,927);
        for(Integer value: integers) {
            bst.insert(value);
        }
        bst.print();
        bst.delete(481);
        bst.delete(386);
        bst.delete(706);
        bst.delete(515);
        bst.validate();
        bst.printTree();
        System.out.println("Size of the tree:" + bst.size());
    }

    private <T extends Comparable<T>> void testAVL(AVLTree<T> avlTree, T[] sampleData) {
        assertTrue(ensureInsert(avlTree, sampleData));
        assertTrue(ensureDelete(avlTree, sampleData));
    }

    private <T extends Comparable<T>> boolean ensureInsert(AVLTree<T> avlTree, T[] sampleData) {
        for(T e : sampleData) {
            avlTree.insert(e);
            if(!avlTree.contains(e)) {
                System.err.println("Something went wrong as element: " + e + " does not exist in tree!");
                return false;
            }
            if(!avlTree.validate()) {
                System.err.println("AVL validation failed post insertion of element:" + e);
                return false;
            }
        }
        return true;
    }

    private <T extends Comparable<T>> boolean ensureDelete(AVLTree<T> avlTree, T[] sampleData) {
        for(T e : sampleData) {
            if(!avlTree.contains(e)) {
                System.err.println("Something went wrong as element: " + e + " does not exist in tree!");
                return false;
            }
            avlTree.delete(e);
            if(avlTree.contains(e)) {
                System.err.println("Something went wrong as element " + e + " exist even post deletion in tree!");
                return false;
            }
            if(!avlTree.validate()) {
                System.err.println("AVL validation failed post deletion of element: " + e);
                return false;
            }
        }
        return true;
    }
}
