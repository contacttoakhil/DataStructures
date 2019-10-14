package main.java.problems.others;

/***
 * Given strings S and T, find the minimum (contiguous) substring W of S, so that T is a subsequenceof W.
 * If there is no such window in S that covers all characters in T, return the empty string "". If there are multiple such minimum-length windows, return the one with the left-most starting index.
 * Example 1:
 *
 * Input:
 * S = "abcdebdde", T = "bde"
 * Output: "bcde"
 * Explanation:
 * "bcde" is the answer because it occurs before "bdde" which has the same length.
 * "deb" is not a smaller window because the elements of T in the window must occur in order.
 *
 * Note:
 * All the strings in the input will only contain lowercase letters.
 * The length of S will be in the range [1, 20000].
 * The length of T will be in the range [1, 100].
 *
 */
public class LC727MinimumWindowSubsequence {
    public String minWindow(String S, String T) {
        int start=0;
        String result = "";

        while(start<S.length()){
            int j=0;

            for(int i=start; i<S.length(); i++) {

                if(S.charAt(i)==T.charAt(j) && j==0){
                    start=i;
                }

                if(S.charAt(i)==T.charAt(j)){
                    j++;
                }

                if(j==T.length()){
                    if(result.equals("")||(i-start+1)<result.length()){
                        result = S.substring(start, i+1);
                    }
                    start=start+1;
                    break;
                }

                if(i==S.length()-1){
                    return result;
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        LC727MinimumWindowSubsequence minimumWindowSubsequence = new LC727MinimumWindowSubsequence();
        System.out.println(minimumWindowSubsequence.minWindow("abcdebdde","bde"));
    }
}
