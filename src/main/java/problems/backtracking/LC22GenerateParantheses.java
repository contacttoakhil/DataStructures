package main.java.problems.backtracking;

import java.util.ArrayList;
import java.util.List;

public class LC22GenerateParantheses {
    public static final String LEFT_PARAN = "(";
    public static final String RIGHT_PARAN = ")";

    public List<String> generateParantheses(int n) {
        List<String> result = new ArrayList<>();
        generate(result, "", n, n);
        return result;
    }

    private void generate(List<String> result, String temp, int left, int right) {

        if(left > right)
            return;

        if(left == 0 && right == 0) {
            result.add(temp);
            return;
        }

        if(left > 0)
            generate(result, temp + LEFT_PARAN, left-1, right);

        if (right > 0)
            generate(result, temp + RIGHT_PARAN, left, right-1);
    }

    public static void main(String[] args) {
        LC22GenerateParantheses gp = new LC22GenerateParantheses();
        System.out.println(gp.generateParantheses(3));
    }
}
