package main.java.problems.others;

import java.util.*;

/***
 *  A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 *  The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
 *
 * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .
 *
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour.
 *
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 *
 * Notes:
 *
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 *
 * https://leetcode.com/problems/the-skyline-problem/
 *
 * [[2, 10], [3, 15], [7, 12], [12, 0], [15, 10], [20, 8], [24, 0]]
 */
public class LC218TheSkylineProblem {

    public List<List<Integer>> getSkyline(int[][] buildings) {
        BuildingPoint[] buildingPoints = getBuildingPoints(buildings);
        TreeMap<Integer, Integer> heightMap = new TreeMap<>(); // better than priority queue as pq does not support lgN time removal.
        heightMap.put(0, 1);
        int prevMaxHeight = 0;       // Before we start initial previous max height is 0.
        List<List<Integer>> result = new ArrayList<>();
        for(BuildingPoint buildingPoint : buildingPoints) {
            int key = buildingPoint.height;
            if (buildingPoint.isStart) // For starting point if height exists increment it else add it.
                heightMap.put(key, heightMap.getOrDefault(key, 0) + 1);
            else
                heightMap.compute(key, (k,v) -> (v > 1) ? v - 1 : null); // For end decrement or remove the height
            int currentMaxHeight = heightMap.lastKey(); // current max-height after removal or addition of building
            if(prevMaxHeight != currentMaxHeight) {     // if height changes we need this point
                result.add(Arrays.asList(buildingPoint.x, currentMaxHeight));
                prevMaxHeight = currentMaxHeight;
            }
        }
        return result;
    }
    //Input would be of the form [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]] where each item is  [start,end,height] for the building.
    private BuildingPoint[] getBuildingPoints(int[][] buildings) {
        BuildingPoint[] buildingPoints = new BuildingPoint[buildings.length*2];
        int idx = 0;
        for(int building[] : buildings) {
            buildingPoints[idx] = new BuildingPoint();
            buildingPoints[idx].x = building[0];
            buildingPoints[idx].isStart = true;
            buildingPoints[idx].height = building[2];
            buildingPoints[idx + 1] = new BuildingPoint();
            buildingPoints[idx + 1].x = building[1];
            buildingPoints[idx + 1].isStart = false;
            buildingPoints[idx + 1].height = building[2];
            idx += 2;
        }
        Arrays.sort(buildingPoints);
        return buildingPoints;
    }
    public static void main(String[] args) {
        LC218TheSkylineProblem theSkylineProblem = new LC218TheSkylineProblem();
        System.out.println(theSkylineProblem.getSkyline(new int[][] {{1,3,3},{2,4,4},{5,8,2},{6,7,4}, {8,9,4}})); // [[1, 3], [2, 4], [4, 0], [5, 2], [6, 4], [7, 2], [8, 4], [9, 0]]
        // System.out.println(theSkylineProblem.getSkyline(new int[][] {{1,3,4},{3,4,4},{2,6,2},{8,11,4}, {7,9,3},{10,11,2}})); // [[1, 4], [4, 2], [6, 0], [7, 3], [8, 4], [11, 0]]
        //System.out.println(theSkylineProblem.getSkyline(new int[][] {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}})); // [[2, 10], [3, 15], [7, 12], [12, 0], [15, 10], [20, 8], [24, 0]]
    }
}


class BuildingPoint implements Comparable<BuildingPoint> {
    int x;
    boolean isStart;
    int height;
    @Override
    public int compareTo(BuildingPoint o) {
        if (this.x != o.x)   return this.x - o.x; // first compare by x
        // if same x then a) if same start then higher b) if same end then lower building should come first.
        return (this.isStart ? -this.height : this.height) - (o.isStart ? -o.height : o.height);
    }
}

