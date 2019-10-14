package main.java.problems.others;

import java.util.*;

/***
 * Implement a data structure supporting the following operations:
 *
 * Inc(Key) - Inserts a new key with value 1. Or increments an existing key by 1. Key is guaranteed to be a non-empty string.
 * Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise decrements an existing key by 1. If the key does not exist, this function does nothing. Key is guaranteed to be a non-empty string.
 * GetMaxKey() - Returns one of the keys with maximal value. If no element exists, return an empty string "".
 * GetMinKey() - Returns one of the keys with minimal value. If no element exists, return an empty string "".
 * Challenge: Perform all these in O(1) time complexity.
 */
public class LC432AllOOneDataStructure {
    private TreeMap<Integer,HashSet<String>> reversedIndex;
    private HashMap<String,Integer> index;

    public LC432AllOOneDataStructure() {
        this.reversedIndex = new TreeMap<>();
        this.index = new HashMap<>();
    }

    public void inc(String key) {
        int currentCount = index.getOrDefault(key, 0);
        if(index.containsKey(key)) {
            this.reversedIndex.get(currentCount).remove(key);
            if (this.reversedIndex.get(currentCount).size() == 0)
                this.reversedIndex.remove(currentCount);
        }
        index.put(key, currentCount + 1);
        this.reversedIndex.putIfAbsent(currentCount + 1, new HashSet<>());
        this.reversedIndex.get(currentCount + 1).add(key);
    }

    public void dec(String key) {
        if(!index.containsKey(key)) return;
        int currentCount = this.index.get(key);
        this.reversedIndex.get(currentCount).remove(key);
        if(this.reversedIndex.get(currentCount).size() == 0)
            this.reversedIndex.remove(currentCount);
        if(currentCount == 1) {
            this.index.remove(key);
            return;
        }
        this.reversedIndex.putIfAbsent(currentCount - 1, new HashSet<>());
        this.reversedIndex.get(currentCount -1).add(key);
        this.index.put(key,currentCount - 1);
    }

    public String getMaxKey() {
        if (this.index.size() == 0 ) return "";
        return this.reversedIndex.get(this.reversedIndex.lastKey()).iterator().next();
    }

    public String getMinKey() {
        if (this.index.size() == 0 ) return "";
        return this.reversedIndex.get(this.reversedIndex.firstKey()).iterator().next();
    }

}
