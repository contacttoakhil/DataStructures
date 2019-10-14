package main.java.problems.graphs;

import java.util.*;

/***
 * There are N rooms and you start in room 0.  Each room has a distinct number in 0, 1, 2, ..., N-1, and each room may have some keys to access the children room.
 *
 * Formally, each room i has a list of keys rooms[i], and each key rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.  A key rooms[i][j] = v opens the room with number v.
 *
 * Initially, all the rooms start locked (except for room 0).
 *
 * You can walk back and forth between rooms freely.
 *
 * Return true if and only if you can enter every room.
 *
 * Example 1:
 *
 * Input: [[1],[2],[3],[]]
 * Output: true
 * Explanation:
 * We start in room 0, and pick up key 1.
 * We then go to room 1, and pick up key 2.
 * We then go to room 2, and pick up key 3.
 * We then go to room 3.  Since we were able to go to every room, we return true.
 * Example 2:
 *
 * Input: [[1,3],[3,0,1],[2],[0]]
 * Output: false
 * Explanation: We can't enter the room with number 2.
 * Note:
 *
 * 1 <= rooms.length <= 1000
 * 0 <= rooms[i].length <= 1000
 * The number of keys in all rooms combined is at most 3000.
 *
 * https://leetcode.com/problems/keys-and-rooms/
 *
 * Solution: room[i][j] means a path from room-i to room-j and we need to find connectivity starting from room-0. How about backtrack?
 */
public class LC841KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] seen = new boolean[rooms.size()];
        seen[0] = true;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);  // room-0 is open initially
        while (!stack.isEmpty()) {
            int roomIdx = stack.pop();
            List<Integer> roomKeys = rooms.get(roomIdx);
            for (int roomToCheck : roomKeys) {          // every key-k in roomKeys can open room-k
                if(!seen[roomToCheck]) {
                    seen[roomToCheck] = true;
                    stack.push(roomToCheck);
                }
            }
        }
        for (boolean visitedRoom : seen) {
            if (!visitedRoom) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        LC841KeysAndRooms keysAndRooms = new LC841KeysAndRooms();
        List<List<Integer>> rooms = new ArrayList<>();
        rooms.add(List.of(1));
        rooms.add(List.of(2));
        rooms.add(List.of(3));
        rooms.add(Collections.EMPTY_LIST);
        System.out.println(keysAndRooms.canVisitAllRooms(rooms));
        rooms.clear();
        rooms.add(List.of(1,3));
        rooms.add(List.of(3,0,1));
        rooms.add(List.of(2));
        rooms.add(List.of(0));
        System.out.println(keysAndRooms.canVisitAllRooms(rooms));
    }
}
