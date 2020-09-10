package main.java.problems.graphs;

import java.util.*;

/***
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.
 *
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.
 *
 *
 *
 * Example 1:
 *  (1) <--1--- (2)
 *                |
 *                1
 *                |
 *                V
 *  (4)<----1--- (3)
 *
 * Input: times = [[2,1,1],[2,3,1],[3,4,1]], N = 4, K = 2
 * Output: 2
 */
public class LC743NetworkDelayTime {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<Edge>> graph = new HashMap<>();
        for (int[] time : times)
            graph.computeIfAbsent(time[0], ArrayList::new).add(new Edge(time[1],time[2]));
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.dist));
        pq.offer(new Edge(K,0));
        Map<Integer, Integer> distMap = new HashMap<>();
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            if(distMap.containsKey(current.to)) continue;
            distMap.put(current.to, current.dist);
            if(!graph.containsKey(current.to)) continue;
            for(Edge nei: graph.get(current.to)) {
                if(distMap.containsKey(nei.to)) continue;
                pq.add(new Edge(nei.to, current.dist + nei.dist));
            }
        }
        if(distMap.size() != N) return -1;
        return Collections.max(distMap.values());
    }

    public static void main(String[] args) {
        LC743NetworkDelayTime delayTime = new LC743NetworkDelayTime();
        int[][] times = new int[][]{{2,1,1},{2,3,1},{3,4,1}};
        System.out.println(delayTime.networkDelayTime(times, 4, 2));
    }
}
class Edge {
    public int to;
    public int dist;

    public Edge(int to, int dist) {
        this.to = to;
        this.dist = dist;
    }
}
