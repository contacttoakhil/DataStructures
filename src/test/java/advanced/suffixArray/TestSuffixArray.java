package test.java.advanced.suffixArray;

import main.java.ds.advanced.suffixArray.SuffixArray;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestSuffixArray {

    @Test
    public void testFetch()
    {
        String str = "banana";
        SuffixArray sa = new SuffixArray(str); //suffix aray would be: [a,ana,anana,banana,na,nana]
        assertEquals(sa.fetch(5), str.substring(2)); // str.substring(2) would be nana.
    }

    @Test
    public void testRank()
    {
        String str = "banana";
        SuffixArray sa = new SuffixArray(str); //suffix aray would be: [a,ana,anana,banana,na,nana]
        assertEquals(sa.fetch(5), str.substring(2)); // str.substring(2) would be nana.
    }
}
