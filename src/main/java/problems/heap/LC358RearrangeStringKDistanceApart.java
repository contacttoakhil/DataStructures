package main.java.problems.heap;

import java.util.*;

public class LC358RearrangeStringKDistanceApart {

    public String rearrangeString(String str, int k) {
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = populateFreqs(str);
        Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();
        StringBuilder result = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> entry = maxHeap.poll();
            result.append(entry.getKey());
            entry.setValue(entry.getValue()-1);
            waitQueue.offer(entry);
            if (waitQueue.size() >= k) {
                Map.Entry<Character, Integer> unfreezeEntry = waitQueue.poll();
                if (unfreezeEntry.getValue() > 0) maxHeap.offer(unfreezeEntry);
            }
        }
        return result.length()==str.length()? result.toString() : "";
    }
    // str = aaadbbcc and method would return a pq: {(a->3),(b->2),(c->2),(d->1)}
    private PriorityQueue<Map.Entry<Character, Integer>> populateFreqs(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : str.toCharArray())
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>((e1,e2)-> e2.getValue() - e1.getValue());
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            maxHeap.offer(entry);
        }
        return maxHeap;
    }

    public static void main(String[] args) {
        LC358RearrangeStringKDistanceApart rearrangeStringKDistanceApart = new LC358RearrangeStringKDistanceApart();
        //System.out.println(rearrangeStringKDistanceApart.rearrangeString("aabbcc",3));      //acbacb
        System.out.println(rearrangeStringKDistanceApart.rearrangeString("aaabc",3));
        //System.out.println(rearrangeStringKDistanceApart.rearrangeString("aaadbbcc",2));    //abcabcad
    }

}
