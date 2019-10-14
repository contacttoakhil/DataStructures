package main.java.problems.others;

import java.util.*;

/***
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * insert(val): Inserts an item val to the set if not already present.
 * remove(val): Removes an item val from the set if present.
 * getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
 * Example:
 *
 * // Init an empty set.
 * RandomizedSet randomSet = new RandomizedSet();
 *
 * // Inserts 1 to the set. Returns true as 1 was inserted successfully.
 * randomSet.insert(1);
 *
 * // Returns false as 2 does not exist in the set.
 * randomSet.remove(2);
 *
 * // Inserts 2 to the set, returns true. Set now contains [1,2].
 * randomSet.insert(2);
 *
 * // getRandom should return either 1 or 2 randomly.
 * randomSet.getRandom();
 *
 * // Removes 1 from the set, returns true. Set now contains [2].
 * randomSet.remove(1);
 *
 * // 2 was already in the set, so return false.
 * randomSet.insert(2);
 *
 * // Since 2 is the only number in the set, getRandom always return 2.
 * randomSet.getRandom();
 */
public class LC380InsertDeleteGetRandomO1 {
    private List<Integer> list;
    private Map<Integer, Integer> map;

    public LC380InsertDeleteGetRandomO1() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }
    public boolean insert(int val) {
        if (map.containsKey(val))
            return false;
        int index = list.size();
        list.add(val);
        map.put(val, index);
        return true;
    }
    public boolean remove(int val) {
        if (!map.containsKey(val))
            return false;
        int indexRemove = map.get(val);
        int tail = list.get(list.size() - 1);
        swap(indexRemove, list.size() - 1);
        map.put(tail, indexRemove);
        list.remove(list.size() - 1);
        map.remove(val);
        return true;
    }
    public int getRandom() {
        if (list.isEmpty())
            return 0;
        Random rand = new Random();
        int index = rand.nextInt(list.size());
        return list.get(index);
    }

    private void swap(int i, int j) {
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
