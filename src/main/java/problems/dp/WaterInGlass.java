package main.java.problems.dp;

import java.util.Arrays;

/***
 * There are some glasses with equal capacity as 1 litre. The glasses are kept as follows:
 *                    1
 *                  2   3
 *               4    5    6
 *             7    8    9   10
 *
 * You can put water to only top glass. If you put more than 1 litre water to 1st glass, water overflows and fills equally in both 2nd and 3rd glasses. Glass 5 will get water from both 2nd glass and 3rd glass and so on.
 * If you have X litre of water and you put that water in top glass, how much water will be contained by jth glass in ith row?
 *
 * https://www.geeksforgeeks.org/find-water-in-a-glass/
 * https://stackoverflow.com/questions/12721730/champagne-pyramid-distribution-puzzle
 * https://stackoverflow.com/questions/11764582/find-the-amount-of-water-in-ith-cup-in-a-pyramid-structure
 * https://softwareengineering.stackexchange.com/questions/184994/champaign-fountain-puzzle
 * https://leetcode.com/problems/pascals-triangle/
 *
 * https://stackoverflow.com/questions/11764582/find-the-amount-of-water-in-ith-cup-in-a-pyramid-structure
 * https://www.careercup.com/question?id=9820788
 *
 */
public class WaterInGlass {

    static double[][] glasses = new double[100][100];

    int fillWater(double capacityOfGlass, double waterQty) {
        glasses[0][0] = waterQty;
        int level = 0;
        boolean waterInLevel = true;
        while(waterInLevel)
        {
            waterInLevel = false;

            // Level:0 glasses:1, Level:1 glasses:2, Level:2 glasses:3 etc.
            for(int j=0; j<=level; j++)
            {
                // If the glass has more liquid then it can store
                // then pour it to glasses under it.
                if(glasses[level][j] > capacityOfGlass)
                {
                    double extraWater = glasses[level][j] - capacityOfGlass;
                    glasses[level][j] = capacityOfGlass;
                    glasses[level+1][j] += extraWater / 2;
                    glasses[level+1][j+1] += extraWater / 2;
                    waterInLevel = true;
                }
            }
            level++;
        }
        return level-1;
    }

    public static void main(String[] args) {
        WaterInGlass waterInGlass = new WaterInGlass();
        int level = waterInGlass.fillWater(1, 10);
        for(int i=0; i<=level; i++)
        {
            for(int j=0; j<=level; j++)
                System.out.print(glasses[i][j]);
            System.out.println();
        }
    }
}
