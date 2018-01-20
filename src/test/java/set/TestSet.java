package test.java.set;

import main.java.ds.set.DisjointSet;
import org.junit.Test;

public class TestSet {

    @Test
    public void testDisjointSet()
    {
        int NumElements = 128;
        int NumInSameSet = 16;

        DisjointSet s = new DisjointSet(NumElements);
        int set1, set2;

        for (int k = 1; k < NumInSameSet; k *= 2) {
            for (int j = 0; j + k < NumElements; j += 2 * k) {
                set1 = s.find(j);
                set2 = s.find(j + k);
                s.union(set1, set2);
            }
        }

        for (int i = 0; i < NumElements; i++) {
            System.out.print(s.find(i) + "*");
            if (i % NumInSameSet == NumInSameSet - 1) {
                System.out.println();
            }
        }
        System.out.println();
    }
}
