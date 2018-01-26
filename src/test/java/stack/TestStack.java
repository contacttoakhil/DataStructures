package test.java.stack;

import static org.junit.Assert.assertTrue;

import main.java.ds.stack.ArrayStack;
import main.java.ds.stack.IStack;
import main.java.ds.stack.LinkedStack;
import org.junit.Test;
import test.java.DataGenerator;

import java.util.Objects;

public class TestStack {

    @Test
    public void testArrayStack()
    {
        Integer[] sampleData = DataGenerator.generateIntegersWithoutDuplicates(100, 1000);
        testStack(new ArrayStack<>(),sampleData);
    }

    @Test
    public void testLinkedStack()
    {
        Integer[] sampleData = DataGenerator.generateIntegersWithoutDuplicates(100, 1000);
        testStack(new LinkedStack<>(),sampleData);
    }

    @Test
    public void testStackBasic()
    {
        IStack<Integer> stack = new ArrayStack<>();
        stack.push(10); stack.push(20); stack.push(30); stack.push(40); stack.push(50);
        for (Integer e : stack) {
            System.out.println(e);
        }
    }
    private static <E extends Comparable<E>> void testStack(IStack<E> stack, E[] sampleData)
    {
        assertTrue(ensurePush(stack, sampleData, 0, sampleData.length));
        System.out.print("Stack content:");stack.print(",");
        assertTrue(ensurePop(stack, sampleData, 0, stack.size()-1));
        ensurePushPop(stack, sampleData);
    }

    private static <E extends Comparable<E>> boolean ensurePush(IStack<E> stack, E[] sampleData, int startRange, int endRange)
    {
        for(int i=startRange; i<endRange; i++) {
            E e = sampleData[i];
            boolean pushed = stack.push(e);
            if(!pushed || e==null || !stack.contains(e)) {
                System.err.println("Something went wrong as element " + e + " does not exist on stack.");
                return false;
            }
            if(!stack.validate() || (stack.size() != i+1)) {
                System.err.println("Size mismatch during push");
                return false;
            }
        }
        return true;
    }

    private static <E extends Comparable<E>> boolean ensurePop(IStack<E> stack, E[] sampleData, int startRange, int endRange)
    {
        for(int i=endRange; i>=startRange; i--)
        {
            E e = stack.pop();
            Objects.requireNonNull(e);
            E lastElementInData = sampleData[i];
            if(!e.equals(lastElementInData)) {
                System.err.println("Stack has: " + e + " data has: " + lastElementInData + " and LIFO issues with stack.");
                return false;
            }
            if(!stack.validate() || (stack.size() != i)) {
                System.err.println("Size mismatch during pop");
                return false;
            }
            if(stack.contains(e))
            {
                System.err.println("Element " + e + " still present post pop operation.");
                return false;
            }
        }
        return true;
    }

    private static <E extends Comparable<E>> void ensurePushPop(IStack<E> stack, E[] sampleData)
    {
        int half = sampleData.length/2; // initial stack is empty [xxxx]
        assertTrue(ensurePush(stack, sampleData, 0, half)); // add half of sample elements [q1q2xx]
        assertTrue(ensurePop(stack, sampleData, half/2,half-1)); // remove quarter q2 of sample elements from stack[q1xxx]
        assertTrue(ensurePush(stack, sampleData, half/2, sampleData.length)); // add three quarter of sample elements [q1q2q3q4]
        assertTrue(ensurePop(stack, sampleData, 0,sampleData.length-1)); // now remove all to have[xxxx].
    }

}
