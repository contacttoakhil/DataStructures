package main.java.problems.dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/***
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
 * Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.
 * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.
 * Note:
 * The number of stones is ≥ 2 and is < 1,100.
 * Each stone's position will be a non-negative integer < 231.
 * The first stone's position is always 0.
 * Example 1:
 * [0,1,3,5,6,8,12,17]
 *
 * There are a total of 8 stones.
 * The first stone at the 0th unit, second stone at the 1st unit,
 * third stone at the 3rd unit, and so on...
 * The last stone at the 17th unit.
 *
 * Return true. The frog can jump to the last stone by jumping
 * 1 unit to the 2nd stone, then 2 units to the 3rd stone, then
 * 2 units to the 4th stone, then 3 units to the 6th stone,
 * 4 units to the 7th stone, and 5 units to the 8th stone.
 *
 * https://leetcode.com/problems/frog-jump/
 */
public class LC403FrogJump {
    public boolean canCross(int[] stones) {                                 // 67 ms
        HashMap<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            map.put(stones[i], new HashSet<Integer>());
        }
        map.get(0).add(0);
        for (int i = 0; i < stones.length; i++) {
            for (int k : map.get(stones[i])) {
                for (int step = k - 1; step <= k + 1; step++) {
                    if (step > 0 && map.containsKey(stones[i] + step)) {
                        map.get(stones[i] + step).add(step);
                    }
                }
            }
        }
        return map.get(stones[stones.length - 1]).size() > 0;
    }

    Map<String, Boolean> map = new HashMap<String,  Boolean>();
    public boolean canCrossTwo(int[] stones) {                              // 24 ms
        return canCross(stones, 0, 0);
    }

    public boolean canCross(int[] stones, int pos, int k)
    {
        String key = "pos" + pos + "k" + k;
        if(map.containsKey(key)) return map.get(key);

        for (int i = pos + 1; i < stones.length; i++)
        {
            int gap = stones[i] - stones[pos];
            if (gap < k - 1) continue;
            if (gap > k + 1)
            {
                map.put(key, false);
                return false;
            }
            if (canCross(stones, i, gap))
            {
                map.put(key, true);
                return true;
            }
        }

        map.put(key, pos == stones.length - 1);
        return map.get(key);
    }

}
