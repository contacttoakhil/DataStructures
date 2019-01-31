package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LC89GrayCode {

    public List<Integer> grayCode(int n) {
        if(n==0){
            List<Integer> result = new ArrayList<Integer>();
            result.add(0);
            return result;
        }

        List<Integer> result = grayCode(n-1);
        int numToAdd = 1<<(n-1);

        for(int i=result.size()-1; i>=0; i--){
            result.add(numToAdd+result.get(i));
        }

        return result;
    }

    public static void main(String[] args) {
        LC89GrayCode lc89GrayCode = new LC89GrayCode();
        List<Integer> result = lc89GrayCode.grayCode(2);
        System.out.println(result);
    }
}
