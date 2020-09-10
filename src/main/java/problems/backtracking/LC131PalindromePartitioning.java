package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LC131PalindromePartitioning {

    private List<List<String>> result = new ArrayList<>();

    public List<List<String>> partition(String input) {
        List<List<String>> list = new ArrayList<>();
        compute(new ArrayList<>(), input, 0);
        return list;
    }

    public void compute(List<String> temp, String input, int start) {
        if (start == input.length()) {
            result.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < input.length(); i++) {
            if (isPalindrome(input, start, i)) {
                temp.add(input.substring(start, i + 1));
                compute(temp, input, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }

    public boolean isPalindrome(String s, int low, int high){
        while(low < high)
            if(s.charAt(low++) != s.charAt(high--)) return false;
        return true;
    }

    public static void main(String[] args) {
        LC131PalindromePartitioning pp = new LC131PalindromePartitioning();
        List<List<String>> result = pp.partition("AAB");
        System.out.println(result);
    }
}
