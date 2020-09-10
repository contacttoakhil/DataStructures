package main.java.problems.binarySearch;

/***
 * On a horizontal number line, we have gas stations at positions stations[0], stations[1], ..., stations[N-1], where N = stations.length.
 *
 * Now, we add K more gas stations so that D, the maximum distance between adjacent gas stations, is minimized.
 *
 * Return the smallest possible value of D.
 *
 * Example:
 *
 * Input: stations = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10], K = 9
 * Output: 0.500000
 * Note:
 *
 * stations.length will be an integer in range [10, 2000].
 * stations[i] will be an integer in range [0, 10^8].
 * K will be an integer in range [1, 10^6].
 * Answers within 10^-6 of the true value will be accepted as correct.
 */
public class LC774MinimizeMaxDistanceGasStation {
    public double minmaxGasDist(int[] stations, int K) {
        double lo = 0, hi = stations[stations.length-1] - stations[0];
        while (lo + 1e-6 < hi) {
            double mi = (lo + hi) / 2.0;
            if (possible(mi, stations, K))     hi = mi;
            else                               lo = mi;
        }
        return lo;
    }
    public boolean possible(double mid, int[] stations, int K) {
        int count = 0;
        for (int i = 0; i < stations.length - 1; ++i)
            count += Math.ceil((stations[i + 1] - stations[i]) / mid) - 1;
        return count <= K;
    }

    public static void main(String[] args) {
        LC774MinimizeMaxDistanceGasStation minimizeMaxDistanceGasStation = new LC774MinimizeMaxDistanceGasStation();
        double result = minimizeMaxDistanceGasStation.minmaxGasDist(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 9);
        System.out.println(result);
    }
}
