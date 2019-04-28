package main.java.problems.others;

public class LC05PalindromeNumber {

    public boolean isPalindrome(int x) {
        int reversed = 0;
        int copy = x;

        while(x != 0)
        {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }

        return (reversed == copy);
    }
}
