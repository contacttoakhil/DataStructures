package test.java.list;

import main.java.ds.list.SinglyLinkedList;
import main.java.ds.stack.ArrayStack;
import org.junit.Test;
import test.java.DataGenerator;

import static org.junit.Assert.assertTrue;

public class TestList {

    @Test
    public void testSinglyLinkedList()
    {
        Integer[] sampleData = DataGenerator.generateIntegersWithoutDuplicates(100, 1000);
        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList();
        for(Integer element : sampleData) {
            linkedList.insert(element, 0);
        }
        linkedList.printList();
        for (int i = 0; i < sampleData.length; i++) {
            linkedList.delete(0);
        }
        assertTrue(linkedList.isEmpty());
    }

}
