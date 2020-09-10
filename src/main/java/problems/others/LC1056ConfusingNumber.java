package main.java.problems.others;

import java.util.HashMap;
import java.util.Map;

/***
 * Given a number N, return true if and only if it is a confusing number, which satisfies the following condition:
 *
 * We can rotate digits by 180 degrees to form new digits. When 0, 1, 6, 8, 9 are rotated 180 degrees, they become 0, 1, 9, 8, 6 respectively. When 2, 3, 4, 5 and 7 are rotated 180 degrees, they become invalid. A confusing number is a number that when rotated 180 degrees becomes a different number with each digit valid.
 *
 * Input: 6
 * Output: true
 * Explanation:
 * We get 9 after rotating 6, 9 is a valid number and 9!=6.
 */
public class LC1056ConfusingNumber {
    public boolean confusingNumber(int N) {
        String str = Integer.toString(N);
        char[] ch = {'2','3','4','5','7'};
        for(char c : ch){
            if(str.indexOf(c) != -1)
                return false;
        }
        Map<Character, Character> map=new HashMap<>();
        map.put('0','0');
        map.put('1','1');
        map.put('6','9');
        map.put('8','8');
        map.put('9','6');
        StringBuilder tmp =new StringBuilder();
        for(int i = 0; i < str.length(); i++){
            tmp.append(map.get(str.charAt(i)));
        }
        if(tmp.reverse().toString().equals(str))
            return false;
        else
            return true;
    }

    public static void main(String[] args) {
        LC1056ConfusingNumber confusingNumber = new LC1056ConfusingNumber();
        System.out.println(confusingNumber.confusingNumber(89));
        System.out.println(confusingNumber.confusingNumber(25));
    }
}
