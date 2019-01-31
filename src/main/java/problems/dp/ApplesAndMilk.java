package main.java.problems.dp;

import java.util.Arrays;

/***
 Monk is a fitness freak. He recently joined a gym and he is very serious about it. He follows his instructor's instructions very seriously. His instructor suggested him to eat as many apples as possible. Monk knows that apples from market are not that
 good so he goes daily to the farms and gets apples for himself. Initially Monk is at his house, and to move to the first farm it shall take him one unit of energy. He can move sequentially from his house to the first farm, from the first one to the
 second one, from the second one to the third one and so on.

 So, his directions of movement shall be : House
 −> 1st farm −> 2nd -> 3rd -> 4th -> ......->Nth and so on

 There are N farms in a line. Running from one farm to the next one consumes a single unit of Monk's current energy . So sometimes he may have to refill his energy. He can ask a farm owner to give some milk so that he can gain some energy. The Farm owner
 agrees to give him milk only on one condition that he wont be allowed to take apples from that farm.

 So, at each farm, Monk has one choice either to take milk ( for increasing his energy) or apples from the farm. Each farm has different amount of apples and milk. So, from each farm, Monk is allowed to take only either the entire amount of Milk or the
 entire amount of apples and not none or both. By following so, what is the maximum number of apples Monk can collect, always having non-negative energy ?

 Initially Monk is at his house, and to move to the first farm it shall take him one unit of energy.
 *
 *
 * https://www.hackerearth.com/practice/algorithms/dynamic-programming/2-dimensional/practice-problems/algorithm/collecting-apples-69/description/
 */
public class ApplesAndMilk {
    int[] milk;
    int[] apples;
    int n; // number of farms
    int initialEnergy;
    int[][] solution;   // solution[i][j] = number of apples we have at ith farm and our energy is j unit.

    public ApplesAndMilk(int[] milk, int[] apples, int n, int initialEnergy) {
        this.milk = milk;
        this.apples = apples;
        this.n = n;
        this.initialEnergy = initialEnergy;
        this.solution = new int[n + 1][n + 1];
        for (int i = 0; i < n; i++)
            Arrays.fill(solution[i], -1);   // initially fill with -1.
    }

    private int maxApples() {
        solution[0][initialEnergy] = 0;     // At zeroth farm and with initial energy = initialEnergy we have zero apples in hand.

        // If we take apples at ith farm our energy is reduced by 1 but number of apples will increase = original number + apples at this farm.
        // solution [i][j-1] =  max (solution[i][j-1] , solution[i][j-1] + apples[i])  where 1 <= j <= n

        // If we take milk at ith farm then our energy will be increased by amount of milk but number of apples will not change. The energy increased cannot be more than n as we do not need to visit more than n farms.
        // solution[i][ min(j-1+milk[i], n] = max (solution[i][ min(j-1+milk[i], n], solution[i-1][j]) where 1<=j <= n
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (solution[i - 1][j] == -1)
                    continue; // If previous count was -1 continue.
                solution[i][j - 1] = Math.max(solution[i][j - 1], solution[i - 1][j] + apples[i]);
                solution[i][Math.min(j - 1 + milk[i], n)] = Math.max(solution[i][Math.min(j - 1 + milk[i], n)], solution[i - 1][j]);
            }
            solution[i][0] = Math.max(solution[i][0], solution[i - 1][0]);
        }
        int max = 0;
        for (int i = 0; i <= n; i++) {
            max = Math.max(max, solution[n][i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int numberOfFarms = 3;
        int initialEnergy = 2;
        int[] milk = new int[numberOfFarms+1];
        milk[1] = 1; milk[2]=2; milk[3]=1;
        int[] apples = new int[numberOfFarms+1];
        apples[1]=100; apples[2]=1; apples[3]=100;
        ApplesAndMilk applesAndMilk = new ApplesAndMilk(milk, apples, numberOfFarms, initialEnergy);
        System.out.println(applesAndMilk.maxApples());
    }
}
