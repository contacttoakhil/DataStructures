package main.java.problems.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/***
 * A group of friends went on holiday and sometimes lent each other money. For example, Alice paid for Bill's lunch for $10. Then later Chris gave Alice $5 for a taxi ride. We can model each transaction as a tuple (x, y, z) which means person x gave person y $z. Assuming Alice, Bill, and Chris are person 0, 1, and 2 respectively (0, 1, 2 are the person's ID), the transactions can be represented as [[0, 1, 10], [2, 0, 5]].
 * Given a list of transactions between a group of people, return the minimum number of transactions required to settle the debt.
 * Note:
 * A transaction will be given as a tuple (x, y, z). Note that x ≠ y and z > 0.
 * Person's IDs may not be linear, e.g. we could have the persons 0, 1, 2 or we could also have the persons 0, 2, 6.
 * Example 1:
 * Input:
 * [[0,1,10], [2,0,5]]
 *
 * Output:
 * 2
 *
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #2 gave person #0 $5.
 *
 * Two transactions are needed. One way to settle the debt is person #1 pays person #0 and #2 $5 each.
 * Example 2:
 * Input:
 * [[0,1,10], [1,0,1], [1,2,5], [2,0,5]]
 *
 * Output:
 * 1
 *
 * Explanation:
 * Person #0 gave person #1 $10.
 * Person #1 gave person #0 $1.
 * Person #1 gave person #2 $5.
 * Person #2 gave person #0 $5.
 *
 * Therefore, person #1 only need to give person #0 $4, and all debt is settled.
 *
 * Solution:
 * Backtracking: time complexity O(N!)
 *
 * Use HashMap to store the initial debts of each person, negative means the person sends money to others, positive means the person gets money from others.
 *
 * now if the map value is 0, which means the person is all set, free of debts.
 *
 * Only consider those people with debts(either positive or negative)
 *
 * store them in an array, use backtracking and greedy to clear each person's debts from 1st person till last one.
 *
 * How to clear one person's debt? find a person 2 in the following array that has opposite sign(+->-  or - -> +), and clear person1's whole debt with person2 only.
 *
 * Here's the trick: example: [7, -6, -1], one obvious optimal solution is person1 pay $6 to person2, and pay $1 to person3. Notice that this optimal solution is equivalent to another solution:
 *
 * person1 pay $7 to person2, and person2 pay $1 to person3. So when doing DFS, everytime we only consider clearing person1's debt wholly with another 1 person, we don't need to consider clearing with other more people, cause clearing with 1 person is already guaranteed to be optimal.
 */
public class LC465OptimalAccountBalancing {
    int res = Integer.MAX_VALUE;

    public int minTransfers(int[][] transactions) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int[] t : transactions) {
            map.put(t[0], map.getOrDefault(t[0], 0) - t[2]);
            map.put(t[1], map.getOrDefault(t[1], 0) + t[2]);
        }
        ArrayList<Integer> debts = new ArrayList<Integer>();
        for (int debt : map.values()) {
            if (debt != 0) debts.add(debt);
        }
        helper(debts, 0, 0);
        return res;
    }

    public void helper(ArrayList<Integer> debts, int start, int count) {
        while (start < debts.size() && debts.get(start) == 0) start++;
        if (start == debts.size()) {
            res = Math.min(res, count);
            return;
        }
        for (int i = start + 1; i < debts.size(); i++) {
            if (debts.get(start) < 0 && debts.get(i) > 0 || debts.get(start) > 0 && debts.get(i) < 0) {
                debts.set(i, debts.get(i) + debts.get(start));
                helper(debts, start + 1, count + 1);
                debts.set(i, debts.get(i) - debts.get(start));
            }
        }
    }

    public static void main(String[] args) {
        int[][] transactions = new int[][]{
                {0, 1, 10},
                {2, 0, 5}
        };
        LC465OptimalAccountBalancing optimalAccountBalancing = new LC465OptimalAccountBalancing();
        System.out.println(optimalAccountBalancing.minTransfers(transactions));
        int[][] transactions2 = new int[][]{
                {0, 1, 10},
                {1, 0, 1},
                {1, 2, 5},
                {2, 0, 5}
        };
        System.out.println(optimalAccountBalancing.minTransfers(transactions2));
    }
}

//https://www.cnblogs.com/EdwardLiu/p/6209752.html
