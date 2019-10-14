package main.java.problems.design;

import java.util.*;

/***
 * You are given several logs that each log contains a unique id and timestamp. Timestamp is a string that has the following format: Year:Month:Day:Hour:Minute:Second, for example, 2017:01:01:23:59:59. All domains are zero-padded decimal numbers.
 * Design a log storage system to implement the following functions:
 * void Put(int id, string timestamp): Given a log's unique id and timestamp, store the log in your storage system.
 *
 * int[] Retrieve(String start, String end, String granularity): Return the id of logs whose timestamps are within the range from start to end. Start and end all have the same format as timestamp. However, granularity means the time level for consideration. For example, start = "2017:01:01:23:59:59", end = "2017:01:02:23:59:59", granularity = "Day", it means that we need to find the logs within the range from Jan. 1st 2017 to Jan. 2nd 2017.
 * Example 1:
 * put(1, "2017:01:01:23:59:59");
 * put(2, "2017:01:01:22:59:59");
 * put(3, "2016:01:01:00:00:00");
 * retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year"); // return [1,2,3], because you need to return all logs within 2016 and 2017.
 * retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour"); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.
 * Note:
 * There will be at most 300 operations of Put or Retrieve.
 * Year ranges from [2000,2017]. Hour ranges from [00,23].
 * Output for Retrieve has no order required.
 */
public class LC635DesignLogStorageSystem {
    private static final int MINUTE = 60;
    private static final int HOUR = 60 * MINUTE;
    private static final int DAY = 24 * HOUR;
    private static final int MONTH = 30 * DAY;
    private static final int YEAR = 365 * DAY;

    private TreeMap< Long, Integer > map;

    public LC635DesignLogStorageSystem() {
        map = new TreeMap <> ();
    }

    public void put(int id, String timestamp) {
        int[] st = Arrays.stream(timestamp.split(":")).mapToInt(Integer::parseInt).toArray();
        map.put(convert(st), id);
    }
    public List< Integer > retrieve(String s, String e, String gra) {
        List<Integer> res = new ArrayList<>();
        long start = granularity(s, gra, false);
        long end = granularity(e, gra, true);
        for (long key: map.tailMap(start).keySet()) {
            if (key >= start && key < end)
                res.add(map.get(key));
        }
        return res;
    }
    private long convert(int[] st) {
        st[1] = st[1] - (st[1] == 0 ? 0 : 1);
        st[2] = st[2] - (st[2] == 0 ? 0 : 1);
        return (st[0] - 1999L) * YEAR + st[1] * MONTH + st[2] * DAY + st[3] * HOUR + st[4] * MINUTE + st[5];
    }
    private long granularity(String s, String gra, boolean end) {
        Map< String, Integer > h = new HashMap<>();
        h.put("Year", 0);
        h.put("Month", 1);
        h.put("Day", 2);
        h.put("Hour", 3);
        h.put("Minute", 4);
        h.put("Second", 5);
        String[] res = new String[] {"1999", "00", "00", "00", "00", "00"};
        String[] st = s.split(":");
        if (h.get(gra) + 1 >= 0) System.arraycopy(st, 0, res, 0, h.get(gra) + 1);
        int[] t = Arrays.stream(res).mapToInt(Integer::parseInt).toArray();
        if (end)
            t[h.get(gra)]++;
        return convert(t);
    }

    public static void main(String[] args) {
        LC635DesignLogStorageSystem designLogStorageSystem = new LC635DesignLogStorageSystem();
        designLogStorageSystem.put(1, "2017:01:01:23:59:59");
        designLogStorageSystem.put(2, "2017:01:01:22:59:59");
        designLogStorageSystem.put(3, "2016:01:01:00:00:00");
        System.out.println(designLogStorageSystem.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Year")); // return [1,2,3], because you need to return all logs within 2016 and 2017.
        System.out.println(designLogStorageSystem.retrieve("2016:01:01:01:01:01","2017:01:01:23:00:00","Hour")); // return [1,2], because you need to return all logs start from 2016:01:01:01 to 2017:01:01:23, where log 3 is left outside the range.

    }
}
