package main.java.problems.arrays.median;

/**
 * Given 2 sorted arrays A and B find the median of the array obtained after merging the above 2 arrays(i.e. array of length 2n). The complexity should be O(log(n)).
 *
 *  Case 1:  same size
 *  A = {1, 12, 15, 26, 38}
 *  B = {2, 13, 17, 30, 45}
 *  One of the simplest approaches is to merge them and then find median. For example {1, 2, 12, 13, 15, 17, 26, 30, 38, 45} amd median is (15+17)/2 = 16. This approach runs in O(n) time.
 *  https://www.geeksforgeeks.org/median-of-two-sorted-arrays/
 *
 *  Case 2: different size
 *  A = {1, 12, 15, 26, 38}
 *  B = {2, 13, 17, 30, 45, 50}
 *  Now median is 17.
 *
 *  The below algorithms work for both of them : findMedianUsingKth - O(logn) findMedianSortedArrays - O(logn)
 *
 *  References:
 *
 *  https://ocw.mit.edu/courses/electrical-engineering-and-computer-science/6-046j-introduction-to-algorithms-sma-5503-fall-2005/assignments/ps9sol.pdf
 *  https://articles.leetcode.com/median-of-two-sorted-arrays/#comment-1053
 *  https://www.programcreek.com/2012/12/leetcode-median-of-two-sorted-arrays-java/
 *  https://www.geeksforgeeks.org/median-of-two-sorted-arrays/
 */
public class MedianOfTwoSortedArrays {

    double findMedianUsingKth(int a[], int b[]) {
        boolean totalLengthEven = (a.length + b.length) % 2 == 0;
        int k = (a.length + b.length) / 2;
        if(totalLengthEven){
            return (findKth(k + 1, a, b, 0, 0)+findKth(k, a, b, 0, 0))/2.0;
        }else{
            return findKth(k + 1, a, b, 0, 0);
        }
    }

    public int findKth(int k, int[] a, int[] b, int s1, int s2){
        if(s1 >= a.length)        // all elements in A used
            return b[s2 + k - 1];

        if(s2 >= b.length)        // all elements in B used
            return a[s1 + k - 1];

        if(k == 1)                // both A and B have one element left
            return Math.min(a[s1], b[s2]);

        int mid1 = s1 + k/2 - 1;      // eliminate half of k
        int mid2 = s2 + k/2 - 1;

        // Now we need to eliminate all elements to the left of either mid1 or mid2 (inclusively). So we need to compare the value of A[mid1] and B[mid2]
        int valA = mid1 < a.length ? a[mid1] : Integer.MAX_VALUE;             // A[mid1] - mid1 and mid2 can be out of bound and in that case val would be MAX_VALUE because median could not fall on the other array.
        int valB = mid2 < b.length ? b[mid2] : Integer.MAX_VALUE;             // B[mid2]
        int halfOfK = k - k/2; // for k =3 halfOfK would be 2.
        if(valA > valB){
            return findKth(halfOfK, a, b, s1, mid2+1);                    // discard mid2 and all elements to the left of it.
        }else{
            return findKth(halfOfK, a, b, mid1+1, s2);
        }
    }

    double findMedian(int A[], int B[], int l, int r, int nA, int nB) {  // size(B) > size(A) or nB > nA
        if (l>r)
            return findMedian(B, A, Math.max(0, (nA+nB)/2-nA), Math.min(nB, (nA+nB)/2), nB, nA);
        int i = (l+r)/2;
        int j = (nA+nB)/2 - i - 1;
        // if A[i] is median then B[j] <= A[i] <=B[j+1]
        if (j>=0 && A[i] < B[j]) return findMedian(A, B, i+1, r, nA, nB);
        else if (j<nB-1 && A[i] > B[j+1]) return findMedian(A, B, l, i-1, nA, nB);
        else {
            if ( (nA+nB)%2 == 1 ) return A[i];
            else if (i>0) return (A[i]+Math.max(B[j], A[i-1]))/2.0;
            else return (A[i]+B[j])/2.0;
        }
    }

    double findMedianSortedArrays(int A[], int n, int B[], int m) {
        if (n<m) return findMedian(A, B, 0, n-1, n, m);
        else return findMedian(B, A, 0, m-1, m, n);
    }

    public static void main(String[] args) {
        double h = Math.ceil(3/2);
        int[] a = {1, 12, 15, 26, 38};
        int [] b = {2, 13, 17, 30, 45, 50};
        MedianOfTwoSortedArrays msa = new MedianOfTwoSortedArrays();
        double median = msa.findMedianSortedArrays(a, a.length, b, b.length);
        double median2 = msa.findMedianUsingKth(a, b);
        System.out.println(median + " : " + median2 );
    }
}
