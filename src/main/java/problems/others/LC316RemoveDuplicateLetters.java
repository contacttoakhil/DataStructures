package main.java.problems.others;

/***
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Example 1:
 *
 * Input: "bcabc"
 * Output: "abc"
 * Example 2:
 *
 * Input: "cbacdcbc"
 * Output: "acdb"
 */
public class LC316RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        int[] occurrences = new int[128];
        boolean[] visited = new boolean[128]; // visited[i] true if character i is present in current result Stack
        for(char c : s.toCharArray())      occurrences[c]++;
        StringBuilder sb = new StringBuilder(); // answer stack
        for(char c : s.toCharArray()) {
            occurrences[c]--;
            if(visited[c]) continue;      // if character is already present in stack, dont bother
            while( (sb.length() > 0) && c < sb.charAt(sb.length()-1) && occurrences[sb.charAt(sb.length()-1)]!=0){
                visited[sb.charAt(sb.length()-1)] = false;
                sb.deleteCharAt(sb.length()-1);
            }
            sb.append(c); // add current character and mark it as visited
            visited[c] = true;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        LC316RemoveDuplicateLetters removeDuplicateLetters = new LC316RemoveDuplicateLetters();
        System.out.println(removeDuplicateLetters.removeDuplicateLetters("bcabc"));
    }
}
