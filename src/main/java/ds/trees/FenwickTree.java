package main.java.ds.trees;

/***
 *
 * Source: https://www.topcoder.com/community/competitive-programming/tutorials/binary-indexed-trees/
 * https://www.hackerearth.com/practice/notes/binary-indexed-tree-or-fenwick-tree/
 *
 */
public class FenwickTree {

    int[] array; // 1-indexed array, In this array We save cumulative information to perform efficient range queries and updates

    public FenwickTree(int size) {
        array = new int[size + 1];
    }

    /**
     * Range Sum query from 1 to ind
     * ind is 1-indexed
     * <p>
     * Time-Complexity:    O(log(n))
     *
     * @param  ind index
     * @return sum
     */
    public int rsq(int ind) {
        assert ind > 0;
        int sum = 0;
        while (ind > 0) {
            sum += array[ind];
            //Extracting the portion up to the first significant one of the binary representation of 'ind' and decrementing ind by that number
            ind -= ind & (-ind); // move up till root to sum
        }
        return sum;
    }

    /**
     * Range Sum Query from a to b.
     * Search for the sum from array index from a to b
     * a and b are 1-indexed
     * <p>
     * Time-Complexity:    O(log(n))
     *
     * @param  a left index
     * @param  b right index
     * @return sum
     */
    public int rsq(int a, int b) {
        assert b >= a && a > 0 && b > 0;
        return rsq(b) - rsq(a - 1);
    }

    /**
     * Update the array at ind and all the affected regions above ind.
     * ind is 1-indexed
     * <p>
     * Time-Complexity:    O(log(n))
     *
     * @param  ind   index
     * @param  value value
     */
    public void update(int ind, int value) {
        assert ind > 0;
        while (ind < array.length) {
            array[ind] += value;
            //Extracting the portion up to the first significant one of the binary representation of 'ind' and incrementing ind by that number
            ind += ind & (-ind);
        }
    }

    public int size() {
        return array.length - 1;
    }

    /***
     * Find index for the given element (binary search)
     * @param val
     * @return
     */
    public int index(long val) {
        int l = 0, r = array.length - 1, m = 0;
        while (l <= r) {
            m = l + ((r - l) >> 1);
            if (array[m] >= val) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return l + 1;   // its one-indexed so +1
    }

    // Reinsert the element
    public void insert(int i) {
        while (i > 0) {
            array[i] += 1;
            i = i - (i & -i);
        }
    }

    public int search(int i) {
        int sum = 0;
        while (i < array.length) {
            sum = sum + array[i];
            i = i + (i & -i);
        }
        return sum;
    }
}
