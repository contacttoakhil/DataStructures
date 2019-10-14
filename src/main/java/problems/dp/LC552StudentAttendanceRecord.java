package main.java.problems.dp;

/***
 * Given a positive integer n, return the number of all possible attendance records with length n, which will be regarded as rewardable. The answer may be very large, return it after mod 109 + 7.
 *
 * A student attendance record is a string that only contains the following three characters:
 *
 * 'A' : Absent.
 * 'L' : Late.
 * 'P' : Present.
 * A record is regarded as rewardable if it doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 *
 * Example 1:
 * Input: n = 2
 * Output: 8
 * Explanation:
 * There are 8 records with length 2 will be regarded as rewardable:
 * "PP" , "AP", "PA", "LP", "PL", "AL", "LA", "LL"
 *
 * Only "AA" won't be regarded as rewardable owing to more than one absent times.
 * Note: The value of n won't exceed 100,000.
 *
 * Solution: A student is rewardable if does not contain:
 * a) more than one A  OR
 * b) more than two continuous L
 *
 * P[i] = total number of all possible attendance records ended with 'P' (without A) with length i
 * L(n) = total number of all possible attendance records ended with 'L' (without A) with length i
 *
 * Calculate P[i] for 2<=i<=n
 * a) If its (i - 1)th character is 'P' then can add 'P'. ("PP")
 * b) If its (n - 1)th character is 'L' then can add 'P'. ("LP")
 * P[i] = ( P[i-1] + L[i-1] ) % MOD (so as to stay in range)
 *
 * Calculate L[i] for 2<=i<=n
 * a) If its (i - 1)th char is 'P' then can add 'L' as "PL" is okay.
 * b) If its (i - 1)th char is 'L'
 *      i) if (i-2)th char is L then CANNOT add 'L' as "LLL" is not rewardable.
 *      ii) if (i-2)th char is P then can add as PLL is okay.
 * L[i] = ( P[i-1] + P[i-2] ) % MOD
 *
 * res = ( P[n] + L[n] ) % MOD
 *
 * Now we need to deal with insertion of one A for 0<=i<n
 * (P[i] + L[i])%MOD => attendances of size i without A
 * (P[n-i-1] + L[n-i-1])%MOD ) => attendances of size (n-i-i) without A and we can add one A then also same count.
 *
 *
 */
public class LC552StudentAttendanceRecord {

    static final int MOD = (int) (1e9+7);
    public int checkRecord(int n){
        long[] P = new long[n+1]; //end with P without A
        long[] L = new long[n+1]; //end with L without A
        P[0] = P[1] = L[1] = 1;
        for(int i = 2; i <= n; i++){
            P[i] = (P[i-1] + L[i-1]) % MOD;
            L[i] = (P[i-1] + P[i-2]) % MOD;
        }
        long res = (P[n] + L[n]) % MOD;
        //insert A
        for(int i = 0; i < n; i++){
            long s = ((P[i] + L[i])%MOD * (P[n-i-1] + L[n-i-1])%MOD )% MOD;
            res = (res + s) % MOD;
        }
        return (int) res;
    }

    public static void main(String[] args) {
        LC552StudentAttendanceRecord studentAttendanceRecord = new LC552StudentAttendanceRecord();
        System.out.println(studentAttendanceRecord.checkRecord(2));
    }
}
